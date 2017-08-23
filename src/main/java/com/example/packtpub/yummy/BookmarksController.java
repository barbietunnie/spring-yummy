package com.example.packtpub.yummy;

import com.example.packtpub.yummy.model.Bookmark;
import com.example.packtpub.yummy.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("bookmarks")
public class BookmarksController {
    @Autowired
    BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<Void> addBookmark(@RequestBody Bookmark bookmark) {
        bookmarkService.addBookmark(bookmark);
//        return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity.created(
                BasicLinkBuilder.linkToCurrentMapping()
                                .slash("bookmark")
                                .slash(UUID.randomUUID())
                                .toUri()
        ).build();
    }
}
