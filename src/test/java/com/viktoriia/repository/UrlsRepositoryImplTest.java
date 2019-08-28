package com.viktoriia.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UrlsRepositoryImplTest {

    @Mock
    private HashOperations<String, Object, Object> hashOperations;

    @Spy
    private RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

    @InjectMocks
    private UrlsRepositoryImpl urlsRepository = new UrlsRepositoryImpl(redisTemplate);

    @Test
    public void getAllUrls() {
        HashMap<Object, Object> expectedKeys = new HashMap<>();
        expectedKeys.put("shortUrl", "longUrl");
        Mockito.when(hashOperations.entries("Urls")).thenReturn(expectedKeys);

        assertEquals(expectedKeys, urlsRepository.getAllUrls());
    }
}