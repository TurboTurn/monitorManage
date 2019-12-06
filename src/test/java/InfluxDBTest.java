import com.monitor.App;
import com.monitor.dao.alarm.AlarmHistoryDao;
import com.monitor.pojo.AlarmHistory;
import com.monitor.pojo.AlarmRule;
import com.monitor.service.AlarmRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/19 20:03 星期日
 **/
@SpringBootTest(classes = {App.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//websocket需要server环境，不加会导致创建bean失败
@RunWith(SpringRunner.class)
public class InfluxDBTest {


	@Autowired
	private AlarmRuleService alarmService;
	@Autowired
	private AlarmHistoryDao alarmHistoryDao;

	/**
	 * 查询全部数据
	 */
	@Test
	public void selectAll1(){
		List<AlarmRule> list1 = alarmService.selectAll();
		for (AlarmRule model:list1){
			System.out.println(model);
		}
	}

	@Test
	public void insert(){
		AlarmHistory alarmHistory = new AlarmHistory();
		alarmHistory.setAlarmRule("gg");
		alarmHistoryDao.selectByPrimaryKey(1);
		alarmHistoryDao.insertAlarmHistory(alarmHistory);
	}

}
