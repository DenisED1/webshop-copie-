package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import controllers.SaveCategorieController;

@Path("/savecategorie")
public class SaveCategorieResource {
	@POST
	@Produces("application/json")
	public Response saveCategorie(@FormParam("naam") String naam, @FormParam("omschrijving") String omschrijving) {
		SaveCategorieController sCC = new SaveCategorieController();
		sCC.saveCategorie(naam, omschrijving);
		return Response.ok().build();
	}
}
