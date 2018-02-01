package com.sementcov.spring.footballteams.controller;

import com.sementcov.spring.footballteams.model.FootballTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "teams")
public interface FootballTeamRestRepository extends CrudRepository<FootballTeam, Long> {
}
