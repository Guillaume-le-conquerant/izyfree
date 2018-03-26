package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface OffreDao {
    @SqlUpdate("create table offres (id integer primary key autoincrement, intitule varchar(100),dateDeb varchar(15), dateFin varchar(15), nomEntreprise varchar(50) , champLibre varchar(100))")
    void createOffreTable();

    @SqlUpdate("insert into offres (intitule, dateDeb, dateFin, nomEntreprise, champLibre) values (:intitule, :dateDeb, :dateFin, :nomEntreprise, :champLibre)")
    @GetGeneratedKeys
    int insert(@BindBean() Offre offre);

    @SqlQuery("select * from offres where intitule = :intitule")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Offre findByIntitule(@Bind("intitule") String intitule);

    @SqlQuery("select * from offres where intitule like :intitule")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Offre> search(@Bind("intitule") String intitule);

    @SqlUpdate("drop table if exists offres")
    void dropOffreTable();

    @SqlUpdate("delete from offres where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from offres order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Offre> all();

    @SqlQuery("select * from offres where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Offre findById(@Bind("id") int id);
    
    @SqlUpdate("update offres set intitule = :intitule, dateDeb = :dateDeb, dateFin = :dateFin, nomEntreprise = :nomEntreprise, champLibre = :champLibre where id = :id")
    @GetGeneratedKeys
    int update(@BindBean() Offre offre);
    
    void close();
}
