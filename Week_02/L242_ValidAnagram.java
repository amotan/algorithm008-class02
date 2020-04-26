package hashandmapping;

import java.util.Arrays;

/**
 * 有效的字母异位词
 */
public class L242_ValidAnagram {

    /**
     *
     * 切题四件套
     *     1、clarfication(大小写是否敏感，anagram名词意思？)
     *     2、possible sulotions --> optimal (time & space)
     *     3、codes
     *     4、test cases
     */


    //1、排序 Nlog(N)
    //2、hash表统计每次字符的频次（统计次数 或者第一个数组技术加1，第二个数组-1）,二次hash
    //3、一次hash
    //4、

    /**
     * 排序，数组对比
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {

        if(s.length()!=t.length()) {
            return false;
        }

        char[] x = s.toCharArray();
        char[] y = t.toCharArray();

        Arrays.sort(x);
        Arrays.sort(y);

        return Arrays.equals(x,y);
    }

    /**
     * hash计数器
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramCounter(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        // 如果考虑大写字母或者unicode编码字符，可以考虑使用hashmap
        int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            // 统计第一个字符串每个字母出现的次数，只考虑小写字母
            counter[s.charAt(i) - 'a']++;
            // 第二个字符串在计数器中减掉
            counter[t.charAt(i) - 'a']--;
        }
        // 如果计数器没有归0，那么则不是异位词
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * hash计数器2
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramCounter2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        // 用计数器表计算s，然后用t减少计数器表中的每个字母的计数器。
        // 如果在任何时候计数器低于零，我们知道 tt 包含一个不在 ss 中的额外字母，并立即返回 FALSE
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }








    public static void main(String[] args) {

        System.out.println(new L242_ValidAnagram().isAnagram("anagram", "nagaram"));

        System.out.println((int)'A');



    }
}
