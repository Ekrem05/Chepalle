package org.example.model.entity.tyre;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<Tyre> tyres;

    public Type(){};

    public Type(String name) {
        this.name=name;
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
