package be.Jadoulle_Declercq.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;

import be.Jadoulle_Declercq.JavaBeans.Gift;

public class GiftDAO extends DAO<Gift> {

	public GiftDAO() {
		super();
	}
	
	@Override
	public Gift find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Gift> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Gift obj) {
		this.params.add("priority", String.valueOf(obj.getPriority()));
		this.params.add("name", obj.getName());
		this.params.add("description", obj.getDescription());
		this.params.add("averagePrice", String.valueOf(obj.getAveragePrice()));
		this.params.add("link", obj.getLink());
		this.params.add("picture", obj.getPicture());
		this.params.add("idGiftList", String.valueOf(obj.getGiftList().getId()));
		
		this.response = this.webResource.path("gift").accept(MediaType.APPLICATION_JSON)
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
	public boolean update(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
