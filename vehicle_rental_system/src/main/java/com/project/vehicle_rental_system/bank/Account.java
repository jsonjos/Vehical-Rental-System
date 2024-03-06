package com.project.vehicle_rental_system.bank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="Bank_Details")
public class Account {
    @Id
    @Column(name="Bank_ID")
    private Integer bankId;

    @Column(name="Bank_Password")
    private String bankPassword;

    @Column(name="Bank_Balance")
    private Double bankBalance;

}

