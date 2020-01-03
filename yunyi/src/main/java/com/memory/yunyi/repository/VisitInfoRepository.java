package com.memory.yunyi.repository;
import com.memory.yunyi.entity.VisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface VisitInfoRepository extends JpaRepository<VisitInfo,Integer>{
    @Query("select a from VisitInfo a where a.user.hometown = ?1 order by a.likeNum  DESC ")
    List<VisitInfo> listByHometown(String hometown);
}
