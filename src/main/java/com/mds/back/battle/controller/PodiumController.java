package com.mds.back.battle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mds.back.battle.configuration.settings.BattleSettings;

@Controller
public class PodiumController {

	@Autowired
	private BattleSettings settings;
	
	@GetMapping("/podium")
	public String get(Model model) {
		model.addAttribute("settings", settings);
		return "podium";
	}
	
}
