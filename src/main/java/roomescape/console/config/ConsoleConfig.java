package roomescape.console.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import roomescape.console.controller.ConsoleNavigator;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"roomescape.console", "roomescape.core"})
public class ConsoleConfig {

    @Bean
    public CommandLineRunner executeOnStartUp(ConsoleNavigator navigator) {
        return args -> navigator.run();
    }
}
