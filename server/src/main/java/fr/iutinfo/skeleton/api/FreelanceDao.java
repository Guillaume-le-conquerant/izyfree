package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface FreelanceDao {
    @SqlUpdate("create table freelance (id integer primary key autoincrement, name varchar(100), firstname varchar(100), email varchar(100), phone varchar(30), job varchar(50), photo varchar(300), cv varchar(300), mots varchar(120), passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createUserTable();

    @SqlUpdate("insert into freelance (name,firstname,email, phone, job, photo, cv, mots, passwdHash, salt, search) values (:name, :firstname, :email, :phone, :job, :photo, :cv, :mots, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Freelance freelance);

    @SqlQuery("select * from freelance where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Freelance findByName(@Bind("name") String name);

    @SqlQuery("select * from freelance where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Freelance> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists freelance")
    void dropUserTable();

    @SqlUpdate("delete from freelance where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from freelance order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Freelance> all();

    @SqlQuery("select * from freelance where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Freelance findById(@Bind("id") int id);

    void close();
}