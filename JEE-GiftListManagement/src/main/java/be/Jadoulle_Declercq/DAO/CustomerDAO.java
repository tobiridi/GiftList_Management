package be.Jadoulle_Declercq.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.jersey.api.client.ClientResponse;

import be.Jadoulle_Declercq.JavaBeans.Customer;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO() {
		super();
	}

	@Override
	public Customer find(int id) {
		String idString = String.valueOf(id);
		Customer customer = null;
		
		this.response = this.webResource.path("customer").path(idString).accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		
		if(this.response.getStatus() == 200) {
			String apiResponse = this.response.getEntity(String.class);
			
			try {
				JSONObject json = new JSONObject(apiResponse);
				//add LocalDate parsing
				this.mapper.registerModule(new JavaTimeModule());
				customer = this.mapper.readValue(json.toString(), Customer.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return customer;
	}

	@Override
	public ArrayList<Customer> findAll() {
		ArrayList<Customer> allCustomers = null;
		
		this.response = this.webResource.path("customer").accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		
		if(this.response.getStatus() == 200) {
			String apiResponse = this.response.getEntity(String.class);
			allCustomers = new ArrayList<>();
			try {
				JSONArray json = new JSONArray(apiResponse);
				
				//add LocalDate parsing
				this.mapper.registerModule(new JavaTimeModule());
				for (int i = 0; i < json.length(); i++) {
					JSONObject objectJson = json.getJSONObject(i);
					Customer customer = this.mapper.readValue(objectJson.toString(), Customer.class);
					allCustomers.add(customer);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allCustomers;
	}

	@Override
	public boolean create(Customer obj) {
		this.params.add("email", obj.getEmail());
		this.params.add("password", obj.getPassword());
		this.params.add("firstname", obj.getFirstname());
		this.params.add("lastname", obj.getLastname());
		
		this.response = this.webResource.path("customer").accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, this.params);
		
		if(this.response.getStatus() == 201) {
			//get header location
			URI apiLocation = this.response.getLocation();
			if(apiLocation != null) {
				System.out.println("apiResponse : " + apiLocation.getPath());
			}
			return true;
		}
		
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
		Customer customerLog = null;
		this.params.add("email", email);
		this.params.add("password", password);
		
		this.response = this.webResource.path("customer").path("auth").accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, this.params);
		
		if(this.response.getStatus() == 200) {
			String apiResponse = this.response.getEntity(String.class);
			
			try {
				JSONObject json = new JSONObject(apiResponse);
				//add LocalDate parsing
				this.mapper.registerModule(new JavaTimeModule());
				customerLog = this.mapper.readValue(json.toString(), Customer.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return customerLog;
	}

}
