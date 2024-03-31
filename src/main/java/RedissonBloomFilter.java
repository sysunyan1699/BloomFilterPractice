import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonBloomFilter {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<Integer> bloomFilter = redisson.getBloomFilter("name");
        //初始化布隆过滤器：预计元素为100000000L,误差率为1%
        bloomFilter.tryInit(100000000L,0.01);
        bloomFilter.add(1);

        System.out.println(bloomFilter.contains(1));
        System.out.println(bloomFilter.contains(2));
    }
}
