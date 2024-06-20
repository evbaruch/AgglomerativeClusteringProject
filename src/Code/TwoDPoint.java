import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	public TwoDPoint(String str){
		// TODO: Complete
	}
	public TwoDPoint(double x, double y) {
		// TODO: Complete
	}
	@Override
	public double distance(TwoDPoint other) {
		// TODO: Complete
		return 0;
	}

	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException{
		// TODO: Complete
		return null;
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
