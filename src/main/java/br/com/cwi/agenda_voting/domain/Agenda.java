package br.com.cwi.agenda_voting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Agenda {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "ID_CAMPO_TEXTO")
    private String textField;
    @Column(name = "ID_CAMPO_NUMERICO")
    private String numericField;
    @Column(name = "ID_CAMPO_DATA")
    private String dateField;
}
