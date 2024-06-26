package Code;

import java.util.*;
import java.util.stream.Collectors;

public class AgglomerativeClustering<T extends Clusterable<T>> implements Clustering<T> {

	double threshold;

	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}

	public Set<Set<T>> clusterSet(Set<T> elements) {
		// Initially, each element is its own cluster
		Set<Set<T>> clusters = elements.stream()
				.map(element -> {
					Set<T> singletonCluster = new HashSet<>();
					singletonCluster.add(element);
					return singletonCluster;
				})
				.collect(Collectors.toSet());

		while (clusters.size() > 1) {
			// Find the closest pair of clusters
			Optional<Pair<Set<T>>> closestPair = clusters.stream()
					.flatMap(cluster1 -> clusters.stream()
							.filter(cluster2 -> !cluster1.equals(cluster2))
							.map(cluster2 -> new Pair<>(cluster1, cluster2)))
					.min(Comparator.comparingDouble(pair -> findMinDistance(pair.cluster1, pair.cluster2)));

			if (closestPair.isEmpty()) {
				break; // No more pairs to merge
			}

			if (this.findMinDistance(closestPair.get().cluster1, closestPair.get().cluster2) > this.threshold) {
				return clusters; // No more pairs to merge
			}

			// Merge the closest pair of clusters
			Set<T> mergedCluster = new HashSet<>();
			mergedCluster.addAll(closestPair.get().cluster1);
			mergedCluster.addAll(closestPair.get().cluster2);
			clusters.remove(closestPair.get().cluster1);
			clusters.remove(closestPair.get().cluster2);
			clusters.add(mergedCluster);
		}

		return clusters;
	}

	private double findMinDistance(Set<T> cluster1, Set<T> cluster2) {
		// Find the minimum distance between any two elements from cluster1 and cluster2
		return cluster1.stream()
				.flatMap(elem1 -> cluster2.stream()
						.map(elem1::distance))
				.min(Double::compare)
				.orElse(Double.MAX_VALUE);
	}

	// Helper class to store cluster pairs
	private static class Pair<U> {
		public U cluster1;
		public U cluster2;

		Pair(U cluster1, U cluster2) {
			this.cluster1 = cluster1;
			this.cluster2 = cluster2;
		}
	}
}
