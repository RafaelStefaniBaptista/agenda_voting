package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Service
public class SaveAgendaServiceTest {
    @InjectMocks
    SaveAgendaService saveAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTest() {
        Agenda agenda = new Agenda();

        when(agendaRepository.save(agenda)).thenReturn(agenda);
        Agenda result = saveAgendaService.save(agenda);

        assertEquals(agenda, result);
        verify(agendaRepository, times(1)).save(agenda);
    }

}
