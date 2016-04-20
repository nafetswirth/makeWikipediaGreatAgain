package connection;

import redis.clients.jedis.Jedis;

/**
 * @author Mohammed Derouich, Stefan Wirth
 */
public class RedisManager {
    private final Jedis redis;

    public RedisManager(final String host) {
        redis = new Jedis(host);
    }

    public Jedis connection() {
        return this.redis;
    }
}
