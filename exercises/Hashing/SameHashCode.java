public class SameHashCode {

    public static void main(String[] args) {
        String inputString = args[0];
        System.out.println(inputString.hashCode());
        int n = inputString.length();
        String other = inputString.substring(0, n - 2);
        other += (char) (inputString.charAt(n - 2) + 1);
        other += (char) (inputString.charAt(n - 1) - 31);
        System.out.println(other);
        System.out.println(other.hashCode());
    }
}
