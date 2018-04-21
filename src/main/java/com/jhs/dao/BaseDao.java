package com.jhs.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseDao<T> implements IBaseDao<T> {
    @Autowired //用在JavaBean中的注解，通过byType形式，用来给指定的字段或方法注入所需的外部资源。
    protected SessionFactory sessionFactory;

    @Override //表示重写父类方法
    public T get(Class<T> entityClazz, Serializable id) {
        return sessionFactory.getCurrentSession().get(entityClazz, id);
    }

    @Override
    public Serializable save(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
    // @SuppressWarnings。该批注的作用是给编译器一条指令，
    // 告诉它对被批注的代码元素内部的某些警告保持静默。
    //deprecation   使用了不赞成使用的类或方法时的警告
    //unchecked     执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型。
    //fallthrough   当 Switch 程序块直接通往下一种情况而没有 Break 时的警告。
    //path          在类路径、源文件路径等中有不存在的路径时的警告。
    //serial        当在可序列化的类上缺少 serialVersionUID 定义时的警告。
    //finally       任何 finally 子句不能正常完成时的警告。
    //all           关于以上所有情况的警告。
    @SuppressWarnings("unchecked")
    public boolean delete(Class<T> entityClazz, Serializable id) {
        String hql = "delete " + entityClazz.getSimpleName() + " en where en.id = ?0";
        Query<T> query = sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("0", id);
        return (query.executeUpdate() > 0);
    }

    @Override
    public List<T> findAll(Class<T> entityClazz) {
        String hql = "select en from " + entityClazz.getSimpleName() + " en";
        return find(hql);
    }

    @Override
    public long findCount(Class<T> entityClazz) {
        String  hql  = "select count(*) from " + entityClazz.getSimpleName();
        List<T> list = find(hql);
        if (list != null && list.size() == 1) {
            return (Long) list.get(0);
        }
        return 0;
    }

    /** 
    * @Description:  根据HQL语句查询实体
     * @Param: [hql]
    * @return: java.util.List<T> 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql) {
        return sessionFactory.getCurrentSession()
                .createQuery(hql)
                .list();
    }

    /** 
    * @Description: 根据带占位符参数HQL语句查询实体 
    * @Param: [hql, params] 
    * @return: java.util.List<T> 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... params) {
        Query<T> query = sessionFactory.getCurrentSession().createQuery(hql);

        for (int i=0, len=params.length; i<len; i++) {
            query.setParameter(i + "", params[i]);
        }
        return query.list();
    }

    /** 
    * @Description:  使用hql 语句进行分页查询操作
    * @Param: [hql, pageIndex, pageSize] 
    * @return: java.util.List<T> 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql, int pageIndex, int pageSize) {
        Query<T> query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.setFirstResult((pageIndex-1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    /** 
    * @Description:  使用hql 语句进行分页查询(带条件)操作
    * @Param: [hql, pageNo, pageSize, params] 
    * @return: java.util.List<T> 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql , int pageNo, int pageSize, Object... params) {
        Query<T> query = sessionFactory.getCurrentSession().createQuery(hql);
        for (int i=0, len=params.length; i<len; i++) {
            query.setParameter(i + "", params[i]);
        }
        return query.setFirstResult((pageNo - 1) + pageSize)
                .setMaxResults(pageSize)
                .list();
    }


}
