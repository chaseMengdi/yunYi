package com.memory.yunyi.repository;

import com.memory.yunyi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//by唐桥保
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query(value = "select a from Admin  a where a.adminID=?1")
    Admin findByID(Integer adminID) ;

    @Query(value = "select a from Admin  a where a.adminID=?1 and a.password=?2")
    Admin findByIDAndPassword(Integer adminID, String password) ;


}
