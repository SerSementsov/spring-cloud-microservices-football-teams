package com.sementcov.spring.footballteams.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@ToString
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
}
