package it.unibo.ai.didattica.competition.tablut.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author a.fontana
 */
@Controller
@RequestMapping( "/live-match" )
public class LiveMatchController {

	@ModelAttribute( "page" )
	private String page() {
		return "live-match";
	}
	
	@GetMapping
	public String liveMatch() {
		return "live-match";
	}
}
