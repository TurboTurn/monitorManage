package com.monitor.service;

import com.monitor.dao.TableDao;
import com.monitor.pojo.Table;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/19 19:36 星期日
 **/
@Service
public class TableService {
	private TableDao tableDao;

	public TableService(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	/**
	 * 查询全部数据
	 */
	public List<Table> selectAll(){
		return tableDao.selectAll();
	}

	public Table getLast() {
		return tableDao.getLast();
	}

	public List<Table> pageQuery(int pageSize, int pageNo) {
		return tableDao.pageQuery(pageSize,pageNo);
	}

	public int count(){
		return tableDao.count();
	}

}
