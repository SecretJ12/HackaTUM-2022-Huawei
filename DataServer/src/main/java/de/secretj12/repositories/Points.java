package de.secretj12.repositories;

import de.secretj12.entities.Point;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class Points implements PanacheRepository<Point> {
    public List<Point> getInBounds(double lonA, double lonB, double latA, double latB){
        return find("lon >= ?1 and lon <= ?2 and lat >= ?3 and lat <= ?4", lonA, lonB, latA, latB)
                .page(0, 100).list();
    }
}
