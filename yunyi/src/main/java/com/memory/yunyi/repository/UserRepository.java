package com.memory.yunyi.repository;

import com.memory.yunyi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据id封禁用户
     *
     * @param id
     */
    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.status='stop' WHERE a.openId=?1")
    void stopByID(String id);

    /**
     * 根据id恢复封禁用户
     *
     * @param id
     */
    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.status='normal' WHERE a.openId=?1")
    void recoverByID(String id);

    /**
     * 根据用户nickName模糊查询
     *
     * @param name
     * @return
     */
    @Query("select a from User a where a.nickName like ?1")
    List<User> queryByName(String name);

    /**
     * 根据openId获取已注册用户的信息
     *
     * @param openId
     * @return
     */
    @Query(value = "SELECT * FROM user WHERE open_id=?1", nativeQuery = true)
    User findByOpenId(String openId);

    /**
     * 修改指定用户的昵称和头像
     *
     * @param id
     * @param name
     * @param avatar
     */
    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.nickName=?2 ,a.avatarUrl=?3 WHERE a.openId=?1")
    void update(String id, String name, String avatar);

    /**
     * 以openid查询用户是否已经注册
     *
     * @param openid
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM user WHERE open_id=?1", nativeQuery = true)
    Integer existsOrNotByOpenId(String openid);
}
