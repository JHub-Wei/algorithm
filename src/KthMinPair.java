import java.util.Arrays;

/**
 * @Author: Wei Zhao
 * @Description: 长度为N的数组arr，一定可以组成N²个数值对
 * 给定一个数组arr，和整数k，返回第k小的数值对
 * @Date: Created in 15:15 2020/12/27
 */
public class KthMinPair {
    public static int[] kthMinPair(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        Arrays.sort(arr);
        // 第k小的数值对，一维数字
        int firstNum = arr[(k - 1) / N];

        int lessFirstNumSize = 0;    // 数出比firstNum小的数有几个
        int firstNumSize = 0;    // 数出等于firstNum的数有几个
        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            } else {
                firstNumSize++;
            }
        }
        int rest = k - lessFirstNumSize * N;
        return new int[]{firstNum, arr[(rest - 1) / firstNumSize]};
    }

    public static void main(String[] args) {
        System.out.println(39 / 8);
    }

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
