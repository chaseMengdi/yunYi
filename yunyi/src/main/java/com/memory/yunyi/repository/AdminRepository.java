package com.memory.yunyi.repository;

import com.memory.yunyi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
    /**
     * 根据id查找到对应管理员
     *
     * @param adminID
     * @return
     */
    @Query(value = "select a from Admin  a where a.adminID=?1")
    Admin findByID(Integer adminID);

    @Query(value = "select a from Admin  a where a.adminID=?1 and a.password=?2")
    Admin findByIDAndPassword(Integer adminID, String password);


}
