package fr.iutinfo.skeleton.common.remote;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;

public class EntrepriseProvider {

    final static Logger logger = LoggerFactory.getLogger(UsersProvider.class);
    private String baseUrl;

    public EntrepriseProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public List<EntrepriseDTO> readAllUsers() {
        try {
            return ClientBuilder.newClient()//
                    .target(baseUrl + "entreprise/")
                    .request()
                    .get(new GenericType<List<EntrepriseDTO>>() {
                    });
        } catch (Exception e) {
            String message = ClientBuilder.newClient()
                    .target(baseUrl + "entreprise/")
                    .request()
                    .get(String.class);

            logger.error(e.getMessage());
            throw new RuntimeException(message);
        }
    }

    public EntrepriseDTO addUser(EntrepriseDTO entre) {
        logger.debug("Create user : " + entre.getNom());
        Entity<EntrepriseDTO> userEntity = Entity.entity(entre, MediaType.APPLICATION_JSON);

        return ClientBuilder.newClient()
                .target(baseUrl + "entreprise/")
                .request()
                .post(userEntity)
                .readEntity(EntrepriseDTO.class);
    }

    public EntrepriseDTO readUser(String name) {
        String url = baseUrl + "entreprise/" + name;
        logger.debug("Reade url : " + url);

        return ClientBuilder.newClient()//
                .target(url)
                .request()
                .get(EntrepriseDTO.class);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}