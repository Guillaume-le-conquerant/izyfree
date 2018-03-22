package fr.iutinfo.skeleton.common.remote;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.OffreDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

public class OffresProvider {

    final static Logger logger = LoggerFactory.getLogger(OffresProvider.class);
    private String baseUrl;

    public OffresProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public List<OffreDto> readAllOffres() {
        try {
            return ClientBuilder.newClient()//
                    .target(baseUrl + "offre/")
                    .request()
                    .get(new GenericType<List<OffreDto>>() {
                    });
        } catch (Exception e) {
            String message = ClientBuilder.newClient()
                    .target(baseUrl + "offre/")
                    .request()
                    .get(String.class);

            logger.error(e.getMessage());
            throw new RuntimeException(message);
        }
    }

    public OffreDto addOffre(OffreDto offre) {
        logger.debug("Create offre : " + offre.getIntitule());
        Entity<OffreDto> offreEntity = Entity.entity(offre, MediaType.APPLICATION_JSON);

        return ClientBuilder.newClient()
                .target(baseUrl + "offre/")
                .request()
                .post(offreEntity)
                .readEntity(OffreDto.class);
    }

    public OffreDto readOffre(String intitule) {
        String url = baseUrl + "offre/" + intitule;
        logger.debug("Read url : " + url);

        return ClientBuilder.newClient()//
                .target(url)
                .request()
                .get(OffreDto.class);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
