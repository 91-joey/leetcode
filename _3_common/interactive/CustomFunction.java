package _3_common.interactive;

// This is the custom function interface.
// You should not implement it, or speculate about its implementation
public interface CustomFunction {
  // Returns f(x, y) for any given positive integers x and y.
  // Note that f(x, y) is increasing with respect to both x and y.
  // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
  int f(int x, int y);
}
