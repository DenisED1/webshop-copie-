package webservices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.CategorieController;
import domain.Categorie;

@Path("/categorie")
public class CategorieResource {
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getCategorie(@PathParam("id") int id) {
		Categorie categorie = CategorieController.getCategorie(id);
		if (categorie == null) {
			return Response.status(409, "Geen categorie gevonden.").build();
		}
		else {
			return Response.ok(new Gson().toJson(categorie)).build();
		}
	}
}
