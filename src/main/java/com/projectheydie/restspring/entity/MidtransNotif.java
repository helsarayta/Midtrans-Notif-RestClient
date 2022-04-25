package com.projectheydie.restspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MidtransNotif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payment_type;
    private String transaction_id;
    private String order_id;
    private String gross_amount;
    private String transaction_status;
    private String fraud_status;
    private String signature_key;
    private String status_message;
    private String status_code;
    private String transaction_time;
    private String currency;
    private String merchant_id;

}
