package com.example.packtpub.yummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerRestTemplateTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void sayTheTime() throws Exception {
        String result1 = restTemplate.getForObject("/", String.class);
        String result2 = restTemplate.getForObject("/", String.class);

        assertNotEquals(result1, result2);
    }

    @Test
    public void sayTheTimeMany() throws Exception {
        String result = restTemplate.getForObject("/many?name=Sasha&repetitions=3", String.class);

        assertThat(result, containsString("Sasha"));
        assertThat(result, containsString("3."));
        assertThat(result, not(containsString("4.")));
    }

    @Test
    public void sayTheTimeManyParams() throws Exception {
        String result = restTemplate.getForObject("/manyParams?name=Sasha&repetitions=3", String.class);

        assertThat(result, containsString("Sasha"));
        assertThat(result, containsString("3."));
        assertThat(result, not(containsString("4.")));
    }

    @Test
    public void sayTheTimeManyParamsPathFail() throws Exception {
        String result = restTemplate.getForObject("/many/John/5", String.class, "name=Sasha", "repetitions=3");

        assertThat(result, containsString("Sasha"));
        assertThat(result, containsString("3."));
        assertThat(result, not(containsString("4.")));
    }

    /*@Test
    public void sayTheTimeManyParamsPathFromParams() throws Exception {
        mvc.perform(
                get("/many/{name}/{repetitions}", "John", "5")
                        .param("name", "Sasha")
                        .param("repetitions", "3")
        ).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")))
                .andExpect(content().string(not(containsString("4."))));
    }

    @Test
    public void sayTheTimeManyParamsPathFromPath() throws Exception {
        mvc.perform(
                get("/many2/{name}/{repetitions}", "John", "5")
        ).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("John")))
                .andExpect(content().string(containsString("5.")))
                .andExpect(content().string(not(containsString("6."))));
    }

    @Test
    public void sayTheTimeManyPathExplicit() throws Exception {

    }

    @Autowired
    ObjectMapper mapper;
    @Test
    public void sayTheTimeManyParamsPost() throws Exception {
        mvc.perform(
                post("/manyParams")
                .param("name", "Susan")
                .param("repetitions", "3")
                .content(mapper.writeValueAsString(new SampleController.Params("name", 5)))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().is2xxSuccessful())
         .andExpect(content().string(containsString("John")))
         .andExpect(content().string(containsString("5.")))
         .andExpect(content().string(not(containsString("6."))));
    }*/
}
