package com.memory.yunyi.repository;

import com.memory.yunyi.entity.Echarts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘博谦
 * @Description:
 * @Date: Created in 15:23 2020/2/26
 * @Modified By:
 */
public interface EchartsRepository extends JpaRepository<Echarts,Integer> {
    /**
     * 查询用户来自的省市及数量
     * @return
     */
    @Query(value = "SELECT province AS name,COUNT(province) AS num  FROM user GROUP BY province",nativeQuery = true)
    public List<Echarts> findUserProvinceAndCount();


}
