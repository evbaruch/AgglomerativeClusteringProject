import Code.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

// A simple validation class for agglomerative clustering. DO NOT submit this file
public class AgglomerativeClusteringTest {
	// TODO: change these paths to correspond to the location the files are saved in your computer
	private static final String POINTS_PATH = "src/Datasets/points.txt";
	private static final String BITARRAYS_PATH = "src/Datasets/bitArrays.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter class name: TwoDPoint, BitArray");
		String className = scanner.nextLine();
		System.out.println("Enter Threshold:");
		double threshold = scanner.nextDouble();
		scanner.nextLine();

		if (className.equals("TwoDPoint")) {
			Clustering<TwoDPoint> twoDPointClustering = new AgglomerativeClustering<>(threshold);
			Set<TwoDPoint> points = TwoDPoint.readClusterableSet(POINTS_PATH);
			Set<Set<TwoDPoint>> pointsClusterSet = twoDPointClustering.clusterSet(points);
			System.out.println("The number of 2d point clusters is: " + pointsClusterSet.size());
			// This line creates a visualization of the clusters, where each cluster has its own color
			new MyFrame(pointsClusterSet);
		}

		if (className.equals("BitArray")) {
			AgglomerativeClustering<BitArray> bitArrayClustering = new AgglomerativeClustering<>(threshold);
			Set<BitArray> bitArrays = BitArray.readClusterableSet(BITARRAYS_PATH);
			Set<Set<BitArray>> bitArrayClusterSet = bitArrayClustering.clusterSet(bitArrays);
			System.out.println("The number of bitArray clusters is: " + bitArrayClusterSet.size());
		}

		System.out.println("end:");
	}


}
