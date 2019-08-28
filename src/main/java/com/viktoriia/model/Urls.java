package com.viktoriia.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Urls implements Serializable {

    private String shortUrl;
    private String originalUrl;
}
