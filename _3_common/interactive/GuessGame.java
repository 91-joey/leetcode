package _3_common.interactive;

public class GuessGame {
  int pick;
  
  /**
   * @param num
   * @return -1：我选出的数字比你猜的数字小 pick < num;
   * 1：我选出的数字比你猜的数字大 pick > num;
   * 0：我选出的数字和你猜的数字一样
   */
  public int guess(int num) {
    return Integer.compare(pick, num);
  }
}
