package be.Jadoulle_Declercq.DAO;

import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class DAO<T> {
	//API URI
	private static final String BASE_URI = "http://localhost:8080/JEE-GiftListManagement-API/api/";
	
	private ClientConfig config;
	private Client client;
	//the web resource is the API
	protected WebResource webResource;
	//send GET, POST, PUT, DELETE parameters to API
	protected MultivaluedMap<String, String> params;
	//translate JSON retrieve by API to JavaBeans
	protected ObjectMapper mapper;
	//get response from API
	protected ClientResponse response;
	
	public DAO() {
		this.config = new DefaultClientConfig();
		this.client = Client.create(this.config);
		this.webResource = this.client.resource(UriBuilder.fromUri(BASE_URI).build());
		this.mapper = new ObjectMapper();
		this.params = new MultivaluedMapImpl();
	}
	
	public abstract T find(int id);
	public abstract ArrayList<T> findAll();
	public abstract boolean create(T obj);
	public abstract boolean update(T obj);
	public abstract boolean delete(T obj);
}
