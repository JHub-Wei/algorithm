import java.util.ArrayList;

/**
 * @Author: Wei Zhao
 * @Description: 已知一个搜索二叉树后序遍历的数组posArr，请重建出整棵树，返回新建树的头节点
 * @Date: Created in 22:06 2020/12/20
 */
public class PosArrayToBst {
    static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    // 目前，我们在使用posArr[L...R]这些数字，来建树，搜索二叉树（Binary Search Tree）
    // 建出的每一个节点都连好，然后把整棵树的头节点返回
    public static Node posArrayToBST1(int[] posArr) {
        // 0~N-1
        return process1(posArr, 0, posArr.length - 1);
    }

    private static Node process1(int[] posArr, int L, int R) {
        if (L > R) {
            return null;
        }
        Node head = new Node(posArr[R]);
        if (L == R) {
            return head;
        }
        // L < R，[L...R-1] 找到比posArr[R]小的数
        int M = L - 1; // 为了兼顾左边数都是小于根节点和都是大于根节点这种情况
        for (int i = L; i < R; i++) {
            if (posArr[i] < posArr[R]) {
                M = i;
            }
        }
        // [L...M] <    [M+1...R-1] >
        head.left = process1(posArr, L, M);
        head.right = process1(posArr, M + 1, R - 1);
        return head;    // 时间复杂度O(N²)
    }

    // 用二分找边界
    private static Node process2(int[] posArr, int L, int R) {
        if (L > R) {
            return null;
        }
        Node head = new Node(posArr[R]);
        if (L == R) {
            return head;
        }
        // L < R，[L...R-1] 找到比posArr[R]小的数
        int M = L - 1;
        int left = L;
        int right = R - 1;
        while (left <= right) {
            // mid = (L + R)/2,防止 L+R 越界
            int mid = left + (right - left) >> 1;
            if (posArr[mid] < posArr[R]) {
                M = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // [L...M] <    [M+1...R-1] >
        head.left = process1(posArr, L, M);
        head.right = process1(posArr, M + 1, R - 1);
        return head;    // 时间复杂度O(N²)
    }

    // for test，最多有N层
    public static Node generateRandomBST(int min, int max, int N) {
        if (min > max) {
            return null;
        }
        return createTree(min, max, 1, N);
    }

    public static Node createTree(int min, int max, int level, int N) {
        if (min > max || level > N) {
            return null;
        }
        Node head = new Node(random(min, max));
        head.left = createTree(min, head.val - 1, level + 1, N);
        head.right = createTree(min, head.val + 1, level + 1, N);
        return head;
    }

    public static int random(int min, int max) {
        return min + (int) Math.random() * (max - min + 1);
    }

    public static void pos(Node head, ArrayList<Integer> posList) {
        if (head != null) {
            pos(head.left, posList);
            pos(head.right, posList);
            posList.add(head.val);
        }
    }

    public static int[] getBstPosArray(Node head) {
        ArrayList<Integer> posList = new ArrayList<>();
        pos(head, posList);
        int[] ans = new int[posList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = posList.get(i);
        }
        return ans;
    }

    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return false;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        return head1.val == head2.val && isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree: ");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(space);
        }
        return builder.toString();

    }
}
