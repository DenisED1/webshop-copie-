package webservices;

import java.sql.Date;
import java.time.LocalDate;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import controllers.SaveKlantController;

@Path("/saveklant")
public class SaveKlantResource {
		@POST
		@Produces("application/json")
		public Response saveKlant(@FormParam("straat") String straat, @FormParam("huisnummer") int huisnummer, @FormParam("toevoeging") String toevoeging, @FormParam("postcode") String postcode, @FormParam("plaats") String plaats, @FormParam("naam") String naam,
				@FormParam("username") String username, @FormParam("password") String password) {
			SaveKlantController sKC = new SaveKlantController();
			Date opendatum = Date.valueOf(LocalDate.now());
			sKC.SaveKlant(straat, huisnummer, toevoeging, postcode, plaats, naam, opendatum, username, password, "Klant");
			return Response.ok().build();
		}
}
