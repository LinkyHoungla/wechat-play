package com.example.server;

import com.example.server.dao.CompanionDao;
import com.example.server.service.impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private UserServiceImpl service;

	@Test
	void contextLoads() {
		System.out.println(service.getUserList("", "ACTIVE", 1, 10));
		System.out.println(service.getUserList("", "", 1, 10));
		System.out.println(service.getUserList("u", "", 1, 10));
		System.out.println(service.getUserList("u", "ACTIVE", 1, 10));
	}

}
