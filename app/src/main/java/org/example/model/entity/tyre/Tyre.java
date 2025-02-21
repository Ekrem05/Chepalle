package org.example.model.entity.tyre;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "tyres")
public class Tyre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String model;
    private String description;
    private Long price;

    @Column(name="stripe_id")
    private String stripeId;

    @Column(name="stripe_price_id")
    private String stripePriceId;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne
    @JoinColumn(name="technology_id")
    private Technology technology ;


    @ManyToMany
    @JoinTable(
            name = "tyre_size",
            joinColumns = @JoinColumn(name = "tyre_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> sizes;

    @ManyToMany
    @JoinTable(
            name = "tyre_type",
            joinColumns = @JoinColumn(name = "tyre_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<Type> types;

    public Tyre() {
    }

    public Tyre(String model, String description,
                Long price,
                String stripeId,
                String stripePriceId,
                Family family,
                Season season,
                Technology technology,
                Set<Size> sizes,
                Set<Type> types) {
        this.model = model;
        this.description = description;
        this.price=price;
        this.stripeId=stripeId;
        this.stripePriceId=stripePriceId;
        this.family = family;
        this.season = season;
        this.technology = technology;
        this.sizes = sizes;
        this.types = types;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }
}
