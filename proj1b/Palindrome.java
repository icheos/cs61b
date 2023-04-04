import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
//        int mid = dq.size() / 2;
//        for (int i = 0; i < mid; i++) {
//           if (dq.get(i) != dq.get(dq.size() - i - 1)) {
//              return false;
//            }
//        }
//       return true;
        if (word == null || word.length() <= 1) {
            return true;
        }
        if (!isSame(word.charAt(0), word.charAt(word.length() - 1))) {
            return false;
        }
        return isPalindrome(word.substring(1, word.length() - 1));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        if (!cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            return false;
        }
        return isPalindrome(word.substring(1, word.length() - 1), cc);
    }

    private boolean isSame(Character a, Character b) {
        if (a.equals(b)) {
            return true;
        }
        return false;
    }

}
