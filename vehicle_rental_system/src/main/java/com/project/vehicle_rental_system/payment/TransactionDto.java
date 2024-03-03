package com.project.vehicle_rental_system.payment;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    private Integer customerId;
    private Integer bookingId;
    private Integer adminId;
}
