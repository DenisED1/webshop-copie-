package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.GetKlantGegevensController;
import domain.Klant;

@Path("/klant")
public class GetKlantGegevensResource {
	@GET
	@Path("/gegevens/{username}")
	@Produces("application/json")
	public Response getCategorie(@PathParam("username") String username) {
		Klant klant = GetKlantGegevensController.getKlantGegevens(username);
		if (klant == null) {
			return Response.status(409, "Geen klant gegevens gevonden.").build();
		}
		else {
			return Response.ok(new Gson().toJson(klant)).build();
		}
	}
}
