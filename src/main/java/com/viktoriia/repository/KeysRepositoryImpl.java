package com.viktoriia.repository;

import com.viktoriia.model.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class KeysRepositoryImpl implements KeysRepository {

    private static final String KEY = "Keys";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public KeysRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<Object, Object> getAllUrls() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void add(Keys keys) {
        hashOperations.put(KEY, keys.getKey(), keys.getShortUrl());
    }

    @Override
    public void delete(String key) {
        hashOperations.delete(KEY, key);
    }

}
