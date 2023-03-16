package ru.safin.donation.integration.external;

public interface PaymentGateway {
    void processToPayment(Payment payment);
}
