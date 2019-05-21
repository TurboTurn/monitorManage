package com.monitor.controller;

import com.monitor.pojo.Table;
import com.monitor.service.TableService;
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
public class TableController {
	private final TableService tableService;

	public TableController(TableService tableService) {
		this.tableService = tableService;
	}

	@RequestMapping("/all")
	public List<Table> getAll(){
		return tableService.selectAll();
	}

	/**
	 * 查询最新一条数据
	 * @return
	 */
	@RequestMapping("/last")
	public Table getLast(){
		return tableService.getLast();
	}

	/**
	 * 查询分页数据
	 * @param ps
	 * @param pno
	 * @return
	 */
	@RequestMapping("/list")
	public List<Table> list(@RequestParam Integer ps, @RequestParam Integer pno){
		return tableService.pageQuery(ps,pno);
	}

	/**
	 * 记录总数
	 * @return
	 */
	@RequestMapping("/count")
	public int count(){
		return tableService.count();
	}
}
