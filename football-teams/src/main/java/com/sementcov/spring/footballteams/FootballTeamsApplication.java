package com.sementcov.spring.footballteams;

import com.sementcov.spring.footballteams.controller.FootballTeamRestRepository;
import com.sementcov.spring.footballteams.model.FootballTeam;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

import static com.sementcov.spring.footballteams.FootballTeamsApplication.TeamCountryPair.of;


@EnableDiscoveryClient
@SpringBootApplication
public class FootballTeamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballTeamsApplication.class, args);
    }

    @Bean
    CommandLineRunner initializer(FootballTeamRestRepository repository) {
        return args -> {
            Stream.of(of("Real Madrid", "Spain"),
                    of("Barcelona", "Spain"),
                    of("PSG", "France"),
                    of("Man Utd", "England"),
                    of("Chelsea", "England"),
                    of("Juventus", "Italy"),
                    of("Man City", "England"),
                    of("Liverpool", "England"))

                    .forEach(pair -> {
                        FootballTeam team = new FootballTeam(pair.getTeamName());
                        team.setCountryName(pair.getCountryName());

                        repository.save(team);
                    });

            repository.findAll().forEach(System.out::println);
        };
    }

    @Getter
    static class TeamCountryPair {
        private String teamName;
        private String countryName;

        TeamCountryPair(String teamName, String countryName) {
            this.teamName = teamName;
            this.countryName = countryName;
        }

        static TeamCountryPair of(String teamName, String countryName) {
            return new TeamCountryPair(teamName, countryName);
        }
    }

}

@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}

