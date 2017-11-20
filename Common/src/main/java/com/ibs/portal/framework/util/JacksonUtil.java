package com.ibs.portal.framework.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ibs.common.module.frameworkimpl.exception.JSONException;

/**
 * The class JacksonUtil
 *
 * json字符与对像转换
 * 
 */
public final class JacksonUtil {

    public static ObjectMapper objectMapper;

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class) (2)转换为List,如List
     * <Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     * 
     * @param jsonStr
     * @param valueType
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) throws JSONException {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd");
        objectMapper.setDateFormat(dateFormat);
        T vlaue = null;
        try {
            vlaue = objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new JSONException(e);
        }
        return vlaue;
    }

    /**
     * json数组转List
     * 
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) throws Exception {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper.readValue(jsonStr, valueTypeRef);
    }

    /**
     * 把JavaBean转换为json字符串
     * 
     * @param object
     * @return
     */
    public static String toJSon(Object object) throws Exception {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd");
        objectMapper.setDateFormat(dateFormat);
        String str = null;
        try {
            str = objectMapper.writeValueAsString(object);
        } catch (JsonMappingException e) {
            String canonicalName = object.getClass().getCanonicalName();
            str = toJsonWithFilter(object, canonicalName);
        }

        return str;
    }

    /**
     * 把javaBean过滤掉需要的字段，转换为json字符串
     * 
     * @param object
     * @param filterFields
     * @return
     * @throws Exception
     */
    public static String toJsonWithFilter(Object object, String... filterFields) throws Exception {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        FilterProvider filters = new SimpleFilterProvider().addFilter(object.getClass().getName(),
                SimpleBeanPropertyFilter.serializeAllExcept(filterFields));
        objectMapper.setFilters(filters);
        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            @Override
            public Object findFilterId(AnnotatedClass ac) {
                return ac.getName();
            }
        });
        return objectMapper.writeValueAsString(object);

    }

}