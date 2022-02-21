package com.mds.back.battle.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegistrationRequest {
	@Email
	@NotEmpty
	public String email;
	@Size(min = 8, max = 15)
	public String password;
}
