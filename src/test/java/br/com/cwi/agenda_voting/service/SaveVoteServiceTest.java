package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.controller.request.VoteRequest;
import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.domain.Vote;
import br.com.cwi.agenda_voting.exception.AgendaNotFoundException;
import br.com.cwi.agenda_voting.exception.UniqueVoteException;
import br.com.cwi.agenda_voting.exception.VotingExpiredException;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import br.com.cwi.agenda_voting.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SaveVoteServiceTest {
    @InjectMocks
    @Spy
    SaveVoteService saveVoteService;

    @Mock
    AgendaRepository agendaRepository;

    @Mock
    VoteRepository voteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveVoteSuccessfulTest() {
        Agenda agenda = new Agenda();
        long agendaId = 1;
        String cpf = "218.242.587-04";
        Vote vote = new Vote();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));
        doReturn(false).when(saveVoteService).isVotingExpired(agenda);
        doReturn(false).when(saveVoteService).isVoted(cpf, agendaId);
        when(voteRepository.save(any())).thenReturn(vote);
        Vote result = saveVoteService.save(new VoteRequest(agendaId, cpf, true));

        assertEquals(vote, result);
        verify(voteRepository, times(1)).save(any());
    }

    @Test
    public void saveVoteNotFoundTest() {
        Agenda agenda = new Agenda();
        long agendaId = 1;
        String cpf = "218.242.587-04";
        Vote vote = new Vote();

        when(agendaRepository.findById(agendaId)).thenThrow(new AgendaNotFoundException());
        doReturn(true).when(saveVoteService).isVotingExpired(agenda);
        doReturn(false).when(saveVoteService).isVoted(cpf, agendaId);
        when(voteRepository.save(any())).thenReturn(vote);

        assertThrows(AgendaNotFoundException.class, () -> {
            saveVoteService.save(new VoteRequest(agendaId, cpf, true));
        });
    }

    @Test
    public void saveVoteVotingExpiredTest() {
        Agenda agenda = new Agenda();
        long agendaId = 1;
        String cpf = "218.242.587-04";
        Vote vote = new Vote();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));
        doReturn(true).when(saveVoteService).isVotingExpired(agenda);
        doReturn(false).when(saveVoteService).isVoted(cpf, agendaId);
        when(voteRepository.save(any())).thenReturn(vote);

        assertThrows(VotingExpiredException.class, () -> {
            saveVoteService.save(new VoteRequest(agendaId, cpf, true));
        });
    }

    @Test
    public void saveVoteAlreadyVotedTest() {
        Agenda agenda = new Agenda();
        long agendaId = 1;
        String cpf = "218.242.587-04";
        Vote vote = new Vote();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));
        doReturn(false).when(saveVoteService).isVotingExpired(agenda);
        doReturn(true).when(saveVoteService).isVoted(cpf, agendaId);
        when(voteRepository.save(any())).thenThrow(new DataIntegrityViolationException(any()));
        assertThrows(UniqueVoteException.class, () -> {
            saveVoteService.save(new VoteRequest(agendaId, cpf, true));
        });
    }

    @Test
    public void isVotedTrue() {
        long agendaId = 1;
        String cpf = "218.242.587-04";
        List<Vote> voteList = new ArrayList<>(List.of(new Vote()));

        when(voteRepository.findByCpfAndAgendaId(cpf, agendaId)).thenReturn(voteList);
        boolean result = saveVoteService.isVoted(cpf, agendaId);

        assertTrue(result);
        verify(voteRepository, times(1)).findByCpfAndAgendaId(cpf, agendaId);
    }

    @Test
    public void isVotedFalse() {
        long agendaId = 1;
        String cpf = "218.242.587-04";
        List<Vote> voteList = new ArrayList<>();

        when(voteRepository.findByCpfAndAgendaId(cpf, agendaId)).thenReturn(voteList);
        boolean result = saveVoteService.isVoted(cpf, agendaId);

        assertFalse(result);
        verify(voteRepository, times(1)).findByCpfAndAgendaId(cpf, agendaId);
    }

    @Test
    public void isVotingExpiredTrue() {
        Agenda agenda = new Agenda();
        agenda.setCreatedAt(LocalDateTime.now().minusMinutes(1));
        agenda.setSecondsToExpire(60L);

        boolean result = saveVoteService.isVotingExpired(agenda);

        assertTrue(result);
    }

    @Test
    public void isVotingExpiredFalse() {
        Agenda agenda = new Agenda();
        agenda.setCreatedAt(LocalDateTime.now());
        agenda.setSecondsToExpire(60L);

        boolean result = saveVoteService.isVotingExpired(agenda);

        assertFalse(result);
    }

}
