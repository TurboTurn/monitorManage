package com.monitor.util;

import com.monitor.pojo.Table2;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : ys
 * @date : 2019/4/15 15:49 星期一
 **/
public class InfluxdbBuilder {
	public static InfluxDB createInfluxDB(){
		String url1 = "http://127.0.0.1:8086";
		String url2 = "http://58.49.96.182:8086";
		InfluxDB influxDB = InfluxDBFactory.connect(url1,"root","admin");
		influxDB.setDatabase("ys");
		return influxDB;
	}
	@Test
	public void ping(){
		InfluxDB influxDB = InfluxdbBuilder.createInfluxDB();
		System.out.println(influxDB.ping().toString());
	}

	public static void main(String[] args) {
		InfluxDB influxDB = InfluxdbBuilder.createInfluxDB();
		float i=2000;
		String sql = "insert mytable,host=localhost mytime="+i;
		String sql2 = "insert table1,tag=good field="+i;//不支持
		String sql1 = "select * from table1";
		Query query = new Query(sql1);
		QueryResult result = influxDB.query(query);
		QueryResult.Series series = result.getResults().get(0).getSeries().get(0);
		StringBuilder builder = new StringBuilder();
		builder.append("Series [name=");
		builder.append(series.getName());
		builder.append(", tags=");
		builder.append(series.getTags());
		builder.append(", \ncolumns=");
		builder.append(series.getColumns());
		builder.append("]\n");
		System.out.println(builder.toString());
		for (List list:series.getValues()){
			System.out.println(list);
		}

//		System.out.println(result);
	}

	/**
	 * 查询
	 */
	@Test
	public void select(){
		InfluxDB influxDB = InfluxdbBuilder.createInfluxDB();
		InfluxDBMapper mapper = new InfluxDBMapper(influxDB);
		List<Table2> list = mapper.query(Table2.class);
		for (Table2 model:list){
			System.out.println(model);
		}
	}

	/**
	 * 对象插入
	 */
	@Test
	public void singleInsert(){
		InfluxDB influxDB = InfluxdbBuilder.createInfluxDB();
		InfluxDBMapper mapper = new InfluxDBMapper(influxDB);
		Table2 model = new Table2();
//		model.setTime(Instant.now());
		model.setHostname("localhost");
		model.setHappyDevop(true);
		model.setRegion("wuhan");
		model.setIdle(21.33);
		mapper.save(model);

	}

	@Test
	public void insert() throws InterruptedException {


		String tablename = "table1";
		InfluxDB influxDB = InfluxdbBuilder.createInfluxDB();
		double f = 2036;
		String tag;
		boolean flag = true;
		while (true){
			long s = System.currentTimeMillis();
			Point.Builder builder = Point.measurement(tablename);
			builder.tag("tag",flag?"good":"bad");
			builder.addField("field",f);builder.time(System.currentTimeMillis(), TimeUnit.SECONDS);
			influxDB.write(builder.build());
			long e = System.currentTimeMillis();
//			System.out.println("共耗时"+(e-s)/1000f + "秒");
			System.out.println("插入数据：tag="+(flag?"good":"bad") + " field=" + f + ", 共耗时"+(e-s)/1000d + "秒");
			f+=2;flag=!flag;
			TimeUnit.SECONDS.sleep(2);
		}


	}
	@Test
	public void delayinsert(){
		int arr[] =new int[3];
		int index =0;
		arr[index++]=2;CharSequence c="ff";
		System.out.println(c);
		for(int a : arr)
			System.out.println(a);

	}
}
