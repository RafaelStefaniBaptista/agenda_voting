package br.com.cwi.agenda_voting.controller;

import br.com.cwi.agenda_voting.controller.request.VoteRequest;
import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.domain.Vote;
import br.com.cwi.agenda_voting.domain.dto.VotingResultDto;
import br.com.cwi.agenda_voting.exception.AgendaNotFoundException;
import br.com.cwi.agenda_voting.exception.BaseException;
import br.com.cwi.agenda_voting.exception.UniqueVoteException;
import br.com.cwi.agenda_voting.exception.VotingExpiredException;
import br.com.cwi.agenda_voting.service.FindAllAgendaService;
import br.com.cwi.agenda_voting.service.SaveAgendaService;
import br.com.cwi.agenda_voting.service.SaveVoteService;
import br.com.cwi.agenda_voting.service.VotingResultService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    Logger logger = LoggerFactory.getLogger(AgendaController.class);
    @Autowired
    private SaveAgendaService saveAgendaService;
    @Autowired
    private FindAllAgendaService findAllAgendaService;
    @Autowired
    private VotingResultService votingResultService;
    @Autowired
    private SaveVoteService saveVoteService;

    @PostMapping("/create")
    public ResponseEntity<Agenda> create(@RequestBody Agenda agenda) {
        agenda = saveAgendaService.save(agenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
    }

    @PostMapping("{agendaId}/vote")
    public ResponseEntity<Vote> vote(@PathVariable("agendaId") long agendaId, @RequestBody VoteRequest voteRequest) {
        voteRequest.setAgendaId(agendaId);
        Vote vote = saveVoteService.save(voteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }

    @GetMapping()
    public @ResponseBody Iterable<Agenda> list() {
        return findAllAgendaService.findAll();
    }

    @GetMapping("{agendaId}/voting-result")
    public @ResponseBody VotingResultDto votingResult(@PathVariable("agendaId") long agendaId) {
        return votingResultService.getAgendaVotingResult(agendaId);
    }

    @ExceptionHandler({UniqueVoteException.class, AgendaNotFoundException.class, VotingExpiredException.class})
    public ResponseEntity<String> exceptionHandler(HttpServletRequest req, BaseException ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}