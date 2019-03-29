package com.dza.dao;

import com.dza.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {


    public List<User> findAll();
public  User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int id);

    User findUserByid(int id);

    void update(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findBypage(int start, int rows, Map<String, String[]> condition);
}