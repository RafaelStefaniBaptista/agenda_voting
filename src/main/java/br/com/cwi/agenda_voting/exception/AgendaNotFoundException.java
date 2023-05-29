package br.com.cwi.agenda_voting.exception;

import org.springframework.http.HttpStatus;

public class AgendaNotFoundException extends BaseException {
    public AgendaNotFoundException() {
        super("Agenda Not Found", HttpStatus.NOT_FOUND);
    }
}
