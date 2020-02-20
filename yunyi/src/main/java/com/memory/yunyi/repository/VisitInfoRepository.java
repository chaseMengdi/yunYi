package com.memory.yunyi.repository;

import com.memory.yunyi.entity.VisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VisitInfoRepository extends JpaRepository<VisitInfo, Integer> {
    /**
     * 获取同一家乡的用户
     *
     * @param hometown
     * @return
     */
    @Query("select a from VisitInfo a where a.user.city = ?1 order by a.likeNum  DESC ")
    List<VisitInfo> listByHometown(String hometown);

    /**
     * 根据openId获取主页访问信息
     *
     * @param openId
     * @return
     */
    @Query(value = "SELECT * FROM visitInfo WHERE userid=?1", nativeQuery = true)
    VisitInfo findByOpenId(String openId);
}
