package com.memory.yunyi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setCreateTableOnStartup(true);
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder()); //解密 加密后的密码
    }

    /**
     * security配置，包括登录配置与页面拦截信息
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("adminID")
                .passwordParameter("password")
                .loginProcessingUrl("/auth")
                .failureForwardUrl("/login")
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login.html","/login","/auth","/static/**","/wxGetUserAndPageList","/wxLogin","/wxUpdate","/wxGetUpgById","/wxSaveContent","/setModel","/wxDecLikeById","/wxLikeById","/wxReportById","/wxDescListByLike","/wxListByHometown","/addComment","/commentList","/uploadImg","/upload/**").permitAll()
                .anyRequest().authenticated();

        http.logout().logoutSuccessUrl("/login");
    }
}
