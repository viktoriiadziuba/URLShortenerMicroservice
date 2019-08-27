package com.viktoriia.controller;

import com.viktoriia.dto.Dto;
import com.viktoriia.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UrlsController {

    @Autowired
    UrlsService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String view() {
        return "index";
    }

    @RequestMapping(value = "findShortUrl", method = RequestMethod.GET)
    public @ResponseBody String getShortUrlBySecretKey(@RequestParam String secretKey) {
       Map<String, String> map = service.findAllKeys();
       String result = map.get(secretKey);
       return result;
    }

    @RequestMapping(value = "findOriginalUrl", method = RequestMethod.GET)
    public @ResponseBody String getOriginalUrlByShortUrl(@RequestParam String shortUrl) {
        Map<String, String> map = service.findAllUrls();
        String result = map.get(shortUrl);
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<String> addUrls(@RequestBody Dto dto) {
       String result = service.saveNewUrls(dto);
       return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String deleteUrls(@RequestParam String secretKey) {
        String result = service.deleteUrlsBySecretKey(secretKey);
        return result;
    }
}
