package com.memory.yunyi.service;

import com.memory.yunyi.entity.Log;
import com.memory.yunyi.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//by唐桥保
@Service
public class LogServicelmpl implements LogService{

    @Autowired
    private LogRepository logRepository;

    @Override
    public List<Log> getAllLog(){
        Sort sort = new Sort(Sort.Direction.DESC,"logID");
        return logRepository.findAll(sort);
    }

    @Override
    public void addLog(Log log){
        logRepository.add(log.getAdminID(),log.getAdminName(),log.getLogTime());
    }

    @Override
    public Integer findAdminNow() {
        return logRepository.findById(logRepository.findLogNow()).get().getAdminID();
    }


}


