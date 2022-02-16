package webservices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.GetAllCategorieController;
import domain.Categorie;


@Path("/categorie")
public class GetAllCategorieResource {
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllCategorieNamen() {
		ArrayList<Categorie> categorieën = GetAllCategorieController.getAllCategorieNamen();
		if (categorieën == null || categorieën.isEmpty()) {
			return Response.status(409, "Geen categorie gevonden.").build();
		}
		else {
			return Response.ok(new Gson().toJson(categorieën)).build();
		}
	}
}
