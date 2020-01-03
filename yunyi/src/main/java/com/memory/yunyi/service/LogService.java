package com.memory.yunyi.service;

import org.springframework.stereotype.Service;
import com.memory.yunyi.entity.Log;
import java.util.List;



@Service
public interface LogService {
    List<Log> getAllLog();

    void addLog(Log log);
    Integer  findAdminNow();
}
