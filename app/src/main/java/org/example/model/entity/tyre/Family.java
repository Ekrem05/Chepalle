package org.example.model.entity.tyre;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "family")
    private Set<Tyre> tyres;

    public Family() {}

    public Family(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tyre> getTyres() {
        return tyres;
    }

}