package br.com.cwi.agenda_voting.domain;

import jakarta.persistence.*;

public class Vote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "ID_AGENDA")
    private Agenda agenda;
    @Column(unique=true)
    private String CPF;

}
