package be.Jadoulle_Declercq.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.jersey.api.client.ClientResponse;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

public class GiftListDAO extends DAO<GiftList> {

	public GiftListDAO() {
		super();
	}
	
	@Override
	public GiftList find(int id) {
		String idGiftList = String.valueOf(id);
		GiftList list = null;
		
		this.response = this.webResource.path("giftList").path(idGiftList)
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		
		if(this.response.getStatus() == 200) {
			String apiResponse = this.response.getEntity(String.class);
			
			try {
				//create giftList
				JSONObject json = new JSONObject(apiResponse);
				//add LocalDate parsing
				this.mapper.registerModule(new JavaTimeModule());
				list = this.mapper.readValue(json.toString(), GiftList.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
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
		String id = String.valueOf(obj.getId());
		JSONObject jsonObject = new JSONObject(obj);
		
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
