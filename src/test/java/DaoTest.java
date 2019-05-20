import com.monitor.dao.InfluxDao;
import com.monitor.dao.TableDao;
import com.monitor.pojo.Table;
import org.junit.Test;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/20 11:08 星期一
 **/
public class DaoTest {
	private InfluxDao<Table> influxDao = new InfluxDao<>();

	@Test
	public void drop() {
		System.out.println(influxDao.dropMeasurement());
	}

	@Test
	public void pageQuery() {
		List<Table> list = influxDao.pageQuery(5, 1);
		list.forEach(System.out::println);
	}

	@Test
	public void getLast() {
		TableDao tableDao = new TableDao();
		System.out.println(tableDao.getLast());
	}

	public static void main(String[] args) {
		TableDao tableDao = new TableDao();
		System.out.println(tableDao.getLast());
		System.out.println(tableDao.selectAll());
	}


	@Test
	public void save() {
		Table table = new Table();
		table.setHappyDevop(true);
		table.setIdle(15.57);
		table.setRegion("ezhou");
		table.setHostname("localhost");
		influxDao.save(table);//字段不能有空值
	}

	/**
	 * 对象间隔批量插入
	 */
	@Test
	public void manyInsert() throws InterruptedException {
		Table model = new Table();
		model.setHostname("localhost");
		model.setRegion("wuhan");
		int i = 10;
		boolean flag = true;
		while (i > 0) {
			model.setHappyDevop(flag);
			model.setIdle(10 * Math.random());
			influxDao.save(model);
			i--;
			flag = !flag;
//			TimeUnit.SECONDS.sleep(2);
		}
	}
}
