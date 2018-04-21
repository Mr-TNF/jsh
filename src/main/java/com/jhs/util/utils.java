package com.jhs.util;

import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

public class utils<T>  {
    private static  Map jsonMap = new HashMap();
    public Map getJsonMap (Integer code, String status, String msg) {
        jsonMap.put("code", code);
        jsonMap.put("status", status);
        jsonMap.put("msg", msg);
        return jsonMap;
    }
    // List数组
    public Map getJsonMap (Integer code, String status, String msg, List<T> data) {
        jsonMap.put("code", code);
        jsonMap.put("data", data);
        jsonMap.put("status", status);
        jsonMap.put("msg", msg);
        return jsonMap;
    }
    // map对象
    public Map getJsonMap (Integer code, String status, String msg, Map data) {
        jsonMap.put("code", code);
        jsonMap.put("data", data);
        jsonMap.put("status", status);
        jsonMap.put("msg", msg);
        return jsonMap;
    }
    // 实体类对象
    public Map getJsonMap (Integer code, String status, String msg, T data) {
        jsonMap.put("code", code);
        jsonMap.put("data", data);
        jsonMap.put("status", status);
        jsonMap.put("msg", msg);
        return jsonMap;
    }
    // 普通类对象
    public Map getJsonMap (Integer code, String status, String msg, Page data) {
        jsonMap.put("code", code);
        jsonMap.put("data", data);
        jsonMap.put("status", status);
        jsonMap.put("msg", msg);
        return jsonMap;
    }

    // 验空
    public boolean isNull(String [] arr, MultivaluedMap<String, String> object ) {
        boolean flag = false;
        for(int i=0; i<arr.length; i++) {
            try {
                if(object.getFirst(arr[i]).isEmpty() || object.getFirst(arr[i]) == null) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                flag = true;
                break;
            }

        }
        return flag;
    }
    //验空
    public boolean isNull(String [] arr, Map<String, String> object ) {
        boolean flag = false;
        for(int i=0; i<arr.length; i++) {
            if(object.get(arr[i]).isEmpty() || object.get(arr[i]) == null) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
    * @Description:拼接HQL
    * @Param: [t]
    * @return: java.util.Map
    * @Author: TangNengFa
    * @Date: 2018/4/13
    */
    public Map linkHQL(T t) throws Exception {
        String ClassName = t.getClass().getName();
        String HQL = "";
        String sqlName = "";
        String name = "";
        Map map = new HashMap();
        ClassName = ClassName.substring( ClassName.lastIndexOf(".")+1);
        HQL = "from " + ClassName + " q where ";
        Field[] field = t.getClass().getDeclaredFields();
        ArrayList params = new ArrayList();
        for (int i=0, j = 0; j < field.length; j++) {
            sqlName = field[j].getName();
            name = sqlName.substring(0, 1).toUpperCase() + sqlName.substring(1);
            String type = field[j].getGenericType().toString();
            Method m = t.getClass().getMethod("get" + name);
            // 调用getter方法获取属性值
            if (type.equals("class java.lang.String")) {
                String value = (String) m.invoke(t);
                if(value != null && value != "") {
                    HQL =  HQL + "q."+ sqlName + " = ? and ";
                    params.add(value);
                }
            } else if (type.equals("class java.lang.Integer")) {
                Integer value = (Integer) m.invoke(t);
                if(value != null) {
                    HQL =  HQL + "q."+ sqlName + " = ? and ";
                    params.add(value);
                }
            } else if (type.equals("class java.lang.Long")) {
                Long value = (Long) m.invoke(t);
                if(value != null ) {
                    HQL =  HQL + "q."+ sqlName + " = ? and ";
                    params.add(value);
                }
            } else if (type.equals("class java.lang.Double")) {
                Double value = (Double) m.invoke(t);
                if(value != null) {
                    HQL =  HQL + "q."+ sqlName + " = ? and ";
                    params.add(value);
                }
            }else if (type.equals("class java.util.Date")) {
                Date value = (Date) m.invoke(t);
                if(value != null) {
                    HQL =  HQL + "q."+ sqlName + " = ? and ";
                    params.add(value);
                }
            }
        }
        map.put("HQL", HQL);
        map.put("params", params);
        return map;
    }

    /**
     * @Description:转换大小
     * @Param: [t]
     * @return: java.util.Map
     * @Author: TangNengFa
     * @Date: 2018/4/13
     */
    public String transSize(long length) throws myException  {
        int B = 1024;
        int KB = 1024 * 1024;
        int MB = 1024 * 1024 * 1024;
        int GB = 1024 * 1024 * 1024 *1024;
        DecimalFormat df = new DecimalFormat("0.00"); //格式化小数
        String resultString = "";
        if (length < B) {
            resultString = String.valueOf(length) + "B";
        } else if (length  < KB ) {
            resultString = df.format(length / (float) B) + "KB";
        } else if (length < MB) {
            resultString = df.format(length / (float) KB) +  "MB";
        } else if (length < GB) {
            resultString = df.format(length / (float) MB) +  "GB";
        } else {
            resultString = "数目太大";
        }
        return resultString;
    }
}
