package com.mds.back.battle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab")
public class NewLabController {

	@Secured({ "ROLE_ALIEN", "ROLE_NERD" })
	@GetMapping
	public ResponseEntity<String> getAll() {
		return ResponseEntity.ok("hey");
	}
}
