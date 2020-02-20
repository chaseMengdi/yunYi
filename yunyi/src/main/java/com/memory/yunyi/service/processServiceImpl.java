package com.memory.yunyi.service;

import com.memory.yunyi.entity.process;
import com.memory.yunyi.repository.processRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class processServiceImpl implements processService {
    @Autowired
    private processRepository processRepository;


    @Override
    public List<process> getAll() {
        return processRepository.findAll();
    }

    @Override
    public Optional<process> findById(Integer id) {
        return processRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        processRepository.deleteById(id);
    }

    @Override
    public process add(process process) {
        return processRepository.save(process);
    }

    @Override
    public void up(process process) {
        processRepository.reup(process.getID(), process.getAdminID(), process.getReasonNum(), process.getTime(), process.getUserID());
    }
}
