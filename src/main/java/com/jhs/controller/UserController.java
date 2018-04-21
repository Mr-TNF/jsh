package com.jhs.controller;

import com.jhs.util.Page;
import com.jhs.model.User;
import com.jhs.service.UserService;
import com.jhs.util.ErrorCode;
import com.jhs.util.myException;
import com.jhs.util.utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

@Component
@Path("user")
@Scope("prototype")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    private User user;
    private static Map userMap = new HashMap();
    private static utils<User> util = new utils();

    private static Log log = LogFactory.getLog(UserController.class);

    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map addUser(MultivaluedMap<String, String> formParams) throws myException {
        String arr[] = {"username", "password"};
        if(util.isNull( arr , formParams)){
          userMap = util.getJsonMap(1, "error","参数不全");
        } else {
            try {
                Map map = new HashMap();
                user.setUsername(formParams.getFirst("username"));
                map = util.linkHQL(user);
                Object[] params = ((ArrayList)map.get("params")).toArray();
                String HQL = ((String)map.get("HQL")).substring(0, ((String)map.get("HQL")).lastIndexOf("and"));
                List<User> userResult = userService.findByHQL(HQL, params);
                if (userResult.size() > 0) {
                    userMap = util.getJsonMap(1,"error","已存在此用户");
                } else {
                    user.setCreateTime(new Date());
                    user.setPassword(formParams.getFirst("password"));
                    userMap = util.getJsonMap(0,"success","添加成功", userService.addUser(user));
                }
            } catch (Exception e) {
                throw new myException(ErrorCode.ERROR_CODE);
            }

        }
        return userMap;
    }
    @POST
    @Path("/update")
    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map updateUser(MultivaluedMap<String, String> formParams) throws myException {
        String arr[] = {"id", "password"};
        if (util.isNull( arr , formParams)) {
            userMap = util.getJsonMap(1,"error", "参数不全");
        } else {
            try {
                user = userService.get(formParams.getFirst("id"));
                user.setPassword(formParams.getFirst("password"));
                userMap  = util.getJsonMap(0, "success", "更新成功", userService.updateUser(user));
            } catch(Exception e) {
                throw new myException(ErrorCode.ERROR_CODE);
            }

        }
        return userMap;
    }

    @POST
    @Path("/del")
    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map delUser(MultivaluedMap<String, String> formParams) throws myException {
        String arr[] = {"id"};
        boolean flag = false;
        if (util.isNull(arr, formParams)) {
            throw new myException(1, "error", "参数不为空");
        } else {
            try {
                user.setId(formParams.getFirst("id"));
                flag = userService.delUser(user);
                if (flag) userMap = util.getJsonMap(0,"success", "删除成功");
                else {
                    userMap = util.getJsonMap(1,"success", "无此用户");
                }
            } catch (NumberFormatException e) {
                throw new myException(500, "error", "参数类型不对");
            } catch (Exception e) {
                throw new myException(ErrorCode.ERROR_CODE);
            }

        }
       return userMap;
    }

    @POST
    @Path("/findById")
    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map get(@FormParam("id") String id) throws myException {
        try {
            user = userService.get(id);
            userMap = util.getJsonMap(0,"success", "查找成功", user);
        } catch (Exception e) {
            throw new myException(ErrorCode.ERROR_CODE);
        }
        return userMap;
    }

    @POST
    @Path("/findByHt")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map findByHt(User user)  throws myException {
        try {
            List<User> resultUser =  userService.findByHt(user);
            userMap = util.getJsonMap(0,"success", "查找成功", resultUser);
        } catch (Exception e) {
            throw new myException(ErrorCode.ERROR_CODE);
        }
        return  userMap;
    }

    @POST
    @Path("/findByHQL")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map findByHQL(User user) throws myException {
        try {
            Map map = new HashMap();
            map = util.linkHQL(user);
            Object[] params = ((ArrayList)map.get("params")).toArray();
            String HQL = ((String)map.get("HQL")).substring(0, ((String)map.get("HQL")).lastIndexOf("and"));
            List<User> userResult = userService.findByHQL(HQL, params);
            userMap = util.getJsonMap(0,"success", "读取成功", userResult);
        } catch (Exception e) {
            throw new myException(ErrorCode.ERROR_CODE);
        }
        return userMap;
    }

    @POST //分页
    @Path("/findByPage")
    @Consumes("application/x-www-form-urlencoded") //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map findByPage(MultivaluedMap<String, String> formParams) throws myException {
        int pageIndex = 0;
        int pageSize = 10;
        Page<User> page = new Page();
        String arr[] = {"pageIndex", "pageSize"};
        Map map = new HashMap();
        try {
            if (util.isNull(arr, formParams)){
                userMap = util.getJsonMap(1, "error","参数不全");
            } else if (Integer.parseInt(formParams.getFirst("pageIndex")) < 0 || Integer.parseInt(formParams.getFirst("pageSize")) < 1) {
                userMap = util.getJsonMap(1,"error","不合法的参数");
            } else {
                pageIndex = Integer.parseInt(formParams.getFirst("pageIndex"));
                pageSize = Integer.parseInt(formParams.getFirst("pageSize"));
                for (String key : formParams.keySet()) {
                    if (!key.equals("pageIndex") && !key.equals("pageSize")) {
                        map.put(key, formParams.getFirst(key));
                    }
                }
                int userResultLength = userService.count(map);
                List<User> userResultPage = userService.findByPage(map, pageIndex, pageSize);
                page.setCount(userResultLength);
                page.setPageIndex(pageIndex);
                page.setPageSize(pageSize);
                page.setPageList(userResultPage);
                userMap = util.getJsonMap(0,"success", "查找成功", page);
            }
        } catch (Exception e) {
            throw new myException(ErrorCode.ERROR_CODE);
        }
        return userMap;
    }

    @POST //总页数
    @Path("/findCount")
    @Consumes(MediaType.APPLICATION_JSON) //接收客户端的数据格式
    @Produces(MediaType.APPLICATION_JSON) //返回客户端的数据格式
    public Map findCount(User user) throws myException {
        try {
            Map map = new HashMap();
            map = util.linkHQL(user);
            Object[] params = ((ArrayList)map.get("params")).toArray();
            String HQL = ((String)map.get("HQL")).substring(0, ((String)map.get("HQL")).lastIndexOf("and"));
            List<User> userResult = userService.findByHQL(HQL, params);
            map.put("totalPage", userResult.size());
            userMap = util.getJsonMap(0,"success", "查找成功", map);
        } catch (Exception e) {
            throw new myException(ErrorCode.ERROR_CODE);
        }
        return userMap;
    }

    // 文件下载
    @GET
    @Path("/downFile")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getFile(@QueryParam("name") String name, @Context HttpServletResponse response)
            throws myException{
        FileInputStream fis = null;
        try
        {
           String path = this.getClass().getResource("/").getPath()
                    .replaceFirst("/", "");
            path = path.replaceAll("WEB-INF/classes/", "file/");
            fis = new FileInputStream(new File(path+"test.txt"));

            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            response.setHeader("Content-Disposition","attachment;filename=" + name+ ".txt");//为文件命名
            response.addHeader("content-type","application/txt");
            return bit;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/uploadFile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Map upload(@FormDataParam("cpName") InputStream uploadedInputStream,
            @FormDataParam("importList") String s, @FormDataParam("importList") FormDataContentDisposition fileDetail)
            throws myException{
        Map map = new HashMap<>();
        long size = 0;;
        String fileName = Calendar.getInstance().getTimeInMillis() + "";
        String type = fileDetail.getFileName().substring(fileDetail.getFileName().lastIndexOf(".")+1);
        String path = this.getClass().getResource("/").getPath()
                .replaceFirst("/", "");

        String dateString = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//        path = path.replaceAll("WEB-INF/classes/", "uploadFile/") + dateString+"/"; //服务器用
        path = path.replaceAll("out/artifacts/jhsTest_war_exploded/WEB-INF/classes/",
                "web/uploadFile/")+dateString+"/"; // 测试用
        File file = new File(path+fileName+"."+type);
        File parent = file.getParentFile();
        if(parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try {
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            int read = 0;
            byte [] bytes = new byte[1024];
            while ((read = uploadedInputStream.read(bytes))!= -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            uploadedInputStream.close();
            size = file.length();
            map.put("type", type);
            map.put("url", "uploadFile/"+ dateString+"/"+fileName+"."+type);
            map.put("size",util.transSize(size));
            userMap = util.getJsonMap(0, "success", "上传成功", map);
        } catch (FileNotFoundException e) {
            throw new myException(500, "error", "FileOutputStream error");
        } catch (IOException e) {
            throw new myException(500, "error", "file create error");
        }
        return userMap;
    }

    @GET
    @Path("/log")
    public String TestLog() {
        //level ALL<DEBUG<INFO<WARN<ERROR<FATAL<OFF
        log.debug("This is DEBUG");
//        log.info("This is Info");
        log.warn("This is WARN");
        log.error("This is ERROR");
        return "测试日志";
    }
}
