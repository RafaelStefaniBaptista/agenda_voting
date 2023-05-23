package br.com.cwi.agenda_voting.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Agenda {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
}
