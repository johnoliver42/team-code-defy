package org.TeamCodeDefy.route;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * The class declares root resource and provider classes as well as the root uri path
 */
@ApplicationPath("/")
public class BookListApp extends Application {

    /**
     * The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> routes = new HashSet<>();
        routes.add(BookListRoutes.class);
        routes.add(RootUrl.class);
        return routes;
    }
}
