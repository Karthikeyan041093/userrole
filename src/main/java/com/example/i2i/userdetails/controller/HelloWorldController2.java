package com.example.i2i.userdetails.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class HelloWorldController2 {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String helloWorld() {
		return "Hai this is kumaran !!!!!";
	}
}
