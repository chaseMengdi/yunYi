package com.memory.yunyi.service;

import com.memory.yunyi.entity.Admin;
import org.springframework.stereotype.Service;

//by唐桥保
@Service
public interface AdminService {
   Admin adminLoginJudge(Integer adminID, String password);

   String findAdminNameByID(Integer adminID);
}
