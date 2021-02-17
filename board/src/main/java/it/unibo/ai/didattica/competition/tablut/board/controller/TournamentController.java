package it.unibo.ai.didattica.competition.tablut.board.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import it.unibo.ai.didattica.competition.tablut.board.configuration.TableConfiguration;
import it.unibo.ai.didattica.competition.tablut.board.model.Team;
import it.unibo.ai.didattica.competition.tablut.board.model.Tournament;
import it.unibo.ai.didattica.competition.tablut.board.service.MatchService;
import it.unibo.ai.didattica.competition.tablut.board.service.TeamService;
import it.unibo.ai.didattica.competition.tablut.board.service.TournamentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author a.fontana
 */
@Controller
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

	@NonNull
	private TournamentService tournamentService;

	@NonNull
	private TeamService teamService;
	
	@NonNull
	private MatchService matchService;
	
	@NonNull
	private TableConfiguration tableConfiguration;

	@ModelAttribute("page")
	private String page() {
		return "tournaments";
	}

	@GetMapping
	public String tournaments(@RequestParam(defaultValue = "0", required = false) @Min(0) int page, Model model) {
		if(page < 0)
			page = 0;
		
		Page<Tournament> tournaments = tournamentService.findAll(page, tableConfiguration.getPageSize());
		model.addAttribute("tournaments", tournaments);
		model.addAttribute("tournament", new Tournament());
		
		return "tournaments";
	}
	
	@GetMapping( "/{idTournament}" )
	public String tournament(@PathVariable Long idTournament, Model model) {
		Optional<Tournament> tournamentOpt = tournamentService.findByIdFullDetail(idTournament);
		
		if(!tournamentOpt.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
		
		model.addAttribute("tournament", tournamentOpt.get());
		
		return "tournament";
	}
	
	@PostMapping
	public String tournaments(@ModelAttribute @Valid Tournament tournament, Errors errors, @RequestParam(defaultValue = "0", required = false) @Min(0) int page, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("tournaments", tournamentService.findAll(page, tableConfiguration.getPageSize()));
			model.addAttribute("tournament", tournament);
			return "tournaments";
		}
		
		tournamentService.save(tournament);
		
		return "redirect:/tournaments";
	}
	
	@GetMapping( "/{idTournament}/teams/{idTeam}" )
	public String team(@PathVariable Long idTournament, @PathVariable Long idTeam, Model model) {
		Optional<Tournament> tournamentOpt = tournamentService.findById(idTournament);
		
		if(!tournamentOpt.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
		
		model.addAttribute("tournament", tournamentOpt.get());
		
		Optional<Team> teamOpt = teamService.findByIdFullDetail(idTournament, idTeam);
		
		if(!teamOpt.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
		
		model.addAttribute("team", teamOpt.get());
		
		return "team";
	}
	
	@GetMapping( "/{idTournament}/matches" )
	public String matches(@PathVariable Long idTournament, Model model) {
		Optional<Tournament> tournamentOpt = tournamentService.findById(idTournament);
		
		if(!tournamentOpt.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
		
		model.addAttribute("tournament", tournamentOpt.get());
		
		return "matches";
	}
	
}
