package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controllers.CheckCredentialsController;
import controllers.GetRoleController;

@Path("/check")
public class CheckCredentialsResource {

	@POST
	@Produces("Application/json")
	public Response checkCredentials(@FormParam("username") String username, @FormParam("password") String password) {
		CheckCredentialsController CCC = new CheckCredentialsController();
		boolean uitkomst = CCC.checkCredentials(username, password);
		String uitkomst1;
		if (uitkomst == false) {
			uitkomst1 = "account bestaat niet";
		}
		else {
			GetRoleController gRC = new GetRoleController();
			uitkomst1 = gRC.getRole(username);
		}
		return Response.ok(new Gson().toJson(uitkomst1)).build();
	}
}
