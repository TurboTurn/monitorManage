import com.monitor.App;
import com.monitor.dao.alarm.AlarmRuleDao;
import com.monitor.pojo.AlarmRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {App.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//websocket需要server环境，不加会导致创建bean失败
@RunWith(SpringRunner.class)
public class AlarmRuleDaoTest {
	@Autowired
	AlarmRuleDao alarmRuleDao;

	@Test
	public void t1() {
		System.out.println(alarmRuleDao.selectAll());
	}

	@Test
	public void t2() {
		System.out.println(alarmRuleDao.selectById(2));
	}

	@Test
	public void t3() {
		System.out.println(alarmRuleDao.count());
	}

	@Test
	public void t4() {
		System.out.println(alarmRuleDao.listQuery(0, 10));
	}

	@Test
	public void t5() {
		AlarmRule alarmRule = new AlarmRule("tank2", 15, 16, 0.5f, 0.6f, 26, 27, 2, "ys");
		int id = alarmRuleDao.insertAlarmRule(alarmRule);
		System.out.println(alarmRule);
		alarmRule.setTankId("tank3");
		alarmRuleDao.update(alarmRule);
		System.out.println(alarmRule);
	}

	@Test
	public void t6() {
		System.out.println(alarmRuleDao.delete(3));
		;
	}
}
