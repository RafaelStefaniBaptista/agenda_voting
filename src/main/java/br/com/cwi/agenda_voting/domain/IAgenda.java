package br.com.cwi.agenda_voting.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IAgenda {
    public Integer getId();
    public String getTextField();
    public Integer getNumericField();
    public LocalDate getDateField();
    public LocalDateTime getCreatedAt();
    public Long getSecondsToExpire();
}
