package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import webGrude.Browser;
import webGrude.annotations.Page;
import webGrude.annotations.Selector;

// import com.gargoylesoftware.htmlunit.WebClient;
// import com.gargoylesoftware.htmlunit.html.HtmlPage;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	}
