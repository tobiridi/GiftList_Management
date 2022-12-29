package be.Jadoulle_Declercq.API;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.Jadoulle_Declercq.JavaBeans.Customer;

@Path("/customer")
public class CustomerAPI {

	@POST
	@Path("auth")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCustomer(@FormParam("email") String email, @FormParam("password") String password) {
		if(email != null && password != null) {
			//Customer customer = Customer.login(email, password);
			
			//TODO : personal test
			Customer customer = new Customer();
			customer.setId(5);
			customer.setEmail(email);
			customer.setPassword(password);
			
			if(customer != null) {
				return Response.status(Status.OK)
						.entity(customer)
						.build();
			}
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
}
