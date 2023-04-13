import com.mark.WebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest(classes = WebApplication.class)
public class WebTest {

    //@Autowired
    //private StringRedisTemplate redisTemplate;

    @Test
    public void testRedisSet()
    {
        //redisTemplate.opsForValue().set("name","Mark");
    }

    @Test
    public void testRedisGet()
    {
        //String name = redisTemplate.opsForValue().get("name");
        //String age = redisTemplate.opsForValue().get("age");
        //System.out.println(name+age);
    }
}
