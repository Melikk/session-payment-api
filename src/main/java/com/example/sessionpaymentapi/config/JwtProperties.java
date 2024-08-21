package com.example.sessionpaymentapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final Access access = new Access();
    private final Refresh refresh = new Refresh();

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
