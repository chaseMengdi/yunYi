package com.memory.yunyi.service;

import com.memory.yunyi.entity.Echarts;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘博谦
 * @Description:
 * @Date: Created in 15:27 2020/2/26
 * @Modified By:
 */
@Service
public interface EchartsService {
    public List<Echarts> findUserProvinceAndCount();
}
