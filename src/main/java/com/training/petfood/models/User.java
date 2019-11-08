package com.training.petfood.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usertest")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int salary;

    public User(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
