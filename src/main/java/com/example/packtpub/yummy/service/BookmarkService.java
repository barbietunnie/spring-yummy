package com.example.packtpub.yummy.service;

import com.example.packtpub.yummy.model.Bookmark;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookmarkService {
    private Map<UUID, Bookmark> db = new HashMap<>();

    public UUID addBookmark(Bookmark bookmark) {
        UUID uuid = UUID.randomUUID();
        db.put(uuid, bookmark);
        return uuid;
    }

    public Bookmark find(UUID id) {
        if(db.containsKey(id))
            return db.get(id);

        throw new NoSuchElementException();
    }

    public Collection<Bookmark> findAll() {
        return db.values();
    }
}
