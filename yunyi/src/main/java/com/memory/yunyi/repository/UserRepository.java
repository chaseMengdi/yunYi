package com.memory.yunyi.repository;

import com.memory.yunyi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * by陈曦
 */

public interface UserRepository  extends JpaRepository<User,Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.status='stop' WHERE a.id=?1")
    void  stopByID(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.status='normal' WHERE a.id=?1")
    void  recoverByID(Integer id);

    @Query("select a from User a where a.name like ?1")
    List<User> queryByName(String name);

    @Query(value = "select a from User  a where a.userID=?1 and a.password=?2")
    User login(Integer userID, String password) ;

    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.name=?2 ,a.hometown=?3,a.password=?4 ,a.avatar=?5 WHERE a.id=?1")
    void  update(Integer id,String name,String hometown,String password,String avatar);

}
