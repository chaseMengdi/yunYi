package com.memory.yunyi.repository;

import com.memory.yunyi.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;


public interface LogRepository extends JpaRepository<Log,Integer > {

    @Query(value = "insert into log(adminid,admin_name,log_time) value(?1,?2,?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void add(Integer adminID, String adminName, String logTime);

    @Query (value = "select max(a.logID) FROM log a",nativeQuery = true)
    Integer  findLogNow();

}
