package com.moviesandchill.video.streaming.service.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HelloController {

	@GetMapping("/")
	public String stream_page(Model model) {
		return "stream_page";
	}

}
