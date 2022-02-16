package webservices;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/deletecategorie")
public class DeleteCategorieResource {
	@DELETE
	@Produces("application/json")
	public Response deleteCategorie(@PathParam("id") int id) {
		DeleteProductResource dPR = new DeleteProductResource();
		dPR.deleteProduct(id);
		return Response.ok().build();
	}
}
