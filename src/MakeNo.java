/**
 * @Author: Wei Zhao
 * @Description: 输入一个int类型的值N，构造一个长度为N的数组arr并返回。
 * 要求：对任意的i<k<j，都满足arr[i]+arr[j]!=arr[k]*2
 * @Date: Created in 14:20 2020/12/27
 */
public class MakeNo {
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        // 一半长达标来
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        // base ---> 等长奇数达标来
        // base ---> 等长偶数达标来
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 + 1;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }
}
