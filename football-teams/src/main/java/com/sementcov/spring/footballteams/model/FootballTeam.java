package com.sementcov.spring.footballteams.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity
@Data
public class FootballTeam extends AbstractEntity {

    private String countryName;

    public FootballTeam(String name) {
        this.name = name;
    }
}
