import com.monitor.App;
import com.monitor.MQ.AlarmConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Created by Divo
 * @date 2019/11/19
 */
@SpringBootTest(classes = {App.class})
@RunWith(SpringRunner.class)
public class EmailTest {

    @Autowired
    private AlarmConsumer alarmConsumer;

    @Autowired
    WebApplicationContext applicationContext;

    @Test
    public void etest(){
//
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//
//        String s="hello";
//        alarmConsumer.sendAlarmMail(s);
    }


}
