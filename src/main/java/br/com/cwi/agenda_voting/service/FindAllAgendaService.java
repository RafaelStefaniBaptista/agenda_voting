package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAllAgendaService {
    @Autowired
    private AgendaRepository repository;

    public Iterable<Agenda> findAll() {
        return repository.findAll();
    }

}
