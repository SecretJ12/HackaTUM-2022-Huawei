package de.secretj12.entities;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Point {
    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "lat", nullable = false)
    public Double lat;

    @Column(name = "lon", nullable = false)
    public Double lon;
}
