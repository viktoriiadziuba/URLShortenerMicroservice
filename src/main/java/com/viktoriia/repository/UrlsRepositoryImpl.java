package com.viktoriia.repository;

import com.viktoriia.model.Urls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class UrlsRepositoryImpl implements UrlsRepository {

    private static final String KEY = "Urls";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public UrlsRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<Object, Object> getAllUrls() {
        return hashOperations.entries(KEY);
    }

    @Override
    public Urls add(Urls urls) {
        hashOperations.put(KEY, urls.getShortUrl(), urls.getOriginalUrl());
        return urls;
    }

    @Override
    public String delete(String shortUrl) {
        hashOperations.delete(KEY, shortUrl);
        return "Urls are successfully deleted";
    }

}
