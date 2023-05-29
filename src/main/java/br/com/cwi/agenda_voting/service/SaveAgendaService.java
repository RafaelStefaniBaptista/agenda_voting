package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAgendaService {
    @Autowired
    private AgendaRepository repository;

    public Agenda save(Agenda agenda) {
        return repository.save(agenda);
    }

}
