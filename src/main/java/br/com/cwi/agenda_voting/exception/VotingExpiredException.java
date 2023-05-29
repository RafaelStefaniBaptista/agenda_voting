package br.com.cwi.agenda_voting.exception;

import org.springframework.http.HttpStatus;

public class VotingExpiredException extends BaseException {
    public VotingExpiredException() {
        super("The voting has expired", HttpStatus.BAD_REQUEST);
    }
}
