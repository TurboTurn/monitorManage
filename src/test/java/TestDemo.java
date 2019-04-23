import com.monitor.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : ys
 * @date : 2019/4/15 15:46 星期一
 **/
@SpringBootTest(classes = {App.class})
@RunWith(SpringRunner.class)
@PropertySource("classpath:influxDB.properties")
public class TestDemo {

	@Value("${influx.username}")
	String username;

	@Autowired
	WebApplicationContext applicationContext;
	@Test
	public void fun(){
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String s : names){
			System.out.println(s);
		}
	}

	@Test
	public void test2(){
//		System.out.println(username);
		int a=5;a=~a;
		System.out.println(a);
	}
}
