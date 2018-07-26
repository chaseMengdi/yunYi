package com.memory.yunyi.service;

import com.memory.yunyi.entity.process;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//by罗子璇
@Service
public interface processService {
    List<process> getAll();

    Optional<process> findById(Integer id);
    void delete(Integer id);
    process add(process process);

    void up(process process);

}
