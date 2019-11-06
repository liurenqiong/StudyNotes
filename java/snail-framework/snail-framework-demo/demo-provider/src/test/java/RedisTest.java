import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snail.framework.demo.DemoApplication;
import com.snail.framework.redis.data.IRedisTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RedisTest {
	
	@Autowired
	private IRedisTemplate snailRedisTemplate;

	@Test
	public void testRedis() {
		
		snailRedisTemplate.saveOrUpdate("2323", 23232);
	}
	
}
