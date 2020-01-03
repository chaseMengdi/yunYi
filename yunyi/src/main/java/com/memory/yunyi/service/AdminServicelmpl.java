package com.memory.yunyi.service;

import com.memory.yunyi.entity.Admin;
import com.memory.yunyi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminServicelmpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin adminLoginJudge(Integer adminID,String password){
        Admin admin=adminRepository.findByIDAndPassword(adminID, password);
        return admin;
    }

    @Override
    public String findAdminNameByID(Integer adminID){
         Admin admin=adminRepository.findByID(adminID);
         return admin.getAdminName();
    }
}