package com.example.demo.restcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Action;
import com.example.demo.dto.ActionDto;
import com.example.demo.service.ActionService;

@RestController
@RequestMapping("/action")
@CrossOrigin(origins = "*")
public class ActionRestController {
	private final ActionService actionService;
	
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	public ActionRestController(ActionService actionService) {
		this.actionService = actionService;
	}
	
	@PostMapping("")
	public ResponseEntity<Object> saveAction(@RequestBody ActionDto actionDto) throws ParseException{
		Date d = f.parse(actionDto.getTime());
		Long milliseconds = d.getTime();
		Action a = new Action(actionDto.getId(),milliseconds,actionDto.getDevice(),actionDto.getState());
		actionService.saveAction(a);
		return ResponseEntity.ok(a);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getAll(){
		return ResponseEntity.ok(actionService.getAllResult());
	}
	
}
