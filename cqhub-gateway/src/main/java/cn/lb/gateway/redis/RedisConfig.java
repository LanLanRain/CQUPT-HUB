package cn.lb.gateway.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 *
 * @author RainSoul
 * @create 2024-09-09
 */
// 配置类，用于配置Redis的相关设置
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate，用于Redis的数据操作
     *
     * @param redisConnectionFactory Redis连接工厂，用于建立Redis连接
     * @return 配置好的RedisTemplate对象，用于Redis的数据读写
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建RedisTemplate实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用StringRedisSerializer进行序列化
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        // 设置Redis连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置键的序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        // 设置值的序列化方式
        redisTemplate.setValueSerializer(redisSerializer);
        // 设置Hash键的序列化方式
        redisTemplate.setHashKeySerializer(redisSerializer);
        // 设置Hash值的序列化方式
        redisTemplate.setHashValueSerializer(redisSerializer);

        // 返回配置好的RedisTemplate实例
        return redisTemplate;
    }


    /**
     * 配置Jackson2JsonRedisSerializer，用于Redis中JSON数据的序列化和反序列化
     *
     * @return 配置好的Jackson2JsonRedisSerializer对象，用于JSON数据的处理
     */
    public Jackson2JsonRedisSerializer<Object> createJackson2JsonRedisSerializer() {
        // 创建一个Object类型的Jackson2JsonRedisSerializer实例
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 创建一个ObjectMapper实例，用于配置JSON和Java对象之间的转换
        ObjectMapper objectMapper = new ObjectMapper();

        // 配置ObjectMapper，设置所有属性的可见性，使得所有属性都可以被序列化和反序列化
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 配置ObjectMapper，在反序列化时，对于JSON中存在但Java对象中不存在的属性不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 启用默认类型信息处理，用于在反序列化时识别Java对象的类型
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // 设置ObjectMapper到Jackson2JsonRedisSerializer，以实现自定义的序列化和反序列化行为
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 返回配置好的Jackson2JsonRedisSerializer实例
        return jackson2JsonRedisSerializer;
    }

}
