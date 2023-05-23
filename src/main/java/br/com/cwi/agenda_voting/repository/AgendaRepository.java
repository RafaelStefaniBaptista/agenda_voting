package br.com.cwi.agenda_voting.repository;


import br.com.cwi.agenda_voting.domain.Agenda;
import org.springframework.data.repository.CrudRepository;


public interface AgendaRepository extends CrudRepository<Agenda, Long> {
}
