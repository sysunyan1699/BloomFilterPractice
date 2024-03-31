import java.util.BitSet;

public class BitSetExample {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();

        // 设置第0位和第2位为true
        bitSet.set(0);
        bitSet.set(2);

        // 打印BitSet
        System.out.println("BitSet: " + bitSet);

        // 检查特定位的值
        boolean isSet = bitSet.get(1);
        System.out.println("Is bit 1 set? " + isSet);

        // 清除一个位
        bitSet.clear(2);
        System.out.println("After clearing bit 2: " + bitSet);

        // 获取BitSet的“真实”大小
        System.out.println("Actual size of BitSet: " + bitSet.size());

        // 获取BitSet中设置为true的位数
        System.out.println("Number of bits set to true: " + bitSet.cardinality());
    }
}
