package Code;

import java.util.HashSet;
import java.util.Set;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{

	double threshold;

	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}

	public Set<Set<T>> clusterSet(Set<T> elements) {
		// Initially, each element is its own cluster
		Set<Set<T>> clusters = new HashSet<>();
		for (T element : elements) {
			Set<T> singletonCluster = new HashSet<>();
			singletonCluster.add(element);
			clusters.add(singletonCluster);
		}



		while (clusters.size() > threshold) {

			double minDistance = Double.MAX_VALUE;
			Set<T> e1 = null;
			Set<T> e2 = null;

			for (Set<T> element1 : clusters) {

				for (Set<T> element2 : clusters) {
					if (element1.containsAll(element2) && element2.containsAll(element1)) {
						break;
					}

					double distance = this.findMinDistance(element1,element2);

					if (distance < minDistance) {
						minDistance = distance;
						e1 = element1;
						e2 = element2;
					}

				}
			}

			if (e1 != null) {
				Set<T> mergedCluster = new HashSet<>();
				mergedCluster.addAll(e1);
				mergedCluster.addAll(e2);
				clusters.add(mergedCluster);
				clusters.remove(e1);
				clusters.remove(e2);
			}

		}

		// TODO: Complete
		return clusters;
	}

	private double findMinDistance(Set<T> cluster1, Set<T> cluster2) {
		// Create a variable to store the minimum distance found
		double minDistance = Double.MAX_VALUE;

		// Iterate over each element in cluster1
		for (T elem1 : cluster1) {
			// Iterate over each element in cluster2
			for (T elem2 : cluster2) {
				// Calculate the distance between elem1 and elem2
				double distance = elem1.distance(elem2);

				// Update the minimum distance if the current distance is smaller
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
		}

		// Return the smallest distance found
		return minDistance;
	}
}
