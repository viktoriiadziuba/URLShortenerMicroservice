package com.viktoriia.repository;

import com.viktoriia.model.Urls;

import java.util.Map;

public interface UrlsRepository {

    Map<Object, Object> getAllUrls();

    void add(Urls urls);

    void delete(String id);

}
