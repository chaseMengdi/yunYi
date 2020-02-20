package com.memory.yunyi.service;

import com.memory.yunyi.entity.Log;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LogService {
    List<Log> getAllLog();

    void addLog(Log log);

    Integer findAdminNow();
}
