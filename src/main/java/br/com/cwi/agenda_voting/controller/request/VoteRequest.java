package br.com.cwi.agenda_voting.controller.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteRequest {
    private long agendaId;
    private String cpf;
    private boolean isInFavor;

    public VoteRequest(String CPF, boolean isInFavor) {
        this.cpf = CPF;
        this.isInFavor = isInFavor;
    }
}
