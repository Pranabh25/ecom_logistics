package com.springboot.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecom.dto.ResponseMessageDto;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.model.Manager;
import com.springboot.ecom.service.ManagerService;

@RestController
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/manager/add")
	public Manager addUser(@RequestBody Manager user) {
		 System.out.println(user);
		return managerService.insert(user);
	}
	
	@GetMapping("/manager/all")
	public List<Manager> getAllUser() {
		List<Manager> list = managerService.getAllManager();
		return list;
	}
	
	@DeleteMapping("/manager/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id, ResponseMessageDto dto) {
		try {
			managerService.validate(id);
			managerService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} 
		dto.setMsg("manager Deleted");
		return ResponseEntity.ok(dto);
	}
}
	