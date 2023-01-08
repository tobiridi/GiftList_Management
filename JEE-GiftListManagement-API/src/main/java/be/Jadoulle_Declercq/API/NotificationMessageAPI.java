package be.Jadoulle_Declercq.API;

import java.time.LocalDate;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

@Path("/notificationMessage")
public class NotificationMessageAPI {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNotificationMessage(@FormParam("title") String title, @FormParam("message") String message,
			@FormParam("notificationDate") String notificationDate, @FormParam("isRead") boolean isRead, 
			@FormParam("customerId") int customerId) {
		
		if (title != null && message != null && notificationDate != null && customerId > 0 && !isRead) {
			if (!title.isBlank() && !message.isBlank() && !notificationDate.isBlank()) {
				
				NotificationMessage newNotification = new NotificationMessage();
				newNotification.setTitle(title);
				newNotification.setMessage(message);
				newNotification.setNotificationDate(LocalDate.parse(notificationDate));
				newNotification.setRead(isRead);
				Customer recipient = new Customer();
				recipient.setId(customerId);
				newNotification.setRecipient(recipient);
				
				if (newNotification.create()) {
					return Response.status(Status.CREATED)
							.header("Location", "/JEE-GiftListManagement-API/api/notificationMessage/" + newNotification.getId())
							.build();
				}
				else {
					return Response.status(Status.SERVICE_UNAVAILABLE).build();
				}
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteNotificationMessage(@PathParam("id") int id) {
		if(id > 0) {
			NotificationMessage deleteMessage = new NotificationMessage();
			deleteMessage.setId(id);
			if(deleteMessage.delete()) {
				return Response.status(Status.NO_CONTENT).build();
			}
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
}
