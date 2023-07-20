package com.example.gonggam.global.testconfig;

import com.example.gonggam.GonggamApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(classes = GonggamApplication.class)
@AutoConfigureMockMvc
public interface ControllerAnnotation{
}
