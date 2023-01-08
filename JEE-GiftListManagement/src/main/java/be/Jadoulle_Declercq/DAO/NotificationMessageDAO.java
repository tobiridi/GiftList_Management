package be.Jadoulle_Declercq.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;

import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public class NotificationMessageDAO extends DAO<NotificationMessage> {

	public NotificationMessageDAO() {
		super();
	}
	
	@Override
	public NotificationMessage find(int id) {
		return null;
	}

	@Override
	public ArrayList<NotificationMessage> findAll() {
		return null;
	}

	@Override
	public boolean create(NotificationMessage obj) {
		this.params.add("title", obj.getTitle());
		this.params.add("message", obj.getMessage());
		this.params.add("notificationDate", String.valueOf(obj.getNotificationDate()));
		this.params.add("isRead", String.valueOf(obj.isRead()));
		this.params.add("customerId", String.valueOf(obj.getRecipient().getId()));
		
		this.response = this.webResource.path("notificationMessage").accept(MediaType.APPLICATION_JSON)
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
	public boolean update(NotificationMessage obj) {
		return false;
	}

	@Override
	public boolean delete(NotificationMessage obj) {
		String idString = String.valueOf(obj.getId());
		
		this.response = this.webResource.path("notificationMessage").path(idString)
				.accept(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, this.params);
		
		return this.response.getStatus() == 204;
	}
}
