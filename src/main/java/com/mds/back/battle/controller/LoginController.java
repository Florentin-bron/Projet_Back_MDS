package com.mds.back.battle.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mds.back.battle.BattleSettings;
import com.mds.back.battle.domain.User;
import com.mds.back.battle.repository.UserRepository;

@Controller
public class LoginController {
	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private BattleSettings settings;
	
	@Autowired
	private UserRepository users;
	
	@GetMapping("/login")
	public String get(Model model) {
		model.addAttribute("settings", settings);
		return "login";
	}
	
	@PostMapping("/login")
	public String post(@RequestParam String email,
			@RequestParam String password,
			HttpSession session,
			Model model) 
	{
		LOGGER.info("Login =email{} password={}", email, password);
		List<User> match = users.findByEmail(email);
		if(match.size() == 1) {
			User user = match.get(0);
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] digest = md.digest();
				String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
				if(hash.equals(user.hash)) {
					session.setAttribute("user", user);
					return "redirect:/home";
				}
			}catch(NoSuchAlgorithmException e){
				LOGGER.warn("Impossbiel de hacher le mdp en MD5", e);
				return get(model);
			}
		}

		LOGGER.info("Login email={} +password -> refused", email);
		return get(model);
	}
}
