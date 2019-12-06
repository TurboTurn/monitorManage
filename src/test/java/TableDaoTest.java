import com.monitor.dao.TableDao;
import com.monitor.measurement.Table;
import org.junit.Test;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/20 11:08 星期一
 **/
public class TableDaoTest {
	private TableDao tableDao = new TableDao();
	private static String tankId = "tank1";
	@Test
	public void drop() {
		System.out.println(tableDao.dropMeasurement());
	}

	@Test
	public void pageQuery() {
		List<Table> list = tableDao.pageQuery(5, 1);
		list.forEach(System.out::println);
	}

	@Test
	public void getLast() {
		System.out.println(tableDao.getLast(tankId));
	}

	public static void main(String[] args) {
		TableDao tableDao = new TableDao();
		System.out.println(tableDao.getLast(tankId));
		System.out.println(tableDao.selectAll());
	}


	@Test
	public void save() {
		Table table = new Table();
		tableDao.save(table);//字段不能有空值
	}

}
