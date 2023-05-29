package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.controller.request.VoteRequest;
import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.domain.Vote;
import br.com.cwi.agenda_voting.exception.AgendaNotFoundException;
import br.com.cwi.agenda_voting.exception.UniqueVoteException;
import br.com.cwi.agenda_voting.exception.VotingExpiredException;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import br.com.cwi.agenda_voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Service
public class SaveVoteService {
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private VoteRepository repository;

    public Vote save(VoteRequest voteRequest) {
        Agenda agenda = agendaRepository.findById(voteRequest.getAgendaId()).orElseThrow(AgendaNotFoundException::new);
        if (isVotingExpired(agenda)) {
            throw new VotingExpiredException();
        }
        try {
            return repository.save(new Vote(agenda, voteRequest.getCpf(), voteRequest.isInFavor()));
        } catch (DataIntegrityViolationException ex) {
            if (isVoted(voteRequest.getCpf(), voteRequest.getAgendaId())) {
                throw new UniqueVoteException();
            }

            throw ex;
        }
    }

    public boolean isVoted(String cpf, long agendaId) {
        return !repository.findByCpfAndAgendaId(cpf, agendaId).isEmpty();
    }

    public boolean isVotingExpired(Agenda agenda) {
         return LocalDateTime.now().isAfter(agenda.getCreatedAt().plusSeconds(agenda.getSecondsToExpire()));
    }
}
