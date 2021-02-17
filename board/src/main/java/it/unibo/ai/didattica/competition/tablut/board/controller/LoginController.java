package it.unibo.ai.didattica.competition.tablut.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author a.fontana
 */
@Controller
@RequestMapping( "/login" )
public class LoginController {

	@GetMapping
	public String login() {
		return "login";
	}
}
