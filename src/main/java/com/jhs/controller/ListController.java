package com.jhs.controller;

import com.jhs.model.List;
import com.jhs.model.User;
import com.jhs.service.ListService;
import com.jhs.service.UserService;
import com.jhs.util.myException;
import com.jhs.util.utils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: TangNengFa
 * @descption: 订单Controller
 * @create: 2018-04-13-14-51
 **/
@Component
@Path("list")
@Scope("prototype")
public class ListController {
    @Resource(name = "listService")
    private ListService listService;
    @Resource(name = "userService")
    private UserService userService;
    private List list = new List();
    private User user = new User();
    private static Map listMap = new HashMap();
    private static utils<List> util = new utils();

    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map addList(MultivaluedMap<String, String> formParams) throws myException {
        String arr[] = {"name", "type", "userId"};
        list.setCreateTime(new Date());
        list.setName(formParams.getFirst("name"));
        list.setType(Integer.parseInt(formParams.getFirst("type")));
        user = userService.get(formParams.getFirst("userId"));
        list.setUser(user);
        listMap = listService.addList(list);
        return listMap;
    }
}
