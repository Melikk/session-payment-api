package com.example.sessionpaymentapi.config;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private final Access access = new Access();
    private final Refresh refresh = new Refresh();
    private int attemptedLogins;
    private int lockTime;

    public Access getAccess() {
        return access;
    }

    public Refresh getRefresh() {
        return refresh;
    }

    @Getter
    @Setter
    public static class Access {
        private String secret;
        private int lifeTime; // в минутах

        public Duration getDuration() {
            return Duration.ofMinutes(lifeTime);
        }
    }

    @Getter
    @Setter
    public static class Refresh {
        private String secret;
        private int lifeTime; // в минутах

        public Duration getDuration() {
            return Duration.ofMinutes(lifeTime);
        }
    }
}
