package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import controllers.SaveProductController;

@Path("/saveproduct")
public class SaveProductResource {
	@POST
	@Produces("application/json")
	public Response saveProduct(@FormParam("naam") String naam, @FormParam("prijs") double prijs, @FormParam("omschrijving") String omschrijving) {
		SaveProductController sPC = new SaveProductController();
		sPC.saveProduct(naam, prijs, omschrijving);
		return Response.ok().build();
	}
}
