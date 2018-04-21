package com.jhs.service;

import com.jhs.dao.ListDao;
import com.jhs.model.List;
import com.jhs.model.User;
import com.jhs.util.utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: TangNengFa
 * @descption: 订单Service
 * @create: 2018-04-13-14-51
 **/
@Transactional
@Service(value = "listService")
public class ListService {
    @Resource(name = "listDao")
    private ListDao listDao;

    private static Map listMap = new HashMap();
    private static utils<List> util = new utils();

    //添加用户
    public Map addList(List list) {
        try {
            listDao.addList(list);
            listMap = util.getJsonMap(0, "success", "创建成功", list);
        } catch (Exception e) {
            listMap = util.getJsonMap(1, "error", "内部错误");
        }
        return listMap;
    }
}
