package _2_algorithm.dp.leetbook;

/*
 * 「01背包」问题
 * 有 n 件物品和一个容量是 capacity 的背包。
 * 每件物品只能使用一次，每种物品都有0/1件可用。
 * 第 i 件物品的体积是 w_i，价值是 v_i，求将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 */
public class Knapsack01 {
  // ☆☆☆☆☆ 优化一维双重循环dp（索引 i 从 0 开始遍历）
  public int maxValueWithinVolume(int n, int[] size, int[] value, int capacity) {
    int[] f = new int[capacity + 1];
    // todo 统计函数为 min 时，需要初始化
    // Arrays.fill(f, 0x3f3f3f3f);
    // f[0] = 0;
    for (int i = 0; i < n; i++) {
      // 细节，j 从 size[i] 开始遍历
      for (int j = capacity; j >= size[i]; j--) {
        // todo 统计函数：min/max
        f[j] = Math.max(f[j], f[j - size[i]] + value[i]);
      }
    }
    return f[capacity];
  }
}
