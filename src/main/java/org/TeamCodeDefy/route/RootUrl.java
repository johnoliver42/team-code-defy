package org.TeamCodeDefy.route;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/")
public class RootUrl {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * GET to the root URL of the API
     * @return Response
     */
    @GET
    @Produces("text/plain")
    public Response getIntroduction() {
        // Forward the user to the url https://github.com/johnoliver42/team-code-defy
        UriBuilder uriBuilder = UriBuilder.fromUri("https://github.com/johnoliver42/team-code-defy");
        Link link = Link.fromUriBuilder(uriBuilder).rel("github").build();
        return Response.temporaryRedirect(link.getUri()).status(300).build();
    }
}
