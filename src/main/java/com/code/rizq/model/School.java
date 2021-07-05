package com.code.rizq.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    String name;
    String address;
    String principleName;

    public School() {
    }

    public School(Integer id, String name, String address, String principleName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.principleName = principleName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPrincipleName() {
        return principleName;
    }
}
