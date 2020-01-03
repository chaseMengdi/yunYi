package com.memory.yunyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.memory.yunyi.entity.userPageContent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import javax.transaction.Transactional;


public interface userPageRepository extends JpaRepository<userPageContent,Integer> {


//    更改特定用户使用的模板号
    @Modifying
    @Transactional
    @Query("UPDATE userPageContent a SET a.modelID=?1 WHERE a.id=?2")
   void  setModelId(Integer modelID,Integer userID);
}
