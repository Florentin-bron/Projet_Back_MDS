package com.mds.back.battle.controller.api;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mds.back.battle.configuration.settings.SecuritySettings;
import com.mds.back.battle.payload.LoginResponse;
import com.mds.back.battle.payload.RegistrationRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private SecuritySettings settings;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/signin")
	public ResponseEntity<String> signIn() {
		return ResponseEntity.ok("Bonjour");
	}

	@GetMapping("/signinin")
	public ResponseEntity<LoginResponse> signinin(@RequestHeader("Authorization") String auth,
			@RequestParam("role") String role) {
		if (auth != null && auth.toLowerCase().startsWith("basic")) {
			String base64Credentials = auth.substring("Basic".length()).trim();
			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded);
			String[] values = credentials.split(":", 2);
			logger.info("username : " + values[0] + " password : " + values[1]);
// return ResponseEntity.ok(values[0] + " et " + values[1]);

			LoginResponse response = new LoginResponse();
			String secrets = settings.getSecret();
			byte[] authBytes = secrets.getBytes(StandardCharsets.UTF_8);
			byte[] encoded = Base64.getEncoder().encode(authBytes);

			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, (int) settings.getExpire());

			response.token = Jwts.builder().setSubject(values[0]).setAudience("http://localhost:8090").setIssuedAt(date)
					.setExpiration(calendar.getTime()).claim("role", role).signWith(SignatureAlgorithm.HS512, encoded)
					.compact();
			response.expire = settings.getExpire();
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/singup")
	public ResponseEntity<String> signUp(@Valid @RequestBody RegistrationRequest request) {
		logger.info("email : " + request.email + " / password" + request.password);
		return ResponseEntity.ok(request.email + " / " + request.password);
	}

}