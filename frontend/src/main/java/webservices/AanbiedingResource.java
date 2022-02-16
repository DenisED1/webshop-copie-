package webservices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.AanbiedingController;
import domain.Aanbieding;

@Path("/aanbieding")
public class AanbiedingResource {

	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllAanbiedingen() {
		ArrayList<Aanbieding> aanbiedingen = AanbiedingController.getAllCurrentAanbiedingen();
//		ArrayList<Aanbieding> aanbiedingen = Aanbieding.getAllAanbiedingen();
		if (aanbiedingen.isEmpty()) {
			return Response.status(409, "Geen aanbiedingen.").build();
		} else {
			String json = new Gson().toJson(aanbiedingen);
			return Response.ok(json).build();
		}
	}
}
