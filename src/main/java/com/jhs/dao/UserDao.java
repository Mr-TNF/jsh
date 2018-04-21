package com.jhs.dao;

import com.jhs.model.User;
import com.jhs.util.myException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository(value = "userDao")
public class UserDao extends BaseDao<User> {
    @Resource(name="hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    //添加用户
    public void  addUser(User user) throws myException {
        hibernateTemplate.save(user);
    }
    //更新用户信息
    public void  updateUser(User user) throws myException {
        hibernateTemplate.saveOrUpdate(user);
    }
    //删除用户
    public boolean  delUser(User user) throws myException {
       return delete(User.class, user.getId());
    }
    // 根据id查询
    public User  get(String id) throws myException {
        return hibernateTemplate.get(User.class , id);
//        return get(User.sclass, id);
    }

    //查询所有用户
    public List find(String HQL) {
        return hibernateTemplate.find(HQL);
    }

    // 根据条件查询 模板
    public List<User> findByHt(User user) throws myException {
        List<User> list =  hibernateTemplate.findByExample(user);
        return list;
    }

    // 根据条件查询 HQL
    public List<User> findByHQL(String HQL, Object[] params) throws myException {
        return (List<User>) hibernateTemplate.find(HQL, params);
    }

    // 分页查询
    public List<User> findByPage(Map map, int start, int end) throws myException {
        DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
        for (Object key : map.keySet()) {
            criteria.add(Restrictions.eq(key.toString(), map.get(key)));
        }
        List<User> list = (List<User>) hibernateTemplate.findByCriteria(criteria, start, end);
        return list;
    }
    // criteria统计查询
    public int count(Map map) throws myException {
        DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
        for (Object key : map.keySet()) {
            criteria.add(Restrictions.eq(key.toString(), map.get(key)));
        }
        return hibernateTemplate.findByCriteria(criteria).size();
    }

}
