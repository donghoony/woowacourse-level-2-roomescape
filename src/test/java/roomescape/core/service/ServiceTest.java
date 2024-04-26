package roomescape.core.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import roomescape.core.config.TestConfig;
import roomescape.web.config.WebConfig;

//@SpringBootTest(
//        classes = {WebConfig.class, TestConfig.class},
//        webEnvironment = SpringBootTest.WebEnvironment.NONE,
//        properties = "spring.main.allow-bean-definition-overriding=true"
//)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class, TestConfig.class})
class ServiceTest {
}
