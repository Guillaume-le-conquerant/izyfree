package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface FreelanceDao {
    @SqlUpdate("create table freelance (id integer primary key autoincrement,firstname varchar(100), name varchar(100), email varchar(100), phone varchar(30), job varchar(50), photo varchar(300), cv varchar(300), mots varchar(120), champLibre varchar(250), tarif varchar(10), localisation varchar(100), conditions varchar(100), passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createFreelanceTable();

    @SqlUpdate("insert into freelance (firstname, name, email, phone, job, photo, cv, mots, champLibre, tarif, localisation, conditions, passwdHash, salt, search) values (:firstname, :name, :email, :phone, :job, :photo, :cv, :mots, :champLibre, :tarif, :localisation, :conditions, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Freelance freelance);

    @SqlQuery("select * from freelance where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Freelance findByName(@Bind("name") String name);

    @SqlQuery("select * from freelance where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Freelance> search(@Bind("name") String name);
    
    @SqlQuery("select * from freelance where localisation like :localisation")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Freelance> searchLocalisation(@Bind("localisation") String localisation);

    @SqlUpdate("drop table if exists freelance")
    void dropFreelanceTable();

    @SqlUpdate("delete from freelance where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from freelance order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Freelance> all();

    @SqlQuery("select * from freelance where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Freelance findById(@Bind("id") int id);
    
    @SqlUpdate("update offres set firstname = :firstname, name = :name, email = :email, phone = :phone, job = :job, photo = :photo, cv = :cv, mots = :mots, champLibre = :champLibre, tarif = :tarif, localisation = :localisation, conditions = :conditions, passwdHash = :passwdHash, salt = :salt, search = :search where id = :id")
    @GetGeneratedKeys
    int update(@BindBean() Freelance freelance);

    void close();
}