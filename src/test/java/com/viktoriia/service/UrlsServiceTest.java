package com.viktoriia.service;

import com.viktoriia.dto.Dto;
import com.viktoriia.model.Urls;
import com.viktoriia.repository.KeysRepositoryImpl;
import com.viktoriia.repository.UrlsRepositoryImpl;
import com.viktoriia.utils.UrlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UrlsServiceTest {

    @Mock
    private UrlsRepositoryImpl urlsRepository;

    @Mock
    private KeysRepositoryImpl keysRepository;

    @Mock
    private UrlUtils urlUtils;

    @InjectMocks
    private UrlsService service = new UrlsService();

    @Test
    public void saveNewUrls() {
        Urls urls = new Urls();
        urls.setShortUrl("shortUrl");

        Dto dto = new Dto();
        dto.setSecretKey("secretKey");
        dto.setOriginalUrl("originalUrl");
        Mockito.when(urlUtils.shortenURL(dto.getOriginalUrl())).thenReturn("shortUrl");

        String expected = "Your short url: " + urls.getShortUrl();
        String actual = service.saveNewUrls(dto);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllKeys() {
        HashMap<Object, Object> keys = new HashMap<>();
        keys.put("secretKey", "shortUrl");
        Mockito.when(keysRepository.getAllKeys()).thenReturn(keys);

        assertEquals(keys, service.findAllKeys());
    }

    @Test
    public void findAllUrls() {
        HashMap<Object, Object> urls = new HashMap<>();
        urls.put("shortUrl", "longUrl");
        Mockito.when(urlsRepository.getAllUrls()).thenReturn(urls);

        assertEquals(urls, service.findAllUrls());
    }

    @Test
    public void deleteUrlsBySecretKey() {
        HashMap<Object, Object> keys = new HashMap<>();
        keys.put("secretKey", "shortUrl");
        Mockito.when(keysRepository.getAllKeys()).thenReturn(keys);

        String secretKey = "secretKey";
        Mockito.when(urlsRepository.delete("shortUrl")).thenReturn("Success");
        Mockito.when(keysRepository.delete(secretKey)).thenReturn("Success");

        String expected = "Success" + " " + "Success";

        assertEquals(expected, service.deleteUrlsBySecretKey(secretKey));
    }
}