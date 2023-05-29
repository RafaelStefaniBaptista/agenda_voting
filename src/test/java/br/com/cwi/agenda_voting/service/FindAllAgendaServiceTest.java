package br.com.cwi.agenda_voting.service;

import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindAllAgendaServiceTest {
    @InjectMocks
    FindAllAgendaService findAllAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest()
    {
        List<Agenda> agendaList = new ArrayList<>();
        Agenda agendaOne = new Agenda();
        Agenda agendaTwo = new Agenda();
        Agenda agendaThree = new Agenda();

        agendaList.add(agendaOne);
        agendaList.add(agendaTwo);
        agendaList.add(agendaThree);

        when(agendaRepository.findAll()).thenReturn(agendaList);
        Iterable<Agenda> resultIterable = findAllAgendaService.findAll();
        List<Agenda> resultList = new ArrayList<>();

        resultIterable.forEach(resultList::add);

        assertEquals(3, resultList.size());
        verify(agendaRepository, times(1)).findAll();
    }
}
