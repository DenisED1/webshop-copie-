package webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controllers.ProductController;
import controllers.SaveBestellingController;
import domain.Bestellingsregel;

@Path("/savebestelling")
public class SaveBestellingResource {
	@POST
	@Produces("application/json")
	public Response saveBestelling(@FormParam("adresid") int adres, @FormParam("username") String username, @FormParam("regels") List<String> arr) {
		ArrayList<Bestellingsregel> bestellingsregels = new ArrayList<Bestellingsregel>();
		
		JsonParser parser = new JsonParser();
		for(String s : arr) {
			JsonObject o = parser.parse(s).getAsJsonObject();
			bestellingsregels.add(new Bestellingsregel(o.get("aantal").getAsInt(), o.get("prijs").getAsDouble(), ProductController.getProduct(o.get("productid").getAsInt()).getProduct()));
		}
		
		SaveBestellingController.saveBestelling(adres, username, bestellingsregels);

		return Response.ok().build();
	}
}
