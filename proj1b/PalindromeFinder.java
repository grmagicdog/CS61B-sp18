/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    private static String longestPal = "";

    public static void main(String[] args) {
        String dataPath = "../library-sp18/data/words.txt";
        int mostN = 0;
        int mostPals = 0;
        for (int n = 0; n < 52; n++) {
            System.out.println("Palindromes off by " + n);
            int numPals = printPalindromes(dataPath, n);
            if (numPals > mostPals) {
                mostN = n;
                mostPals = numPals;
            }
        }
        System.out.println("Palindromes off by " + mostN + " are the most.");
        System.out.println("Longest Palindrome off by N for any N: " + longestPal);
    }

    private static int printPalindromes(String fileName, int offBy) {
        int minLength = 4;
        In in = new In(fileName);
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffByN(offBy);
        int total = 0;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
                total += 1;
                if (word.length() > longestPal.length()) {
                    longestPal = word;
                }
            }
        }
        return total;
    }
}
