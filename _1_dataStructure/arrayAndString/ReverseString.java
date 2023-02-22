package _1_dataStructure.arrayAndString;

// 344. 反转字符串
public class ReverseString {
  //    1.单指针
  public void reverseString1(char[] s) {
    int length = s.length;
    for (int i = 0; i < length / 2; i++) {
      char tmp = s[i];
      s[i] = s[length - 1 - i];
      s[length - 1 - i] = tmp;
    }
  }
  
  //    2.双指针(while)
  public void reverseString2(char[] s) {
    int i = 0;
    int j = s.length - 1;
    while (i < j) {
      char tmp = s[i];
      s[i] = s[j];
      s[j] = tmp;
      i++;
      j--;
    }
  }
  
  //    2.双指针(for)
  public void reverseString3(char[] s) {
    for (int i = 0, j = s.length - 1; i < j; i++, j--) {
      char tmp = s[i];
      s[i] = s[j];
      s[j] = tmp;
    }
  }
}
