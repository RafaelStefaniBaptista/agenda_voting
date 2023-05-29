package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.domain.dto.VotingResultDto;
import br.com.cwi.agenda_voting.exception.AgendaNotFoundException;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingResultService {
    @Autowired
    private AgendaRepository repository;

    public VotingResultDto getAgendaVotingResult(long agendaId) {
        return repository.getAgendaVotingResult(agendaId).orElseThrow(AgendaNotFoundException::new);
    }
}
