package connection;

import redis.clients.jedis.Jedis;

/**
 * Created by herby on 22/04/15.
 * This could propably be made a singleton managing connetions
 * but it seems to be overkill for the task we are trying to accomplish
 */
public class RedisManager
{
    private final Jedis redis;

    public RedisManager() {
        redis = new Jedis("localhost");
    }

    public Jedis connection() {
        return this.redis;
    }
}
