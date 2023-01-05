package be.Jadoulle_Declercq.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

import be.Jadoulle_Declercq.JavaBeans.GiftList;

public class GiftListDAO extends DAO<GiftList> {

	public GiftListDAO() {
		super();
	}
	
	@Override
	public GiftList find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GiftList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(GiftList obj) {
		this.params.add("type", obj.getType());
		if(obj.getDeadLine() != null) {
			this.params.add("deadLine", obj.getDeadLine().toString());
		}
		this.params.add("isActive", String.valueOf(obj.isActive()));
		this.params.add("id_customer", String.valueOf(obj.getOwnerList().getId()));
		
		this.response = this.webResource.path("giftList").accept(MediaType.APPLICATION_JSON)
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
	public boolean update(GiftList obj) {
		JSONObject jsonObject = new JSONObject(obj);
		String id = String.valueOf(obj.getId());
		
		this.response = this.webResource.path("giftList").path(id)
				.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, jsonObject.toString());
		
		return this.response.getStatus() == 204;
	}

	@Override
	public boolean delete(GiftList obj) {
		String idString = String.valueOf(obj.getId());
		
		this.response = this.webResource.path("giftList").path(idString).accept(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, this.params);

		return this.response.getStatus() == 204;
	}

}
