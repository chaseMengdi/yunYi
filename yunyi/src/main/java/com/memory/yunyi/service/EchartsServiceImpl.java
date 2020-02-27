package com.memory.yunyi.service;

import com.memory.yunyi.entity.Echarts;
import com.memory.yunyi.repository.EchartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘博谦
 * @Description:
 * @Date: Created in 15:28 2020/2/26
 * @Modified By:
 */
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    EchartsRepository echartsRepository;
    @Override
    public List<Echarts> findUserProvinceAndCount() {
        return echartsRepository.findUserProvinceAndCount();
    }
}
