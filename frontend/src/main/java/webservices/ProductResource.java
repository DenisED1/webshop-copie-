package webservices;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import controllers.ProductController;
import domain.Aanbieding;

// localhost:<port>/frontend/restservices/product/id
@Path("/product")
public class ProductResource {

	@GET
	@Path("{id}")
	@Produces("text/html")
	public String getProduct(@PathParam("id") int id) {

		NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
		DecimalFormat df = (DecimalFormat) nf;
		df.setGroupingUsed(false);

		Aanbieding result = ProductController.getProduct(id);
		if (result != null && result.getProduct() != null) {
			try {
				String response = new String(Files.readAllBytes(Paths.get((new File(
						new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent())
								.getParent())
						+ "/productIdHTML.txt")), StandardCharsets.UTF_8);
				response = String.format(response, result.getProduct().getNaam(), result.getProduct().getNaam(),
						result.getProduct().getNaam().replace(" ", "_"), result.getProduct().getOmschrijving(),
						result.getProduct().getPrijs(),
						result.getAanbiedingsprijs() != 0 ? ("Nu voor: €" + result.getAanbiedingsprijs()) : "",
						result.getAanbiedingsprijs() != 0 ? result.getReclametext() : "", result.getProduct().getId(),
						result.getProduct().getNaam().replace(" ", "_"),
						result.getAanbiedingsprijs() != 0 ? df.format(result.getAanbiedingsprijs())
								: df.format(result.getProduct().getPrijs()));
				return response;
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return "Geen product gevonden";
	}
}
