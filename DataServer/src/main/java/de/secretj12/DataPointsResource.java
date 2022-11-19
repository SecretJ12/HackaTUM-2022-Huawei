package de.secretj12;

import de.secretj12.entities.Point;
import de.secretj12.repositories.Points;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/points")
public class DataPointsResource {

    @Inject
    private Points points;

    @GET
    @Path("/between")
    @Transactional
    public List<Point> getPointsBetween(
            @QueryParam("lonA") double lonA, @QueryParam("latA") double latA,
            @QueryParam("lonB") double lonB, @QueryParam("latB") double latB) {
        return points.getInBounds(lonA, lonB, latA, latB);
    }

    @POST
    @Path("/add")
    @Transactional
    public String addPoint(@QueryParam("id") long id, @QueryParam("lon") double lon, @QueryParam("lat") double lat) {
        Point p = new Point();
        p.id = id;
        p.lon = lon;
        p.lat = lat;
        points.persist(p);
        return "added";
    }
}
