import com.monitor.App;
import com.monitor.pojo.AlarmRule;
import com.monitor.service.AlarmRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {App.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//websocket需要server环境，不加会导致创建bean失败
@RunWith(SpringRunner.class)
public class AlarmRuleServiceTest {
	@Autowired
	AlarmRuleService alarmRuleService;

	@Test
	public void t1() {
		System.out.println(alarmRuleService.selectAll());
	}

	@Test
	public void t2() {
		System.out.println(alarmRuleService.selectById(2));
	}

	@Test
	public void t3() {
		System.out.println(alarmRuleService.count());
	}

	@Test
	public void t4() {
		System.out.println(alarmRuleService.listQuery(1, 10));
	}

	@Test
	public void t5() {
		AlarmRule alarmRule = new AlarmRule("tank2", 15, 16, 0.5f, 0.6f, 26, 27, 2, "ys");
		int id = alarmRuleService.insertAlarmRule(alarmRule);
		System.out.println(alarmRule);
		alarmRule.setTankId("tank3");
		alarmRuleService.update(alarmRule);
		System.out.println(alarmRule);
		System.out.println(alarmRuleService.delete(alarmRule.getId()));
		;
	}
}
