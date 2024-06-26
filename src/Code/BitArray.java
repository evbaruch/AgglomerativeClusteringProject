package Code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BitArray implements Clusterable<BitArray>{
	private final ArrayList<Boolean> bits;

	public BitArray(String str){
		String[] parts = str.split(",");
		this.bits = new ArrayList<>();
		for (String part : parts) {
			this.bits.add(Boolean.parseBoolean(part));
		}
	}
	public BitArray(boolean[] bits) {
		this.bits = new ArrayList<>();
		for (boolean bit : bits) {
			this.bits.add(bit);
		}
	}

	@Override
	public double distance(BitArray other) {
		// Ensure comparison is up to the length of the shorter bit sequence
		int length = Math.min(this.bits.size(), other.bits.size());
		int diffCount = 0;

		// Count differing bits
		for (int i = 0; i < length; i++) {
			if (!this.bits.get(i).equals(other.bits.get(i))) {
				diffCount++;
			}
		}

		// Count extra bits as differences if lengths are unequal
		diffCount += Math.abs(this.bits.size() - other.bits.size());

		return diffCount;
	}

	// Reading a set of BitArrays from a file
	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		List<String> lines = Files.lines(Paths.get(path))
				.collect(Collectors.toList());
		int maxLength = lines.stream()
				.mapToInt(line -> line.split(",").length)
				.max()
				.orElse(0);
		return lines.stream()
				.filter(line -> line.split(",").length == maxLength)
				.map(BitArray::new)
				.collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
