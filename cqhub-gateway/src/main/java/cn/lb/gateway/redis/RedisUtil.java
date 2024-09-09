package cn.lb.gateway.redis;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author RainSoul
 * @create 2024-09-09
 */
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String CACHE_KEY_SEPARATOR = ".";

    /**
     * 构建缓存键字符串
     * 该方法接受可变参数的字符串数组，并将其连接成一个单一的字符串，用作缓存键
     * 缓存键是由各个字符串部分通过特定的分隔符连接起来的
     *
     * @param strObjs 可变参数的字符串数组，用于构建缓存键
     * @return 返回连接后的字符串，用作缓存的键
     */
    public String buildKey(String... strObjs) {
        // 使用Stream API将字符串数组连接成一个单一的字符串，之间使用CACHE_KEY_SEPARATOR分隔
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }

    /**
     * 将给定的键值对存储在Redis中
     *
     * @param key   要存储的键，通常用于唯一标识该数据
     * @param value 要存储的值，可以是任何类型的对象
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 判断Redis中指定的key是否存在
     *
     * @param key 要判断的key
     * @return 如果key存在，则返回true；否则返回false
     */
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 删除Redis中的指定键
     *
     * @param key 要删除的键
     * @return 删除操作的结果，true表示删除成功，false表示删除失败或键不存在
     */
    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 将键值对添加到Redis中，如果键已存在，则不进行任何操作
     *
     * @param key 要添加到Redis中的键
     * @param value 与键关联的值
     * @param time 键的过期时间
     * @param timeUtil 时间单位，用于解释过期时间的时间单位
     * @return 如果键以前不存在并且操作成功，则返回true；如果键已存在，则返回false
     */
    public boolean setNx(String key, String value, Long time, TimeUnit timeUtil){
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUtil);
    }

    /**
     * 根据指定的键从Redis中获取对应的值
     *
     * @param key 要获取值的键
     * @return 键对应的值，如果键不存在则返回null
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 向Redis的有序集合中添加元素
     * 有序集合是集合的一个变种，它为集合中的每个元素都关联了一个分数值
     * 此操作可以确保元素在集合中的顺序被分数值所定义
     *
     * @param key   有序集合的键
     * @param value 待添加的元素值
     * @param score 待添加元素的分数，用于排序
     * @return 添加操作的结果，true表示成功添加，false表示添加失败
     */
    public Boolean zAdd(String key, Object value, Long score) {
        // 将长整型的score转换为双精度型并添加到有序集合中
        return redisTemplate.opsForZSet().add(key, value, Double.valueOf(String.valueOf(score)));
    }

    /**
     * 计算给定排序集合(key)的成员数量
     *
     * @param key 排序集合的键
     * @return 排序集合中成员的数量
     */
    public Long countZset(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 从有序集合中按索引获取指定范围内的元素
     *
     * @param key 有序集合的键
     * @param start 起始索引
     * @param end 结束索引
     * @return 包含指定范围内元素的集合
     */
    public Set<String> rangeZset(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 从有序集合中移除指定成员
     *
     * @param key   有序集合的键
     * @param value 要移除的成员对象
     * @return 移除成员后的结果，返回移除的成员数量，如果返回0表示成员不存在或没有被移除
     */
    public Long removeZset(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 从Redis的有序集合中批量删除成员
     *
     * @param key   有序集合的键
     * @param value 待删除的成员集合
     */
    public void removeZsetList(String key, Set<String> value) {
        // 对每个值执行删除操作
        value.stream().forEach((val) -> redisTemplate.opsForZSet().remove(key, val));
    }

    /**
     * 获取有序集合中指定成员的分数
     *
     * @param key   有序集合的键
     * @param value 集合中成员的对象表示
     * @return      成员的分数，如果成员不存在则返回null
     */
    public Double score(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }


    /**
     * 根据分数范围获取有序集合中的元素
     * 此方法使用RedisTemplate的opsForZSet方法来操作Redis中的有序集合
     * 它根据指定的分数范围返回有序集合中符合条件的所有元素
     *
     * @param key   Redis中有序集合的键
     * @param start 起始分数（包含）
     * @param end   结束分数（包含）
     * @return 返回符合条件的元素集合如果找不到这样的元素，则返回一个空集合
     */
    public Set<String> rangeByScore(String key, long start, long end) {
        // 将long类型的start和end转换为Redis有序集合操作所需的double类型
        return redisTemplate.opsForZSet().rangeByScore(key, Double.valueOf(String.valueOf(start)), Double.valueOf(String.valueOf(end)));
    }

    /**
     * 增加分数
     *
     * @param key   排序集合的键
     * @param obj   排序集合中的对象
     * @param score 要增加的分数
     * @return 增加后的分数
     */
    public Object addScore(String key, Object obj, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, obj, score);
    }


    /**
     * 获取有序集合中元素的排名
     *
     * @param key 缓存的键，用于标识特定的有序集合
     * @param obj 集合中的元素，可以是任何实现了可比较接口的对象
     * @return 返回元素在集合中的排名，排名按照升序排列；如果元素不存在于集合中，则返回null
     */
    public Object rank(String key, Object obj) {
        return redisTemplate.opsForZSet().rank(key, obj);
    }
}
