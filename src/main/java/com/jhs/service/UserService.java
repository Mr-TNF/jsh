package com.jhs.service;

import com.jhs.dao.UserDao;
import com.jhs.model.User;
import com.jhs.util.myException;
import com.jhs.util.utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service(value = "userService")
public class UserService {
    @Resource(name="userDao")
    private UserDao userDao;

    private static Map userMap = new HashMap();
    private static utils<User> util = new utils();
    //添加用户
    public User addUser(User user) throws myException {
        userDao.addUser(user);
        return user;
    }
    //更新用户
    public Map updateUser(User user) throws myException {
        Map map = new HashMap();
        userDao.updateUser(user);
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("createTime", user.getCreateTime());
        return map;
    }
    //删除用户
    public boolean delUser(User user) throws myException {
        return userDao.delUser(user) ? true : false;
    }
    // 根据id查询
    public User  get(String id) throws myException {
          return userDao.get(id);
    }
    // 根据条件查询 模板
    public  List<User> findByHt(User user) throws myException {
        return userDao.findByHt(user);
    }

    // 根据条件查询 HQL
    public List<User> findByHQL(String HQL, Object[] params) throws myException {
        return userDao.findByHQL(HQL, params);
    }

    // 分页查询
    public List<User> findByPage(Map map, int start, int end) throws myException {
        return userDao.findByPage(map, start, end);
    }

    //查询所有
    public List find(String HQL) throws myException {
        return userDao.find(HQL);
    }

    //统计
    public int count(Map map) throws myException {
        return userDao.count(map);
    }
}
