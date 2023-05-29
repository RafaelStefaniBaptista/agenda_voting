package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.domain.dto.VotingResultDto;
import br.com.cwi.agenda_voting.exception.AgendaNotFoundException;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Service
public class VotingResultServiceTest {
    @InjectMocks
    VotingResultService votingResultService;

    @Mock
    AgendaRepository agendaRepository;

    @Mock
    VotingResultDto votingResultDto;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAgendaVotingResultSuccessfulTest() {
        long agendaId = 1;

        when(agendaRepository.getAgendaVotingResult(agendaId)).thenReturn(Optional.of(votingResultDto));
        VotingResultDto result = votingResultService.getAgendaVotingResult(agendaId);

        assertEquals(votingResultDto, result);
        verify(agendaRepository, times(1)).getAgendaVotingResult(agendaId);
    }

    @Test
    public void getAgendaVotingResultNotFoundTest() {
        long agendaId = 1;

        when(agendaRepository.getAgendaVotingResult(agendaId)).thenThrow(new AgendaNotFoundException());
        assertThrows(AgendaNotFoundException.class, () -> {
            votingResultService.getAgendaVotingResult(agendaId);
        });
    }
}
