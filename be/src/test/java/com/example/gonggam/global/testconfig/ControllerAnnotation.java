package com.example.gonggam.global.testconfig;

import com.example.gonggam.GonggamApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(classes = GonggamApplication.class)
@AutoConfigureMockMvc
public class ControllerAnnotation {

    private static String API_VERSION = "/api/v1";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String getUrl(String url) {
        return API_VERSION + url;
    }
}
