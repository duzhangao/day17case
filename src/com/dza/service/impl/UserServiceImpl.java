package com.dza.service.impl;

import com.dza.dao.UserDao;
import com.dza.dao.impl.UserDaoImpl;
import com.dza.domain.PageBean;
import com.dza.domain.User;
import com.dza.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    public void addUser(User user){
        dao.add(user);
    }

    @Override
    public void deleteUser(String user_id) {
        dao.delete(Integer.valueOf(user_id));
    }

    @Override
    public User findUserById(String id) {
        return  dao.findUserByid(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void deleteUsers(String[] ids) {
           for (int i=0;i<ids.length;i++){
               dao.delete(Integer.valueOf(ids[i]));
           }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage=Integer.valueOf(_currentPage);
        int rows=Integer.valueOf(_rows);//每页记录数
        if (currentPage<=0){
            currentPage=1;
        }
        //空的pagebean
        PageBean<User>pb=new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int totalcount=dao.findTotalCount(condition);
        pb.setTotalCount(totalcount);
        int start=(currentPage-1)*rows;

        List<User>list=dao.findBypage(start,rows,condition);
        pb.setList(list);
        int totalPage=totalcount%rows==0?totalcount/rows:(totalcount/rows+1);
        pb.setTotalPage(totalPage);
        return pb;
    }

    public User login(User user) {

        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
