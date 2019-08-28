package com.viktoriia.controller;

import com.viktoriia.dto.Dto;
import com.viktoriia.service.UrlsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UrlsRestController {

    private UrlsService service;

    public UrlsRestController(UrlsService service) {
        this.service = service;
    }

    @RequestMapping(value = "findShortUrl", method = RequestMethod.GET)
    public ResponseEntity<String> getShortUrlBySecretKey(@RequestParam String secretKey) {
        Map<String, String> map = service.findAllKeys();
        String result = map.get(secretKey);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "findOriginalUrl", method = RequestMethod.GET)
    public ResponseEntity<String> getOriginalUrlByShortUrl(@RequestParam String shortUrl) {
        Map<String, String> map = service.findAllUrls();
        String result = map.get(shortUrl);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<String> addUrls(@ModelAttribute(value = "dto") Dto dto) {
        String result = service.saveNewUrls(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteUrls(@RequestParam String secretKey) {
        String result = service.deleteUrlsBySecretKey(secretKey);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
