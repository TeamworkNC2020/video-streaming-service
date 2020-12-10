package com.mycompany.video.streaming.service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

public class ClassExample {


@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Hello World!";
	}

}
}
