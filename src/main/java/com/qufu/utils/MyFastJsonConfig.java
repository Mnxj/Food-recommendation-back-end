package com.qufu.utils;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义配置
 * 把返回类转换给json类
 */
@Configuration
public class MyFastJsonConfig {
    /**
     * 产生json对象
     * * @return
     */
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //设置json输出的细节
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(Charset.forName("UTF-8"));
        config.setSerializerFeatures(
                SerializerFeature.WriteClassName,// //是否格式化输出，默认为false
                SerializerFeature.WriteMapNullValue,//Map字段如果为null,输出为[],而非null
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullListAsEmpty,////List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty,// //字符类型字段如果为null,输出为”“,而非null
                SerializerFeature.WriteNullNumberAsZero

        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        // 解决中文乱码问题，相当于在 Controller 上的 @RequestMapping 中加了个属性 produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }
}
