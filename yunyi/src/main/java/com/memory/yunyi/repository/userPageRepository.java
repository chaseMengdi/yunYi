package com.memory.yunyi.repository;

import com.memory.yunyi.entity.userPageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface userPageRepository extends JpaRepository<userPageContent, Integer> {


    //    更改特定用户使用的模板号
    @Modifying
    @Transactional
    @Query("UPDATE userPageContent a SET a.modelID=?1 WHERE a.userID=?2")
    void setModelId(Integer modelID, String userID);

    /**
     * 根据用户的openId获取主页内容
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM user_page_content WHERE userid=?1", nativeQuery = true)
    userPageContent findByOpenId(String id);

    /**
     * 寻找对应用户同一家乡的人员
     * @param city 家乡
     * @return
     */
    @Query(value = "SELECT a FROM userPageContent a WHERE a.userID IN (" +
            "SELECT b.userID FROM User b WHERE b.city=?1)")
    List<userPageContent> pageListByHometown(String city);
}
