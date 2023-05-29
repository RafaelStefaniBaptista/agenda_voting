package br.com.cwi.agenda_voting.repository;


import br.com.cwi.agenda_voting.domain.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    List<Vote> findByCpfAndAgendaId(String cpf, long agendaId);
}
