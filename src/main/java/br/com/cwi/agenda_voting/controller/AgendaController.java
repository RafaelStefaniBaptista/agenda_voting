package br.com.cwi.agenda_voting.controller;

import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.service.FindAllAgendaService;
import br.com.cwi.agenda_voting.service.SaveAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    private SaveAgendaService saveAgendaService;
    @Autowired
    private FindAllAgendaService findAllAgendaService;

    @PostMapping("/create")
    public ResponseEntity<Agenda> create(@RequestBody Agenda agenda) {
        agenda = saveAgendaService.save(agenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
    }

    @GetMapping("/vote")
    public String vote() {
        return "Greetings from Spring Boot!";
    }

    public @ResponseBody Iterable<Agenda> list() {
        // This returns a JSON or XML with the users
        return findAllAgendaService.findAll();
    }

}