package com.jhs.dao;

import com.jhs.model.List;
import com.jhs.model.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author: TangNengFa
 * @descption: 订单Dao
 * @create: 2018-04-13-14-50
 **/
@Repository(value = "listDao")
public class ListDao extends BaseDao<List> {
    @Resource(name="hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    //添加订单
    public void  addList(List list) {
        hibernateTemplate.save(list);
    }


}
