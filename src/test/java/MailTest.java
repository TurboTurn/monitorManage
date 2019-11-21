import com.monitor.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : ys
 * @date : 2019/4/26 10:14 星期五
 **/

@SpringBootTest(classes = {App.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//websocket需要server环境，不加会导致创建bean失败
@RunWith(SpringRunner.class)
public class MailTest {

	@Autowired
	JavaMailSenderImpl javaMailSender;
	@Test
	public void test(){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("javamail邮件");
		message.setText("这是测试邮件");
		message.setTo("164497083@qq.com");
		message.setFrom("164497083@qq.com");
		javaMailSender.send(message);
	}
}
