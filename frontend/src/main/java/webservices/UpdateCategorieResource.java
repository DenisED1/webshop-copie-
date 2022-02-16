package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import controllers.UpdateCategorieController;

@Path("/updatecategorie")
public class UpdateCategorieResource {
	@PUT
	@Produces("application/json")
	public Response updateProduct(@FormParam("id") int id ,@FormParam("naam") String naam, @FormParam("prijs") double prijs, @FormParam("omschrijving") String omschrijving) {
		UpdateCategorieController uCC = new UpdateCategorieController();
		uCC.updateCategorie(id, naam, omschrijving);
		return Response.ok().build();
	}
}
