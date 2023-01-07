package be.Jadoulle_Declercq.API;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.Jadoulle_Declercq.JavaBeans.Gift;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

@Path("/gift")
public class GiftAPI {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGift(@FormParam("priority") int priority, @FormParam("name") String name, 
			@FormParam("description") String description, @FormParam("averagePrice") double averagePrice, 
			@FormParam("link") String link, @FormParam("picture") String picture, 
			@FormParam("idGiftList") int idGiftList) {
		
		if (priority > 0 && name != null && description != null && averagePrice > 0.0 && idGiftList > 0) {
			if (!name.isBlank() && !description.isBlank()) {
				
				Gift newGift = new Gift();
				newGift.setName(name);
				newGift.setPriority(priority);
				newGift.setDescription(description);
				newGift.setAveragePrice(averagePrice);
				newGift.setLink(link);
				newGift.setPicture(picture);
				GiftList giftList = new GiftList();
				giftList.setId(idGiftList);				
				newGift.setGiftList(giftList);
				
				if (newGift.create()) {
					return Response.status(Status.CREATED)
							.header("Location", "/JEE-GiftListManagement-API/api/gift/" + newGift.getId()).build();
				}
				else {
					return Response.status(Status.SERVICE_UNAVAILABLE).build();
				}
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}

}
