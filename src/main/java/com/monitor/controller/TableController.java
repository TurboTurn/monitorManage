package com.monitor.controller;

import com.monitor.dao.TableDao;
import com.monitor.measurement.Table;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/19 20:35 星期日
 **/
@RestController
@RequestMapping("/table")
@Api(description = "table表相关接口")
public class TableController {
	private final TableDao tableDao;

	public TableController(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	@ApiOperation(value = "查询所有数据")
	@GetMapping("/all")
	public List<Table> getAll(){
		return tableDao.selectAll();
	}

	/**
	 * 查询最新一条数据
	 * @return
	 */
	@ApiOperation(value = "查询最新",notes = "查询最新一条数据")
	@GetMapping("/last")
	public Table getLast(){
		return tableDao.getLast();
	}

	/**
	 * 查询分页数据
	 * @param ps
	 * @param pno
	 * @return
	 */
	@ApiOperation(value = "分页查询",notes = "传入分页参数查询列表")
	@GetMapping("/list")
	public List<Table> list(@RequestParam Integer ps, @RequestParam Integer pno){
		return tableDao.pageQuery(ps, pno);
	}

	/**
	 * 记录总数
	 * @return
	 */
	@ApiOperation(value = "查询总数",notes = "查询表里field字段的总记录数")
	@GetMapping("/count")
	public int count(){
		return tableDao.count();
	}
}
