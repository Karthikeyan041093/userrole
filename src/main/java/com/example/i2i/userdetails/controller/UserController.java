package com.example.i2i.userdetails.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.i2i.userdetails.dto.UserDTO;
import com.example.i2i.userdetails.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/user")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		try {
			UserDTO _userDTO = userService.saveUser(userDTO);
			return new ResponseEntity<>(_userDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
		try {
			UserDTO _userDTO = userService.updateUser(userDTO);
			return new ResponseEntity<>(_userDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/user/{id}")
	public ResponseEntity<UserDTO> patchUpdateUser(@PathVariable("id") int id,
			@RequestBody Map<Object, Object> userObj) {
		try {
			UserDTO _userDTO = userService.patchUpdateUser(id, userObj);
			return new ResponseEntity<>(_userDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> listOfUsers = new ArrayList<UserDTO>();
		try {
			listOfUsers = userService.getAllUsers();
			if (listOfUsers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(listOfUsers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/users/page", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserDTO>> getAllUsersWithPagination(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "1") Integer pageSize) {
		List<UserDTO> listOfUsers = new ArrayList<UserDTO>();
		try {
			listOfUsers = userService.getAllUsersWithPagination(pageNo, pageSize);
			if (listOfUsers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(listOfUsers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
		UserDTO userData = userService.findById(id);

		if (userData != null) {
			return new ResponseEntity<>(userData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") int id) {

		userService.deleteUserById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
