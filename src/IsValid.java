import java.util.Stack;

/**
 * @Author: Wei Zhao
 * @Description:
 * @Date: Created in 19:34 2021/3/24
 */
public class IsValid {
    public static void main(String[] args) {
        System.out.println(isValid("()]{}"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c) {
                stack.push(')');
            } else if ('[' == c) {
                stack.push(']');
            } else if ('{' == c) {
                stack.push('}');
            } else {
                if (stack.isEmpty() || c != stack.pop()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid2(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }

        return s.length() == 0;
    }
}
