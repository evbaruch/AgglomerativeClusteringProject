package Code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	// Constructor that takes a String
	public TwoDPoint(String str) {
		String[] parts = str.split(",");
		if (parts.length != 2) {
			throw new IllegalArgumentException("Invalid format for TwoDPoint: " + str);
		}
		try {
			this.x = Double.parseDouble(parts[0].trim());
			this.y = Double.parseDouble(parts[1].trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid numeric values in TwoDPoint: " + str, e);
		}
	}

	// Constructor that takes x and y coordinates
	public TwoDPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Calculate the Euclidean distance to another TwoDPoint
	@Override
	public double distance(TwoDPoint other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

	// Read TwoDPoints from a file and return the set of points
	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException {
		Set<TwoDPoint> pointSet = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					try {
						pointSet.add(new TwoDPoint(line));
					} catch (IllegalArgumentException e) {
						// Skip invalid lines
					}
				}
			}
		}
		return pointSet;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object other) {
		TwoDPoint otherP = (TwoDPoint) other;
		return (otherP.x == x && otherP.y == y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
