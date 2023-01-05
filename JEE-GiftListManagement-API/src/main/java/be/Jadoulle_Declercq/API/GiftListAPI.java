package be.Jadoulle_Declercq.API;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

@Path("/giftList")
public class GiftListAPI {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGiftList(@FormParam("type") String type, @FormParam("isActive") boolean isActive, 
			@FormParam("deadLine") String deadLine, @FormParam("id_customer") int idCustomer) {
		
		if(type != null && !type.isBlank() && isActive && idCustomer > 0) {
			GiftList newGiftList = new GiftList();
			newGiftList.setType(type);
			newGiftList.setActive(isActive);
			Customer owner = new Customer();
			owner.setId(idCustomer);
			newGiftList.setOwnerList(owner);
			
			if(deadLine != null) {
				try {
					LocalDate date = LocalDate.parse(deadLine);
					newGiftList.setDeadLine(date);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
				}
			}
			
			if (newGiftList.create()) {
				return Response.status(Status.CREATED)
						.header("Location", "/JEE-GiftListManagement-API/api/giftList/" + newGiftList.getId()).build();
			}
			else {
				return Response.status(Status.SERVICE_UNAVAILABLE).build();
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteGiftList(@PathParam("id") int id) {
		GiftList deleteList = new GiftList();
		deleteList.setId(id);
		if(deleteList.delete()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		
		return Response.status(Status.SERVICE_UNAVAILABLE).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGiftList(@PathParam("id") int id, GiftList giftList) {
		if(giftList.update()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		
		return Response.status(Status.SERVICE_UNAVAILABLE).build();
	}
}
