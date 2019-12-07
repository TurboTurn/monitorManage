package com.monitor.controller;

import com.monitor.pojo.AlarmRule;
import com.monitor.service.AlarmRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarmRule")
public class AlarmRuleController {
	@Autowired
	AlarmRuleService alarmRuleService;

	@PostMapping("/set")
	public ResultEntity setRule(@RequestBody AlarmRule alarmRule) {
		return ResultEntity.success(alarmRuleService.insertAlarmRule(alarmRule));
	}

	@GetMapping("/id/{id}")
	public ResultEntity getRule(@PathVariable("id") Integer alarmId) {
		return ResultEntity.success(alarmRuleService.selectById(alarmId));
	}

	@GetMapping("/list")
	public ResultEntity getAllRuleList(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		List<AlarmRule> list = alarmRuleService.listQuery(pageNo, pageSize);
		return ResultEntity.success(list);
	}

	@GetMapping("/count")
	public ResultEntity getAllRuleCount() {
		return ResultEntity.success(alarmRuleService.count());
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResultEntity deleteRule(@PathVariable("id") Integer alarmId) {
		return ResultEntity.success(alarmRuleService.delete(alarmId));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResultEntity updateRule(@RequestBody AlarmRule alarmRule) {
		return ResultEntity.success(alarmRuleService.update(alarmRule));
	}

}
