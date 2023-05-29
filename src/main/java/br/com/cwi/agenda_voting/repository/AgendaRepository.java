package br.com.cwi.agenda_voting.repository;


import br.com.cwi.agenda_voting.domain.Agenda;
import br.com.cwi.agenda_voting.domain.dto.VotingResultDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {
    @Query(value = "SELECT a.id, a.created_at as \"createdAt\", a.date_field as \"dateField\", a.numeric_field as \"numericField\", a.seconds_to_expire as \"secondsToExpire\", a.text_field as \"textField\", COUNT(v.is_in_favor) AS \"votesTotal\", COALESCE(SUM(is_in_favor), 0) AS \"votesYes\", COALESCE(SUM(NOT(is_in_favor)), 0) AS \"votesNo\" FROM agenda a LEFT JOIN vote v ON a.id = v.agenda_id WHERE a.id = ?1 GROUP BY a.id", nativeQuery = true)
    Optional<VotingResultDto> getAgendaVotingResult(long agendaId);
}
