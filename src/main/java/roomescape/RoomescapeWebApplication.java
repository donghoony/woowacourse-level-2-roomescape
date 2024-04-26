package roomescape;

import org.springframework.boot.SpringApplication;
import roomescape.web.config.WebConfig;

public class RoomescapeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebConfig.class, args);
    }
}
