/** Tests the Planet class */
public class TestPlanet {
    public static void main(String[] args) {
        checkPlanet();
    }

    public static void checkPlanet() {
        Planet planet1 = new Planet(-5, -5, 1.5, -1.5, 5, "");
        Planet planet2 = new Planet(5, 5, -1.5, 3, 20, "");
        checkEquals(3.335e-11, planet1.calcForceExertedBy(planet2), "calcForceExertedBy()", 0.01);
    }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }
}