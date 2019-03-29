package com.dza.service;

import com.dza.domain.PageBean;
import com.dza.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();
    public User login(User user);

    void addUser(User add_user);

    void deleteUser(String user_id);

    User findUserById(String id);

    void updateUser(User user);

    void deleteUsers(String []ids);

    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
