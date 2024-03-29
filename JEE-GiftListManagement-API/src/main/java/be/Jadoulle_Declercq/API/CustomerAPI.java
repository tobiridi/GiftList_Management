package be.Jadoulle_Declercq.API;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.Jadoulle_Declercq.JavaBeans.Customer;

@Path("/customer")
public class CustomerAPI {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomer() {
		ArrayList<Customer> allCustomers = Customer.getAll();

		if (allCustomers != null) {
			return Response.status(Status.OK).entity(allCustomers).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id") int id) {
		if(id > 0) {
			Customer customer = Customer.get(id);

			if (customer != null) {
				return Response.status(Status.OK).entity(customer).build();
			}
			else {
				return Response.status(Status.NOT_FOUND).build();
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("auth")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCustomer(@FormParam("email") String email, @FormParam("password") String password) {
		if(email != null && password != null) {
			if(!email.isBlank() && !password.isBlank()) {
				Customer customer = Customer.login(email, password);
				
				if(customer != null) {
					return Response.status(Status.OK).entity(customer).build();
				}
				else {
					return Response.status(Status.NOT_FOUND).build();
				}
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer(@FormParam("email") String email, @FormParam("password") String password, 
			@FormParam("firstname") String firstname, @FormParam("lastname") String lastname) {
		
		if (email != null && password != null && firstname != null && lastname != null) {
			if (!email.isBlank() && !password.isBlank() && !firstname.isBlank() && !lastname.isBlank()) {
				
				Customer newCustomer = new Customer();
				newCustomer.setEmail(email);
				newCustomer.setPassword(password);
				newCustomer.setLastname(lastname);
				newCustomer.setFirstname(firstname);

				if (newCustomer.create()) {
					return Response.status(Status.CREATED)
							.header("Location", "/JEE-GiftListManagement-API/api/customer/" + newCustomer.getId()).build();
				}
				else {
					return Response.status(Status.SERVICE_UNAVAILABLE).build();
				}
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
}
