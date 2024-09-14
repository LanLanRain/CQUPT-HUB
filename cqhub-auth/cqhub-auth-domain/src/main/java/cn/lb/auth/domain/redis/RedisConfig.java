package cn.lb.auth.domain.redis;

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
 * @author RainSoul
 * @create 2024-09-14
 */
@Configuration
public class RedisConfig {

    /**
     * 配置并返回一个 RedisTemplate 实例
     * 该方法用于配置 Redis 模板，设置连接工厂和序列化方式
     *
     * @param redisConnectionFactory Redis 连接工厂，用于建立与 Redis 服务器的连接
     * @return 配置好的 RedisTemplate<String, Object> 实例，用于操作 Redis
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建 RedisTemplate 实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用 StringRedisSerializer 作为键的序列化方式
        RedisSerializer<String>  redisSerializer = new StringRedisSerializer();

        // 设置 Redis 连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置键的序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        // 设置 Hash 键的序列化方式
        redisTemplate.setHashKeySerializer(redisSerializer);
        // 设置值的序列化方式为 JSON
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        // 设置 Hash 值的序列化方式为 JSON
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());

        // 返回配置好的 RedisTemplate 实例
        return redisTemplate;
    }

    /**
     * 创建一个 Jackson2JsonRedisSerializer 序列化器，用于将对象序列化为 JSON 并存储在 Redis 中。
     * 该方法配置了对象映射器（ObjectMapper）以确保序列化和反序列化过程的灵活性和安全性。
     * 特别是，它允许未知属性而不抛出错误，并启用了默认类型信息，这有助于处理不同类型的对象。
     *
     * @return 配置好的 Jackson2JsonRedisSerializer 实例，用于 Redis 的数据序列化和反序列化。
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        // 创建一个通用的 Jackson2JsonRedisSerializer 实例，用于所有类型的对象。
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 创建一个 ObjectMapper 实例，用于配置 JSON 处理行为。
        ObjectMapper objectMapper = new ObjectMapper();

        // 配置可见性，使所有属性都可以被序列化和反序列化。
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 配置对象映射器，在反序列化时，即使遇到未知属性也不抛出错误。
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 启用默认类型信息，提高反序列化时的类型安全。
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // 设置对象映射器到序列化器，应用上述配置。
        jsonRedisSerializer.setObjectMapper(objectMapper);

        // 返回配置好的序列化器实例。
        return jsonRedisSerializer;
    }

}
