package com.jersey;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.logging.LoggingFeature;

public class applicationConfig extends ResourceConfig {
    public applicationConfig() {
        packages("com.jhs"); //package指定了Jersey要扫描的包
        register(LoggingFeature.class); // 注册日志
        register(JacksonJsonProvider.class); //注册JSON转换器
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
        register(RequestContextFilter.class); //RequestContextFilter是集成Spring的关键
    }
}