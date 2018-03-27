package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface EntrepriseDAO {
    @SqlUpdate("create table entreprise (id integer primary key autoincrement, name varchar(100), nomContact varchar(100), prenomContact varchar(100), tel varchar(10), email varchar(100), fonctionsContact varchar(100), profilRecherche varchar(100), ville varchar(100), champLibre varchar(250), search varchar(25))")
    void createEntrepriseTable();

    @SqlUpdate("insert into entreprise (name,nomContact, prenomContact, tel,email, fonctionsContact, profilRecherche, ville, champLibre, search) values (:name, :nomContact, :prenomContact, :tel, :email, :fonctionsContact, :profilRecherche, :ville, :champLibre, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Entreprise user);

    @SqlQuery("select * from entreprise where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Entreprise findByName(@Bind("name") String name);

    @SqlQuery("select * from entreprise where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Entreprise> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists entreprise")
    void dropEntrepriseTable();

    @SqlUpdate("delete from entreprise where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from entreprise order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Entreprise> all();

    @SqlQuery("select * from entreprise where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Entreprise findById(@Bind("id") int id);
    
    @SqlUpdate("update entreprise set name = :name, nomContact = :nomContact, prenomContact= :prenomContact, tel = :tel, email = :email, fonctionsContact = :fonctionsContact, profilRecherche = :profilRecherche, ville = :ville, champLibre = :champLibre, search = :search where id = :id")
    @GetGeneratedKeys
    int update(@BindBean() Entreprise entreprise);

    void close();
}
