package com.viktoriia.service;

import com.viktoriia.dto.Dto;
import com.viktoriia.model.Keys;
import com.viktoriia.model.Urls;
import com.viktoriia.repository.KeysRepositoryImpl;
import com.viktoriia.repository.UrlsRepositoryImpl;
import com.viktoriia.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UrlsService {

    @Autowired
    UrlsRepositoryImpl urlsRepository;

    @Autowired
    KeysRepositoryImpl keysRepository;

    public String saveNewUrls(Dto dto) {
        Urls urls = new Urls();
        urls.setOriginalUrl(dto.getOriginalUrl());
        urls.setShortUrl(encode(dto.getOriginalUrl()));
        urlsRepository.add(urls);

        Keys keys = new Keys();
        keys.setKey(dto.getSecretKey());
        keys.setShortUrl(encode(dto.getOriginalUrl()));
        keysRepository.add(keys);
        return "Your short url: " + urls.getShortUrl();
    }

    public Map<String, String> findAllKeys() {
        Map<Object, Object> keys = keysRepository.getAllUrls();
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<Object, Object> entry : keys.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, keys.get(key).toString());
        }
        return map;
    }

    public Map<String, String> findAllUrls() {
        Map<Object, Object> urls = urlsRepository.getAllUrls();
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<Object, Object> entry : urls.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, urls.get(key).toString());
        }
        return map;
    }

    public String deleteUrlsBySecretKey(String secretKey) {
        Map<String, String> keys = findAllKeys();
        String shortUrl = keys.get(secretKey);
        urlsRepository.delete(shortUrl);
        keysRepository.delete(secretKey);
        return "Urls are successfully deleted";
    }

    public String encode(String longUrl) {
        UrlUtils urlUtils = new UrlUtils();
       return urlUtils.shortenURL(longUrl);
    }
}
