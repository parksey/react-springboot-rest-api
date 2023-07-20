package com.example.gonggam.space;


import com.example.gonggam.global.testconfig.RepositoryAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("SharedSpaceRepository 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class SharedSpaceRepositoryTest extends RepositoryAnnotation {
}
