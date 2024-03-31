import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class GuavaBloomFilterExample {
    public static void main(String[] args) {

        // 创建一个布隆过滤器，预期插入1000个元素，误报率为0.01
        BloomFilter<CharSequence> filter = BloomFilter.create(
                Funnels.stringFunnel(),
                1000,
                0.01);

        // 向布隆过滤器中添加元素
        filter.put("element1");
        filter.put("element2");
        filter.put("element3");

        // 检查元素是否可能存在于布隆过滤器中
        System.out.println(filter.mightContain("element1")); // 应该输出true
        System.out.println(filter.mightContain("element2")); // 应该输出true
        System.out.println(filter.mightContain("element3")); // 应该输出true
        System.out.println(filter.mightContain("someOtherElement")); // 很可能输出false，但也可能是true（误报）
    }
}
