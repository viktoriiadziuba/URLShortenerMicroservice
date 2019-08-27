package com.viktoriia.repository;

import com.viktoriia.model.Keys;

import java.util.Map;

public interface KeysRepository {

    Map<Object, Object> getAllUrls();

    void add(Keys keys);

    void delete(String id);

}
