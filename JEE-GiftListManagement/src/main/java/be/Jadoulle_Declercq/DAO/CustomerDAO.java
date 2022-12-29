package be.Jadoulle_Declercq.DAO;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.api.client.ClientResponse;

import be.Jadoulle_Declercq.JavaBeans.Customer;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO() {
		super();
	}

	@Override
	public Customer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Customer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Customer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Customer obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Customer authenticate(String email, String password) {
		this.params.add("email", email);
		this.params.add("password", password);
		
		this.response = this.webResource.path("customer").path("auth").accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, this.params);
		
		if(this.response.getStatus() == 200) {
			String apiResponse = this.response.getEntity(String.class);
			System.out.println("apiResponse : " + apiResponse);
			return null;
		}
		else {
			System.out.println("method post invalide : " + response.getStatus());
			return null;
		}
	}

}
