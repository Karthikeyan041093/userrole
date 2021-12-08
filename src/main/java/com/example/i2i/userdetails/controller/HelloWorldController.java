package com.example.i2i.userdetails.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

	@RequestMapping(value = "/world", method = RequestMethod.GET)
	public String helloWorld() {
		return "Hai this is karthikeyan !!!!!";
	}
}
