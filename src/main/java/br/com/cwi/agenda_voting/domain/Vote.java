package br.com.cwi.agenda_voting.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne()
    @JoinColumn()
    private Agenda agenda;
    @Column(unique = true)
    private String cpf;
    private boolean isInFavor;
    public Vote(Agenda agenda, String cpf, boolean isInFavor) {
        this.agenda = agenda;
        this.cpf = cpf;
        this.isInFavor = isInFavor;
    }
}
