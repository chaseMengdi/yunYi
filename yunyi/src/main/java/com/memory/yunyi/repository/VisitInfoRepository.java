package com.memory.yunyi.repository;

import com.memory.yunyi.entity.VisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VisitInfoRepository extends JpaRepository<VisitInfo, Integer> {
    /**
     * 获取同一家乡的用户页面信息
     *
     * @param hometown
     * @return
     */
    @Query("select a from VisitInfo a where a.user.city = ?1 order by a.likeNum  DESC ")
    List<VisitInfo> pageListByHometown(String hometown);

    /**
     * 根据openId获取主页访问信息
     *
     * @param openId
     * @return
     */
    @Query(value = "SELECT a FROM VisitInfo a WHERE a.userID=?1")
    VisitInfo findByOpenId(String openId);
}
