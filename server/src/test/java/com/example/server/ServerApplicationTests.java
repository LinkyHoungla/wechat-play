package com.example.server;

import com.example.server.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private AdminServiceImpl adminService;

	@Test
	void contextLoads() {
		System.out.println(adminService.getAdminList(null,0, 5));
	}

}
