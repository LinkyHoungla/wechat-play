package com.example.server;

import com.example.server.service.impl.AdminServiceImpl;
import com.example.server.service.impl.CompanionServiceImpl;
import com.example.server.service.impl.PermissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private PermissionServiceImpl permissionService;

	@Autowired
	private CompanionServiceImpl companionService;

	@Test
	void contextLoads() {
		System.out.println(companionService.getGameList("", "SERVER", 1, 10));;
	}

}
