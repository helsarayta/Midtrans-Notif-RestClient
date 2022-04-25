package com.projectheydie.restspring.RestClient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.projectheydie.restspring.entity.MidtransNotif;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestClient {

    static String URL_REST = "http://localhost:7777/transactions/status";

    static RestTemplate restTemplate = new RestTemplate();
    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        callGetStatusByTransactionId();
    }

    public static void callGetStatusByTransactionId(){
        Map<String, String> param = new HashMap<String, String>();
        param.put("transaction_id", "aa71d5ee-8e62-4287-a477-db763df7d010");

        String notif = restTemplate.postForObject(URL_REST, param, String.class);

        try {
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            MidtransNotif midtransNotif = mapper.readValue(notif, new TypeReference<MidtransNotif>() {
            });
            System.out.println("transaction_id: " + midtransNotif.getTransaction_id());
            System.out.println("order_id: " + midtransNotif.getOrder_id());
            System.out.println("gross_amount: " + midtransNotif.getGross_amount());
            System.out.println("transaction_status: " + midtransNotif.getTransaction_status());
            System.out.println("fraud_status: " + midtransNotif.getFraud_status());
            System.out.println("status_message: " + midtransNotif.getStatus_message());
            System.out.println("payment_type: " + midtransNotif.getPayment_type());
            System.out.println("status_code: " + midtransNotif.getStatus_code());
            System.out.println("signature_key: " + midtransNotif.getSignature_key());
            System.out.println("transaction_time: " + midtransNotif.getTransaction_time());
            System.out.println("currency: " + midtransNotif.getCurrency());
            System.out.println("merchant_id: " + midtransNotif.getMerchant_id());


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//        try {
//            MidtransNotif midtransNotif = mapper.readValue(notif, new TypeReference<MidtransNotif>() {
//            });
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }


    }

