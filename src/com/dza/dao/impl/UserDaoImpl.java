package com.dza.dao.impl;

import com.dza.dao.UserDao;
import com.dza.domain.User;
import com.dza.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public void add(User user){
        String sql="insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());

    }

    @Override
    public void delete(int id) {
        String sql="DELETE FROM  USER WHERE id=?";
        template.update(sql,id);
    }

    @Override
    public User findUserByid(int id) {
        String sql="select *from user where id=? ";
        User user=template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
        return  user;
    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ?,age = ?, address = ?, qq = ?, email = ? where id = ?";
        template.update(sql,user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql="select count(*) from user WHERE 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        Set<String>keyset=condition.keySet();
        List<Object>params=new ArrayList<Object>();
        for (String key:keyset)
        {
            //获取value
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value=condition.get(key)[0];
            if (value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");//加条件值

        }
        }
        return  template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findBypage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user WHERE 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        Set<String>keyset=condition.keySet();
        List<Object>params=new ArrayList<Object>();
        for (String key:keyset)
        {
            //获取value
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value=condition.get(key)[0];
            if (value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"% ");//加条件值

            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql=sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

}
