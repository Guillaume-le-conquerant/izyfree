package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface StaffDao {
    @SqlUpdate("create table staff (pseudo varchar(30) primary key, phone varchar(30), mail varchar(60), passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createStaffTable();

    @SqlUpdate("insert into staff (pseudo, phone, mail, passwdHash, salt, search) values (:pseudo, :phone, :mail, :passwdHash, :salt, :search)")
    @GetGeneratedKeys
    int insert(@BindBean() Staff staff);

    @SqlQuery("select * from staff where pseudo = :pseudo")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Staff findByPseudo(@Bind("pseudo") String pseudo);

    @SqlQuery("select * from staff where search like :pseudo")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Staff> search(@Bind("pseudo") String pseudo);

    @SqlUpdate("drop table if exists staff")
    void dropUserTable();

    @SqlUpdate("delete from staff where pseudo = :pseudo")
    void delete(@Bind("pseudo") String pseudo);

    @SqlQuery("select * from staff order by pseudo")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Staff> all();

    void close();
}
