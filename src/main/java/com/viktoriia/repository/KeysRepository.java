package com.viktoriia.repository;

import com.viktoriia.model.Keys;

import java.util.Map;

public interface KeysRepository {

    Map<Object, Object> getAllKeys();

    Keys add(Keys keys);

    String delete(String id);

}
