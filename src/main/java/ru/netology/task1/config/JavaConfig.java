package ru.netology.task1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import ru.netology.task1.service.DevProfile;
import ru.netology.task1.service.ProductionProfile;
import ru.netology.task1.service.SystemProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
