package _9_contest.history.week319;

// 6233. 温度转换
public class T1 {
  public double[] convertTemperature9(double celsius) {
    double[] ans = new double[2];
    ans[0] = celsius + 273.15;
    ans[1] = celsius * 1.80 + 32.00;
    return ans;
  }
  
  public double[] convertTemperature(double celsius) {
    return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
  }
  
}
