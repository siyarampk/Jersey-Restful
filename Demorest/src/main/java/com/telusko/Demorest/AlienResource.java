package com.telusko.Demorest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Alien> getAliens() {
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Alien getAlien(@PathParam("id") int id) {
  	return repo.getAlien(id);
	}

	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien createAlien(Alien a1) {
		System.out.println(a1);
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("alienUpdate")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien AlienUpdate(Alien a1) {
	
		 Alien a2 = repo.getAlien(a1.getId());
		
		 if(a2.getId()==0) {
			repo.create(a1);
		}else {
			repo.updateAlien(a1);
		}
	  return a1;
	}
	
   @DELETE
   @Path("alien/{id}")
   public Alien AlienKill(@PathParam("id") int  id) {
	 
	   Alien a1 = repo.getAlien(id);
	   
	   if(a1.getId()!=0) {
		   repo.AlienKill(id);
	   }
	return a1;
   }
}
