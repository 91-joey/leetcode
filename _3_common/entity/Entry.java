package _3_common.entity;

public class Entry<K, V> {
  public K key;
  public V value;
  
  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }
  
  public K getKey() {
    return key;
  }
  
  public void setKey(K key) {
    this.key = key;
  }
  
  public V getValue() {
    return value;
  }
  
  public void setValue(V value) {
    this.value = value;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Entry<?, ?> entry = (Entry<?, ?>) o;
    
    if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
    return value != null ? value.equals(entry.value) : entry.value == null;
  }
  
  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
  
  @Override
  public String toString() {
    return "Entry{" +
        "key=" + key +
        ", value=" + value +
        '}';
  }
}
