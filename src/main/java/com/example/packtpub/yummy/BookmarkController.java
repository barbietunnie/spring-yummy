package com.example.packtpub.yummy;

import com.example.packtpub.yummy.model.Bookmark;
import com.example.packtpub.yummy.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.UUID;

@RestController
@RequestMapping("bookmark")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;

    @GetMapping("{id}")
    public Resource<Bookmark> getBookmark(@PathVariable UUID id) {
        return new Resource<>(new Bookmark("http://packtpub.com"));
    }
}
