package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import fr.iutinfo.skeleton.common.dto.OffreDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/freelance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FreelanceResource {
    final static Logger logger = LoggerFactory.getLogger(FreelanceResource.class);
    private static FreelanceDao dao = getDbi().open(FreelanceDao.class);

    public FreelanceResource() throws SQLException {
        if (!tableExist("freelance")) {
            logger.debug("Create table freelance");
            dao.createFreelanceTable();
            dao.insert(new Freelance(0, "Petit", "Benjamin"));
        }
    }

    @POST
    public FreelanceDto createFreelance(FreelanceDto dto) {
    	Freelance free = new Freelance();
        free.initFromDto(dto);
        free.resetPasswordHash();
        int id = dao.insert(free);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("/name/{name}")
    public FreelanceDto getFreelance(@PathParam("name") String name) {
        Freelance free = dao.findByName(name);
        if (free == null) {
            throw new WebApplicationException(404);
        }
        return free.convertToDto();
    }
    
    @GET
    @Path("/id/{id}")
    public FreelanceDto getFreelanceId(@PathParam("id") int id) {
        Freelance free = dao.findById(id);
        if (free == null) {
            throw new WebApplicationException(404);
        }
        return free.convertToDto();
    }
      

    @GET
    public List<FreelanceDto> getAllFreelance(@QueryParam("q") String query) {
        List<Freelance> freelance;
        if (query == null) {
            freelance = dao.all();
        } else {
            logger.debug("Search entreprise with query: " + query);
            freelance = dao.search("%" + query + "%");
        }
        return freelance.stream().map(Freelance::convertToDto).collect(Collectors.toList());
    }
    
	@PUT
	@Path("/id/{id}")
	public FreelanceDto modifyOffre(@PathParam("id") int id, FreelanceDto freelanceDto) {
		System.out.println(freelanceDto);
		FreelanceDto freeDto = dao.findById(id).convertToDto();
		if (!(freeDto.getId() == id)) {
			throw new WebApplicationException(404);
		} else {
			Freelance free = new Freelance();
			freelanceDto.setId(id);
			free.initFromDto(freelanceDto);;
			dao.update(free);
			return freelanceDto;
		}
	}

    @DELETE
    @Path("{id}")
    public void deleteFreelance(@PathParam("id") int id) {
        dao.delete(id);
    }

}