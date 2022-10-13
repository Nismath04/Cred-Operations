package com.example.sampleProject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sampleProject.entity.UserEntity;
import com.example.sampleProject.model.CustomResponse;
import com.example.sampleProject.model.User;
import com.example.sampleProject.service.UserService;

@RestController
@RequestMapping("user")
public class userController {

	@Autowired
	UserService userService;

	@PostMapping("/saveUser")
	public ResponseEntity<CustomResponse> newUser(@RequestBody User newUser) {
		CustomResponse response = new CustomResponse();

		String saveUser = userService.saveUser(newUser);

		if (saveUser.equals("success")) {
			response.setData("success");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.CREATED);
		} else if (saveUser.equals("Duplicate Entry")) {
			response.setError_des("Duplicate Key");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			response.setError_des(saveUser);

			return new ResponseEntity<CustomResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<CustomResponse> updateUser(@RequestBody User updateuser) {
		CustomResponse response = new CustomResponse();
		List<UserEntity> userList= new ArrayList<>();
		UserEntity user = userService.updateUser(updateuser);
		if (user != null) {
			userList.add(user);
			response.setUser(userList);
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		} else {
			response.setError_des("update not successfful");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}

	}

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<CustomResponse> updateUser(@PathVariable String email) {
		CustomResponse response = new CustomResponse();

		String status = userService.deleteUser(email);
		if (status.equals("Delete")) {
			response.setStatus("success");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		} else {
			response.setStatus("failed");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getusers/byAge")

	public ResponseEntity<CustomResponse> getUserAgeBetween20And30() {
		CustomResponse response = new CustomResponse();

		List <UserEntity> userList = userService.getUserAgeBetween20And30();
		
		if(userList.size()>0) {
			response.setStatus("succcess");
			response.setUser(userList);
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}
		else {
			response.setData("not found");
			response.setStatus("failed");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}
		
	}
	@GetMapping("/login")

	public ResponseEntity<CustomResponse> userLogin(@RequestBody User userLogin) {
		CustomResponse response = new CustomResponse();

		String status=userService.userLogin(userLogin);
		
		if(status!=null) {
			response.setStatus("Login success");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}
		else {
			response.setStatus("Login Failed");
			return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}
		
	}
}
