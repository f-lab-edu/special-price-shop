package com.specialpriceshop.order.repository;

import com.specialpriceshop.order.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
