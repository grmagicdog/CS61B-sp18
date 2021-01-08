public class Palindrome {

    /** Returns a Deque where the characters appear in the same order
     *  as in the given word. */
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> result = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    /** Returns true if the given word is a palindrome, and false otherwise. */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> d = wordToDeque(word.toLowerCase());
        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /** Returns true if the word is a palindrome according to the character comparison test
     *  provided by the CharacterComparator object passed in as the argument cc. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (cc == null) {
            return isPalindrome(word);
        }
        if (word == null) {
            return false;
        }
        Deque<Character> d = wordToDeque(word.toLowerCase());
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
