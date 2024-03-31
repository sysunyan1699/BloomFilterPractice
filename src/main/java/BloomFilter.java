import java.util.BitSet;

public class BloomFilter {
    // 位数组大小
    int capacity;

    //映射过程中使用的函数数量
    int numHashFunctions;

    // 位数组
    BitSet bitArray;

    public BloomFilter() {
        this.capacity = 100;
        this.numHashFunctions = 2;
        bitArray = new BitSet(this.capacity);
    }


    public BloomFilter(int capacity, int numHashFunctions) {
        this.capacity = capacity;
        this.numHashFunctions = numHashFunctions;
        bitArray = new BitSet(capacity);
    }

    public void add(Object value) {
        if (value == null) {
            throw new NullPointerException("Value cannot be null.");
        }
        long hash = Murmur3Hash.hash(value);
        // 取低32位
        int hash1 = (int) hash;
        //取高32位
        int hash2 = (int) hash >>> 32;

        //利用单个哈希函数配合位操作的方法来模拟多个哈希函数，从而优化性能和空间使用。
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            bitArray.set(nextHash % bitArray.size());
        }
    }

    public boolean mightContains(Object value) {
        if (value == null) {
            throw new NullPointerException("Value cannot be null.");
        }
        long hash = Murmur3Hash.hash(value);
        // 取低32位
        int hash1 = (int) hash;
        //取高32位
        int hash2 = (int) hash >>> 32;

        //利用单个哈希函数配合位操作的方法来模拟多个哈希函数，从而优化性能和空间使用。
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            if (!bitArray.get(nextHash % bitArray.size())) {
                return false;
            }
        }
        return true;
    }
}
