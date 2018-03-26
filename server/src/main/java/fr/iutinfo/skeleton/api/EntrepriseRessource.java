package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;
import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/entreprise")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntrepriseRessource {
    final static Logger logger = LoggerFactory.getLogger(EntrepriseRessource.class);
    private static EntrepriseDAO dao = getDbi().open(EntrepriseDAO.class);

    public EntrepriseRessource() throws SQLException {
        if (!tableExist("entreprise")) {
            logger.debug("Create table entreprise");
            dao.createEntrepriseTable();
            dao.insert(new Entreprise(0, "Izyfree", "Benjamin"));
        }
    }

    @POST
    public EntrepriseDTO createEntreprise(EntrepriseDTO dto) {
    	Entreprise entre = new Entreprise();
        entre.initFromDto(dto);
        int id = dao.insert(entre);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("{name}")
    public EntrepriseDTO getEntreprise(@PathParam("name") String name) {
        Entreprise entre = dao.findByName(name);
        if (entre == null) {
            throw new WebApplicationException(404);
        }
        return entre.convertToDto();
    }

    @GET
    public List<EntrepriseDTO> getAllEntreprise(@QueryParam("q") String query) {
        List<Entreprise> entreprise;
        if (query == null) {
            entreprise = dao.all();
        } else {
            logger.debug("Search entreprise with query: " + query);
            entreprise = dao.search("%" + query + "%");
        }
        return entreprise.stream().map(Entreprise::convertToDto).collect(Collectors.toList());
    }
    
    @PUT
	@Path("/id/{id}")
	public EntrepriseDTO modifyCorporation(@PathParam("id") int id, EntrepriseDTO entrepriseDto) {
		System.out.println(entrepriseDto);
		EntrepriseDTO entDto = dao.findById(id).convertToDto();
		if (!(entDto.getId() == id)) {
			throw new WebApplicationException(404);
		} else {
			Entreprise ent = new Entreprise();
			entrepriseDto.setId(id);
			ent.initFromDto(entrepriseDto);;
			dao.update(ent);
			return entrepriseDto;
		}
	}

    @DELETE
    @Path("{id}")
    public void deleteEntreprise(@PathParam("id") int id) {
        dao.delete(id);
    }

}
