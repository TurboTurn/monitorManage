package com.monitor.controller;

import com.monitor.pojo.Table;
import com.monitor.service.TableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/19 20:35 星期日
 **/
@RestController
public class TableController {
	private final TableService tableService;

	public TableController(TableService tableService) {
		this.tableService = tableService;
	}

	@RequestMapping("/all")
	public List<Table> getAll(){
		return tableService.selectAll();
	}
	@RequestMapping("/last")
	public Table getLast(){
		return tableService.getLast();
	}
}
