package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface StaffDao {
    @SqlUpdate("create table staff (id integer primary key autoincrement, name varchar(30), firstname varchar(30),mail varchar(60), passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createStaffTable();

    @SqlUpdate("insert into staff (name, firstname, mail, passwdHash, salt, search) values (:name, :firstname, :mail, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Staff staff);

    @SqlQuery("select * from staff where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Staff findByName(@Bind("name") String name);

    @SqlQuery("select * from staff where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Staff> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists staff")
    void dropStaffTable();

    @SqlUpdate("delete from staff where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from staff order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Staff> all();

    void close();
}
