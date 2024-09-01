package com.example.capstion3.Controller;

import com.example.capstion3.Model.Consultant_Request;
import com.example.capstion3.Service.Consultant_RequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cq")
@AllArgsConstructor
public class Consultant_RequestController {
    private final Consultant_RequestService consultant_requestService;
    ///Hassan alzahrani
    @GetMapping("/get")
    public ResponseEntity getALlConsultantRequest() {
        return ResponseEntity.status(200).body(consultant_requestService.getAllConsultant_Requests());
    }

    @PostMapping("/add/{cqId}")
    public ResponseEntity addConsultantRequest( @PathVariable Integer cqId,@Valid @RequestBody Consultant_Request consultant_request) {
        consultant_requestService.addNewConsultantRequest( cqId,consultant_request);
        return ResponseEntity.status(200).body("consultant request add successfully");
    }
    ///Hassan alzahrani
    @PutMapping("/update/{cqId}")
    public ResponseEntity updateConsultantRequest(@Valid @RequestBody Consultant_Request consultant_request, @PathVariable Integer cqId) {
        consultant_requestService.updateConsultantRequest(consultant_request, cqId);
        return ResponseEntity.status(200).body("consultant request update successfully");
    }
    ///Hassan alzahrani

    @DeleteMapping("/delete/{cqId}")
    public ResponseEntity deleteConsultantRequest(@PathVariable Integer cqId) {
        consultant_requestService.deleteConsultantRequest(cqId);
        return ResponseEntity.status(200).body("consultant request delete successfully");
    }

    @GetMapping("/user/approve/{uId}/{offerId}/{cqId}")
    public ResponseEntity userAddApproveToRequest(@PathVariable Integer uId, @PathVariable Integer offerId, @PathVariable Integer cqId) {
        consultant_requestService.UserAddApprovedService(uId, offerId, cqId);
        return ResponseEntity.status(200).body("approved user add successfully");
    }


}
