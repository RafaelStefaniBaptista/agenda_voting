package br.com.cwi.agenda_voting.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Agenda implements IAgenda {
    final static long DEFAULT_SECONDS_TO_EXPIRE = 60;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String textField;
    private Integer numericField;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateField;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long secondsToExpire = DEFAULT_SECONDS_TO_EXPIRE;
}
