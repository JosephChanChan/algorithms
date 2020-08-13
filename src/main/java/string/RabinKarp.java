package string;

/**
 * @author Joseph Chan
 * @since 2020/08/13
 */
public class RabinKarp {

    private int doRabinKarp(String s, String t) {
        // calc 31^n-1
        int power = 1;
        for (int i = 0; i < t.length() - 1; i++) {
            power *= 31;
        }

        // calc t hash code
        // using formula s[0]*31^n-1 + s[1]*31^n-2 + ... + s[n]
        int tHash = t.hashCode();

        // compare using hash
        int sHash = 0;
        char head ;
        char[] sArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            head = sArray[i];
            sHash = sHash * 31 + sArray[i];
            if (i < t.length()) continue;
            if (sHash == tHash) {

            }
            // [(a*31^2 + b*31 + c)-a*31^2]*31+d
        }

        return -1;
    }
}
