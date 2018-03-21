package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface OffreDao {
    @SqlUpdate("create table offres (id integer primary key autoincrement, intitule varchar(100),dateDeb date, dateFin date, listeMots varchar(100))")
    void createOffreTable();

    @SqlUpdate("insert into offres (intitule,dateDeb,dateFin, listeMots) values (:intitule, :dateDeb, :dateFin, :listeMots)")
    @GetGeneratedKeys
    int insert(@BindBean() Offre offre);

    @SqlQuery("select * from offres where intitule = :intitule")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Offre findByIntitule(@Bind("intitule") String intitule);

    @SqlQuery("select * from offres where search like :intitule")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Offre> search(@Bind("intitule") String intitule);

    @SqlUpdate("drop table if exists offres")
    void dropOffreTable();

    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from offres order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Offre> all();

    @SqlQuery("select * from offres where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Offre findById(@Bind("id") int id);

    void close();
}
