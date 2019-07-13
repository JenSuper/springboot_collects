/**
 * All rights Reserved, Designed by www.rongdasoft.com
 *
 * @version: V1.0
 * @Title: JsonUtil.java
 * @Description:
 * @author wangshipeng
 * @date 2019-04-15 16:03
 * @Copyright: 2019-04-15 www.rongdasoft.com Inc. All rights reseved.
 */

package com.jensuper.springboot.exportexcel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


/**
 * @author wangshipeng
 * @ClassName: JsonUtil
 * @Description: JSON序列化类
 * @date 2019-04-15 16:03
 */
@Slf4j
public class JsonUtil {


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
//        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.ORA_DATE_TIMES3_FORMAT));

        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    /**
     * @param obj
     * @return
     * @Description 转换为漂亮格式（格式化，带换行）的json字符串
     * @author wangshipeng
     * @date 2019年04月15日 17:01:47
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }


    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * @param str           待反序列化字符串
     * @param typeReference 指定反序列化成的类型，例如是一个List<User>对象，则传一个new TypeReference<List<User>>(){}
     * @return
     * @Description 字符串转对象 适用于复杂嵌套对象
     * @author wangshipeng
     * @date 2019年04月15日 16:45:39
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * @Description 字符串转对象 适合一层泛型对象的转换
     * @author wangshipeng
     * @date 2019年04月15日 16:48:43
     * @param str 待反序列化字符串
     * @param collectionClass 指定集合类型   例如指定类型为Map<String,Object>   则传Map.class，指定类型为List<User> 则传 List.class
     * @param elementClasses  集合里的类型  例如指定类型为Map<String,Object>  则传String.class,Object.class     指定类型为List<User> 则传 User.class
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }


}

