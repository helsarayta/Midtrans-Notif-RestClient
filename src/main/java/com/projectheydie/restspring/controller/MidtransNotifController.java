package com.projectheydie.restspring.controller;

import com.projectheydie.restspring.MidtransNotifService.MidtransNotifService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/midtrans")
public class MidtransNotifController {

    private MidtransNotifService midtransNotifService;

    public MidtransNotifController(MidtransNotifService midtransNotifService) {
        this.midtransNotifService = midtransNotifService;
    }

    @PostMapping("/notif")
    public ResponseEntity<String> getMidtransNotif(@RequestParam("transaction_id") String transactionId) {
        midtransNotifService.saveMidtransNotif(transactionId);
        return ResponseEntity.ok("Save Success");
    }
}
