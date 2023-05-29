package br.com.cwi.agenda_voting.exception;

import org.springframework.http.HttpStatus;

public class UniqueVoteException extends BaseException {
    public UniqueVoteException() {
        super("Votes are unique by CPF", HttpStatus.BAD_REQUEST);
    }
}
