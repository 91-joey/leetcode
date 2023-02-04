package org.example.leetcode.problems._9_contest.history.lccup22.personal;

// LCP 61. 气温变化趋势
public class TemperatureTrend {
  public static void main(String[] args) {
    System.out.println(temperatureTrend(new int[]{21, 18, 18, 18, 31}, new int[]{34, 32, 16, 16, 17}));
  }
  
  public static int temperatureTrend(int[] temperatureA, int[] temperatureB) {
    getTrends(temperatureA);
    getTrends(temperatureB);
    int slow = 0;
    int fast = 0;
    int maxDays = 0;
    for (; fast < temperatureA.length - 1; fast++) {
      if (temperatureA[fast] != temperatureB[fast]) {
        maxDays = Math.max(maxDays, fast - slow);
        slow = fast + 1;
      }
    }
    maxDays = Math.max(maxDays, fast - slow);
    return maxDays;
  }
  
  // 妙用 Integer.compare()
  public static int temperatureTrend2(int[] temperatureA, int[] temperatureB) {
    int slow = 0;
    int fast = 0;
    int maxDays = 0;
    for (; fast < temperatureA.length - 1; fast++) {
      if (Integer.compare(temperatureA[fast], temperatureA[fast + 1]) !=
          Integer.compare(temperatureB[fast], temperatureB[fast + 1])) {
        maxDays = Math.max(maxDays, fast - slow);
        slow = fast + 1;
      }
    }
    maxDays = Math.max(maxDays, fast - slow);
    return maxDays;
  }
  
  private static void getTrends(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] < arr[i + 1]) {
        arr[i] = 1;
      } else if (arr[i] == arr[i + 1]) {
        arr[i] = 0;
      } else {
        arr[i] = -1;
      }
    }
  }
}
