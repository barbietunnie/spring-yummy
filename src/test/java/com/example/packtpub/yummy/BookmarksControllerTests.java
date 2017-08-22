package com.example.packtpub.yummy;

import com.example.packtpub.yummy.model.Bookmark;
import com.example.packtpub.yummy.service.BookmarkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookmarksControllerTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    BookmarkService bookmarkService;

    @Test
    public void addBookmark() throws Exception {
        mvc.perform(
                post("/bookmarks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(new Bookmark("http://packtpub.com")))
        ).andExpect(status().isCreated());

        Mockito.verify(bookmarkService).addBookmark(Mockito.any(Bookmark.class));
    }
}
