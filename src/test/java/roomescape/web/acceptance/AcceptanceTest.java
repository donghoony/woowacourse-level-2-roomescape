package roomescape.web.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import roomescape.core.config.TestConfig;
import roomescape.web.config.WebConfig;

@SpringBootTest(
        classes = {WebConfig.class, TestConfig.class},
        webEnvironment = WebEnvironment.RANDOM_PORT,
        properties = "spring.main.allow-bean-definition-overriding=true"
)
class AcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void initializePort() {
        RestAssured.port = port;
    }
}
