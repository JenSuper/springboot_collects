package com.jensuper.springboot.redis;

import com.jensuper.springboot.VO.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: RedisTest
 * @Description:
 * @author:jichao
 * @date: 2019/5/30
 * @Copyright: 2019/5/30 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增字符串类型，设置过期时间为15分钟
     */
    @Test
    public void testStr(){
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("1", "小明",900, TimeUnit.SECONDS);
        Assert.assertEquals("小明", opsForValue.get("1"));
    }

    /**
     * 新增对象
     */
    @Test
    public void testVO(){
        User user = new User();
        user.setId(1);
        user.setName("小明明明");
        redisTemplate.opsForValue().set("user", user,900, TimeUnit.SECONDS);
        Assert.assertEquals(user, redisTemplate.opsForValue().get("user"));
    }
}
