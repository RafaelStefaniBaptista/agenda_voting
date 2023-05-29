package br.com.cwi.agenda_voting.domain.dto;

import br.com.cwi.agenda_voting.domain.IAgenda;

public interface VotingResultDto extends IAgenda {
    public int getVotesTotal();
    public int getVotesYes();
    public int getVotesNo();

}
