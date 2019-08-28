package com.viktoriia.repository;

import com.viktoriia.model.Urls;

import java.util.Map;

public interface UrlsRepository {

    Map<Object, Object> getAllUrls();

    Urls add(Urls urls);

    String delete(String id);

}
