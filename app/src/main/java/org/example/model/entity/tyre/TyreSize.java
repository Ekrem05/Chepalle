package org.example.model.entity.tyre;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "sizes")
public class TyreSize {

    @Id
    private Long id;

    private int width;
    private int height;
    private int diameter;

    public TyreSize() {
    }

    public TyreSize(int width, int height, int diameter) {
        this.width = width;
        this.height = height;
        this.diameter = diameter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}