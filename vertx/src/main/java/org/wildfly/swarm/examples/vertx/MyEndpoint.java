package org.wildfly.swarm.examples.vertx;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.vertx.resourceadapter.VertxConnection;
import io.vertx.resourceadapter.VertxConnectionFactory;

/**
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
@Path("/my")
@Stateless
public class MyEndpoint {

    @Resource(mappedName = "java:/eis/VertxConnectionFactory")
    VertxConnectionFactory connectionFactory;

    @GET
    @Produces("text/plain")
    public Response doGet() throws Exception {
        try(VertxConnection vertxConnection = connectionFactory.getVertxConnection()) {
            vertxConnection.vertxEventBus().send("tacos","A message");
        }
        return Response.ok("OK!").build();
    }
}