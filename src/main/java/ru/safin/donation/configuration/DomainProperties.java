package ru.safin.donation.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@ConfigurationProperties("application.domain")
@Data
public class DomainProperties {
    private PayoutSettings payoutsSettings;
    private DonationSettings donationSettings;
    private UserSettings UserSettings;

    @Data
    public static class PayoutSettings {
        private BigDecimal balance;
    }

    @Data
    public static class DonationSettings {
        private BigDecimal minSum;
        private BigDecimal maxSum;
        private Integer maxCountOfSymbols;
        private Boolean isRemoveLinks;
        private Boolean isMessageModerate;
    }

    @Data
    public static class UserSettings {
        private String currency;
        private BigDecimal percentFee;
        private String timezone;
        private String language;
    }
}
