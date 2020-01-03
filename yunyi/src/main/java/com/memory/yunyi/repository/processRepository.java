package com.memory.yunyi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.memory.yunyi.entity.process;

import javax.transaction.Transactional;


public interface processRepository extends JpaRepository<process,Integer> {
    @Query(value = "insert into process(id,adminid,reason_num,ttime,userid) value(?1,?2,?3,?4,?5)", nativeQuery = true)
    @Modifying
    @Transactional
    void reup(Integer id,Integer adminID, String Num, String ttime, Integer userid);
}
