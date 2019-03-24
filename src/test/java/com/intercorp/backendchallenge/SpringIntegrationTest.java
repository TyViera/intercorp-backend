package com.intercorp.backendchallenge;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(classes = BackendChallengeApplication.class,
    webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {

}
