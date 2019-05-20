import com.monitor.App;
import com.monitor.dao.TableDao;
import com.monitor.pojo.Table;
import com.monitor.service.TableService;
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
	TableService tableService;
	@Autowired
	TableDao tableDao;

	/**
	 * 查询全部数据
	 */
	@Test
	public void selectAll(){
		List<Table> list1 = tableService.selectAll();
		for (Table model:list1){
			System.out.println(model);
		}
	}
	@Test
	public void selectAll1(){
		List<Table> list1 = tableDao.selectAll();
		for (Table model:list1){
			System.out.println(model);
		}
	}

}
