package ru.safin.donation.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("application.domain")
public class DomainProperties {

    private Payouts payouts;

    private DonationSettings donationSettings;

    @Data
    public static class Payouts {
        private Integer maxCountOfMethods;
        private List<String> methods;
    }

    @Data
    public static class DonationSettings {
        private Map<String, Integer> messages;
        private List<String> spamFilters;
    }
}
