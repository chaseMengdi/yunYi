package com.memory.yunyi.security;

import com.alibaba.druid.util.StringUtils;
import com.memory.yunyi.entity.Admin;
import com.memory.yunyi.repository.AdminRepository;
import com.memory.yunyi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByID(Integer.parseInt(adminId));
        String password = new BCryptPasswordEncoder().encode(admin.getPassword());
        if(admin !=null){
            return new User(adminId,password,new ArrayList<GrantedAuthority>());
        }
        return null;
    }
}
