package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import controllers.UpdateProductController;

@Path("/updateproduct")
public class UpdateProductResource {
	@PUT
	@Produces("application/json")
	public Response updateProduct(@FormParam("id") int id ,@FormParam("naam") String naam, @FormParam("prijs") double prijs, @FormParam("omschrijving") String omschrijving) {
		UpdateProductController uPC = new UpdateProductController();
		uPC.updateProduct(id, naam, prijs, omschrijving);
		return Response.ok().build();
	}
}
