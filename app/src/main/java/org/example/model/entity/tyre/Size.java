package org.example.model.entity.tyre;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "sizes")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int width;
    private int height;
    private int diameter;

    @ManyToMany(mappedBy = "sizes", fetch = FetchType.LAZY)
    private Set<Tyre> tyres;

    public Size() {
    }

    public Size(int width, int height, int diameter) {
        this.width = width;
        this.height = height;
        this.diameter = diameter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Set<Tyre> getTyres() {
        return tyres;
    }
}