package ru.safin.donation.integration.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentGatewayImpl implements PaymentGateway{
    @Override
    public void processToPayment(Payment payment) {
        log.info("Start processing payment through payment gateway");
    }
}
