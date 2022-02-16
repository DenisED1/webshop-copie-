package webservices;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import controllers.DeleteProductController;

@Path("/deleteproduct/{id}")
public class DeleteProductResource {
		@DELETE
		@Produces("application/json")
		public Response deleteProduct(@PathParam("id") int id) {
			DeleteProductController dPC = new DeleteProductController();
			dPC.deleteProduct(id);
			return Response.ok().build();
		}
}
