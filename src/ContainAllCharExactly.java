import java.util.Arrays;

/**
 * @Author: Wei Zhao
 * @Description: 给定长度为m的字符串aim，以及一个长度为n的字符串str，能否在str中找到一个长度为m的连续子串，
 * 使得这个字串刚好由aim的m个字符组成，顺序无所谓，返回任意满足条件的一个字串的起始位置，未找到返回-1。
 * @Date: Created in 21:45 2020/12/21
 */
public class ContainAllCharExactly {
    public static void main(String[] args) {
        System.out.println(isSameSource("abbc", "cabb"));
    }

    public static int containExactly1(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] aim = a.toCharArray();
        Arrays.sort(aim);
        String aimSort = String.valueOf(aim);
        for (int i = 0; i < s.length(); i++) {  // 枚举每一个字符串的开头
            // 0-0 0-1 0-2 0-N-1
            // 1-1 1-2 1-3 1-N-1
            for (int j = i; j < s.length(); j++) {
                char[] cur = s.substring(i, j + 1).toCharArray();
                Arrays.sort(cur);
                String curSort = String.valueOf(cur);
                if (curSort.equals(aimSort)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean isSameSource(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 0~255
        int[] count = new int[256];
        for (int i = 0; i < str1.length; i++) {
            count[str1[i]]++;
        }
        for (int i = 0; i < str2.length; i++) {
            if (count[str2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static int containExactly2(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] aim = a.toCharArray();
        for (int i = 0; i < str.length - aim.length; i++) {
            if (isCountEqual(str, i, aim)) {
                return i;
            }
        }
        return -1;
    }

    // 假设aim的长度为M，str[i...一共取M个字符]
    private static boolean isCountEqual(char[] str, int i, char[] aim) {
        int[] count = new int[256];
        for (int j = 0; j < aim.length; j++) {
            count[aim[j]]++;
        }
        for (int j = 0; j < aim.length; j++) {
            if (count[str[i + j]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static int containExactly3(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] aim = a.toCharArray();
        int[] count = new int[256];
        for (int i = 0; i < aim.length; i++) {
            count[aim[i]]++;
        }
        int M = aim.length;
        char[] str = s.toCharArray();
        int inValidTimes = 0;
        int R = 0;  // 右窗口边界
        // 先让窗口拥有M个字符
        for (; R < M; R++) {
            if (count[str[R]]-- <= 0) {
                inValidTimes++;
            }
        }
        // [0...M-1]，第一次形成了长度为M的窗口
        for (; R < str.length; R++) {
            if (inValidTimes == 0) {
                return R - M;
            }
            if (count[str[R]]-- <= 0) {
                inValidTimes++;
            }
            if (count[str[R]]++ < 0) {
                inValidTimes--;
            }
        }
        return inValidTimes == 0 ? R - M : -1;
    }
}
