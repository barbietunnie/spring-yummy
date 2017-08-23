package com.example.packtpub.yummy.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Bookmark {
    private final UUID uuid;
    private String url;

//    @JsonCreator
    public Bookmark(@JsonProperty("url") String url) {
        this.url = url;
        uuid = null;
    }

    @JsonCreator
    public Bookmark(@JsonProperty("url") String url, @JsonProperty("uuid") UUID uuid) {
        this.uuid = uuid;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Bookmark withUuid(UUID id) {
        return new Bookmark(url, uuid);
    }
}
