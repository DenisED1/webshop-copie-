package webservices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.CategorieProductController;
import domain.Product;

@Path("/categorieProduct")
public class CategorieProductResource {
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getCategorieProducten(@PathParam("id") int id) {
		ArrayList<Product> producten = CategorieProductController.getCategorieProducten(id);
		if (producten == null || producten.isEmpty()) {
			return Response.status(409, "Geen producten gevonden.").build();
		}
		else {
			return Response.ok(new Gson().toJson(producten)).build();
		}		
	}
}
