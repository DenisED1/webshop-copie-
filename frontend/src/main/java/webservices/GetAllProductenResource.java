package webservices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.GetAllProductenController;
import domain.Aanbieding;

@Path("/allproducten")
public class GetAllProductenResource {
	@GET
	@Produces("application/json")
	public Response getAllProducten() {
		ArrayList<Aanbieding> producten = GetAllProductenController.getAllProducten();
		if (producten == null || producten.isEmpty()) {
			return Response.status(409, "Geen producten gevonden.").build();
		}
		else {
			return Response.ok(new Gson().toJson(producten)).build();
		}
	}
}
