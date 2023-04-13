import com.mark.Controller.HelloController;
import com.mark.HelloApplication;
import com.mark.mybatis.User.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HelloApplication.class)
public class TestApplication {

    @Autowired
    public HelloController helloController;
    @Test
    public void JunitTest() {
        System.out.println("Hello Test!");
        System.out.println(helloController);
    }

    @Autowired
    public UserMapper userMapperr;

    @Test
    public void TestMybatis()
    {
        System.out.println(userMapperr.selectUserAll());
    }
}
