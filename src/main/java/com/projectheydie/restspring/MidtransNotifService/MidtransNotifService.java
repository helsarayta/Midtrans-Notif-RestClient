package com.projectheydie.restspring.MidtransNotifService;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.projectheydie.restspring.MidtransNotifRepo.MidtransNotifRepo;
import com.projectheydie.restspring.entity.MidtransNotif;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MidtransNotifService {

    static String URL_REST = "http://localhost:7777/transactions/status";

    static RestTemplate restTemplate = new RestTemplate();
    static ObjectMapper mapper = new ObjectMapper();


    private final MidtransNotifRepo midtransNotifRepo;

    public MidtransNotifService(MidtransNotifRepo midtransNotifRepo) {
        this.midtransNotifRepo = midtransNotifRepo;
    }

    public void saveMidtransNotif(String transactionId) {
//        String transactionId = null;
        Map<String, String> param = new HashMap<String, String>();
        param.put("transaction_id", transactionId);

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

            midtransNotif.setTransaction_id(midtransNotif.getTransaction_id());
            midtransNotif.setOrder_id(midtransNotif.getOrder_id());
            midtransNotif.setGross_amount(midtransNotif.getGross_amount());
            midtransNotif.setTransaction_status(midtransNotif.getTransaction_status());
            midtransNotif.setFraud_status(midtransNotif.getFraud_status());
            midtransNotif.setStatus_message(midtransNotif.getStatus_message());
            midtransNotif.setPayment_type(midtransNotif.getPayment_type());
            midtransNotif.setStatus_code(midtransNotif.getStatus_code());
            midtransNotif.setSignature_key(midtransNotif.getSignature_key());
            midtransNotif.setTransaction_time(midtransNotif.getTransaction_time());
            midtransNotif.setCurrency(midtransNotif.getCurrency());
            midtransNotif.setMerchant_id(midtransNotif.getMerchant_id());

            midtransNotifRepo.save(midtransNotif);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



    }



}
