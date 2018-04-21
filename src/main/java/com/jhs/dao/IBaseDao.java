package com.jhs.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    /** 
    * @Description: 根据ID加载实体
    * @Param: [entityClazz, id] 
    * @return: T 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    T get(Class<T> entityClazz, Serializable id);
    /**
    * @Description: 保存实体
    * @Param: [entity]
    * @return: java.io.Serializable
    * @Author: TangNengFa
    * @Date: 2018/4/12
    */
    Serializable save(T entity);
    /** 更新实体
    * @Description:
    * @Param: [entity] 
    * @return: void 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    void update(T entity);
    /** 删除实体
    * @Description:
    * @Param: [entity] 
    * @return: void 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */
    void delete(T entity);
    /** 
    * @Description: 根据ID删除实体
    * @Param: [entityClazz, id] 
    * @return: boolean 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    boolean delete(Class<T> entityClazz , Serializable id);
    /** 
    * @Description: 获取所有实体
     *@Param: [entityClazz]
    * @return: java.util.List<T> 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    List<T> findAll(Class<T> entityClazz);
    /** 
    * @Description:  获取实体总数
    * @Param: [entityClazz]
    * @return: long 
    * @Author: TangNengFa 
    * @Date: 2018/4/12 
    */ 
    long findCount(Class<T> entityClazz);
}
