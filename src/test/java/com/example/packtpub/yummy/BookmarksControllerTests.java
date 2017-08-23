package com.example.packtpub.yummy;

import com.example.packtpub.yummy.model.Bookmark;
import com.example.packtpub.yummy.service.BookmarkService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookmarksControllerTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
//    @MockBean
    @SpyBean
    BookmarkService bookmarkService;

    @Test
    public void addBookmark() throws Exception {
        addBookmark();

        Mockito.verify(bookmarkService).addBookmark(Mockito.any(Bookmark.class));
    }

    private void addBookmark(Bookmark value) throws Exception {
        mvc.perform(
                post("/bookmarks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(value))
        ).andExpect(status().isCreated());
    }

    @Test
    public void getAllBookmarks() throws Exception {
        addBookmark(new Bookmark("http://packtpub.com"));
        addBookmark(new Bookmark("http://orchit.de"));

        String result = mvc.perform(
                get("/bookmarks")
        ).andDo(print())
                .andReturn().getResponse().getContentAsString();

        Resources<Bookmark> output = mapper.readValue(result, new TypeReference<Resources<Bookmark>>(){});

        assertTrue(output.getContent().size() >= 2);
        assertTrue(output.getContent().stream().anyMatch(
                bookmark -> bookmark.getUrl().equals("http://orchit.de")
        ));
        assertTrue(output.getContent().stream().anyMatch(
                bookmark -> bookmark.getUrl().equals("http://packtpub.com")
        ));
    }
}
