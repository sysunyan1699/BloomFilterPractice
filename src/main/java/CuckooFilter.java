import java.util.Random;

public class CuckooFilter {
    private static final int BUCKET_SIZE = 4; // 桶的大小
    private static final int FILTER_SIZE = 128; // 桶的数量
    private static final int MAX_NUM_KICKS = 500;

    private Integer[][] buckets;
    private Random random;

    public CuckooFilter() {
        this.buckets = new Integer[FILTER_SIZE][BUCKET_SIZE];
        this.random = new Random();
    }

    private int[] getBuckets(Object item, int fingerprint) {
        // 模拟h1, h2的处理
        int hash1 = (int) Murmur3Hash.hash(item);
        // 这里对fingerprint的处理 是应该是另外一个hash 函数
        int hash2 = hash1 ^ (int) Murmur3Hash.hash(fingerprint);
        return new int[]{Math.abs(hash1) % FILTER_SIZE, Math.abs(hash2) % FILTER_SIZE};
    }


    private int getBucket(int fingerprint, int hash1) {
        return hash1 ^ (int) Murmur3Hash.hash(fingerprint);
    }

    private int getFingerprint(Object item) {
        return item.hashCode() & ((1 << 15) - 1); // Just an example to get a fingerprint.
    }


    public boolean insert(Object item) {
        if (item == null) {
            throw new NullPointerException("item can not be null");
        }
        int fingerprint = getFingerprint(item);

        int[] buckets = getBuckets(item, fingerprint);

        // 在两个候选桶中遍历搜索空位
        for (int bucketIndex : buckets) {
            for (int i = 0; i < BUCKET_SIZE; i++) {
                if (this.buckets[bucketIndex][i] == null) {
                    this.buckets[bucketIndex][i] = fingerprint;
                    return true;
                }
            }
        }

        // // 如果两个桶都满了，随机选择一个桶进行踢出 先默认选候选桶中第一个
        int bucketIndex = buckets[0];
        for (int i = 0; i < MAX_NUM_KICKS; i++) {
            int slot = random.nextInt(BUCKET_SIZE);
            // 保存被踢出的指纹
            int temp = this.buckets[bucketIndex][slot];
            // 将元素插入到随机位置
            this.buckets[bucketIndex][slot] = fingerprint;

            // 尝试为被踢出的 fingerprint 找到新的位置
            fingerprint = temp;
            // 被踢出元素的第二个候选桶
            bucketIndex = getBucket(fingerprint, bucketIndex);
            for (int j = 0; j < BUCKET_SIZE; j++) {
                if (this.buckets[bucketIndex][j] == null) {
                    this.buckets[bucketIndex][j] = fingerprint;
                    return true;
                }
            }
        }
        return false; // Failed to insert after MAX_NUM_KICKS.
    }

    public boolean lookup(Object item) {
        if (item == null) {
            throw new NullPointerException("item can not be null");
        }
        int fingerprint = getFingerprint(item);
        int[] buckets = getBuckets(item, fingerprint);

        for (int bucketIndex : buckets) {
            for (int i = 0; i < BUCKET_SIZE; i++) {
                if (this.buckets[bucketIndex][i] != null && this.buckets[bucketIndex][i].equals(fingerprint)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(Object item) {
        if (item == null) {
            throw new NullPointerException("item can not be null");
        }
        int fingerprint = getFingerprint(item);
        int[] buckets = getBuckets(item, fingerprint);

        for (int bucketIndex : buckets) {
            for (int i = 0; i < BUCKET_SIZE; i++) {
                if (this.buckets[bucketIndex][i] != null && this.buckets[bucketIndex][i].equals(fingerprint)) {
                    this.buckets[bucketIndex][i] = null;
                    return true;
                }
            }
        }
        return false;
    }
}


