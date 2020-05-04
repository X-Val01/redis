
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
public class DemoJedis {
	 private static JedisPool pool = null;
	
	
	
	public String getJedis(String key) {
		Jedis jedis = pool.getResource();
		String str = jedis.hget("hash1", key);
	return str;	
	}
	public void setJedis(String key, String info) {
		Jedis jedis = pool.getResource();
		jedis.hset("hash1", key, info);
	}
	public void clearJedis(String name) {
		Jedis jedis = pool.getResource();
		jedis.del(name);
	}
	public boolean keySearch(String arg0) {
		Jedis jedis = pool.getResource();
			return jedis.hexists("hash1",arg0);
		
	}
	public boolean valueSearch(String script) {
		boolean result= false;
		List<String> list = allValueInJedis();
		Iterator<String> iter = list.iterator();
		for (int i = 0; i < list.size(); i++) {
			if(iter.next().equals(script)) {
				 result = true;
		}			
		}
		return result;
	}
	public boolean DBsize() {
		Jedis jedis = pool.getResource();
		if(jedis.hlen("hash1")== 0 || jedis.hlen("hash1")== null) {
		return true;	
		}
		else {
			return false;
		}
	}
	public Set<String> allKeyInJedis() {
		Jedis jedis = pool.getResource();
		Set<String> set = jedis.hkeys("hash1");
		 return set;

	}
	public void save() {
		Jedis jedis = pool.getResource();
		jedis.bgsave();
		
	}
	public void deleteByKey(String key) {
		Jedis jedis = pool.getResource();
		jedis.hdel("hash1", key);
	}
	public  void swap(Map<? extends String, ? extends String> arg0) {
		Jedis jedis = pool.getResource();
		jedis.hmset("hash1", (Map<String, String>) arg0);
	}
	public  long sizeDB() {
		Jedis jedis = pool.getResource();
		return jedis.hlen("hash1");	
	}
	public List<String> allValueInJedis(){
		Jedis jedis = pool.getResource();
		List<String> list = jedis.hvals("hash1");
		return list;
	}
	public Map<String, String> getSetDB(){
		Jedis jedis = pool.getResource();
		Map<String, String>  set = jedis.hgetAll("hash1");
		return set;
	}
	public void close() {
		Jedis jedis = pool.getResource();
	      if (jedis.isConnected()) {
	            	jedis.disconnect();
	        
	        }
	    }
	public String  test() {
		Jedis jedis = pool.getResource();
		return jedis.memoryDoctor();
	}
}
