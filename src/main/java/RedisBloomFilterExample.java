import redis.clients.jedis.UnifiedJedis;

import java.util.List;

public class RedisBloomFilterExample {
    public static void main(String[] args) {

        UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");
        //JedisPool pool = new JedisPool("localhost", 6379);
        String res1 = jedis.bfReserve("bikes:models", 0.01, 1000);
        System.out.println(res1); // >>> OK


        boolean res2 = jedis.bfAdd("bikes:models", "Smoky Mountain Striker");
        System.out.println(res2); // >>> True

        boolean res3 = jedis.bfExists("bikes:models", "Smoky Mountain Striker");
        System.out.println(res3); // >>> True

        List<Boolean> res4 = jedis.bfMAdd("bikes:models",
                "Rocky Mountain Racer",
                "Cloudy City Cruiser",
                "Windy City Wippet");
        System.out.println(res4); // >>> [True, True, True]

        List<Boolean> res5 = jedis.bfMExists("bikes:models",
                "Rocky Mountain Racer",
                "Cloudy City Cruiser",
                "Windy City Wippet");
        System.out.println(res5); // >>> [True, True, True]

    }
}

