/** A class that will actually run the simulation.
 *  The goal of this class is to simulate a universe specified in one of the data files. 
 */
public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        int skip = in.readInt();
        return in.readDouble();
    }
}