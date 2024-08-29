package com.example.capstion3.Controller;

import com.example.capstion3.Model.Consultant_Service;
import com.example.capstion3.Service.Consultant_ServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cs")
@AllArgsConstructor
public class Consultant_ServiceController {
    private final Consultant_ServiceService consultant_serviceService;

    @GetMapping("/getall/{uid}")
    public ResponseEntity getAllConsultants_Services(@PathVariable Integer uid) {
        return ResponseEntity.status(200).body(consultant_serviceService.getAllConsultantService(uid));
    }

    @PostMapping("/add/{cId}")
    public ResponseEntity addNewConsultantService(@Valid @RequestBody Consultant_Service consultant_service,@PathVariable Integer cId){
        consultant_serviceService.addNewConsultantService(consultant_service,cId);
        return ResponseEntity.status(200).body("consultant service add successfully");
    }

    @DeleteMapping("/delete/{csId}")
    public ResponseEntity deleteConsultantService(@PathVariable Integer csId){
        consultant_serviceService.deleteConsultantService(csId);
        return ResponseEntity.status(200).body("consultant service delete successfully");
    }

    @PutMapping("/update/{csId}")
    public ResponseEntity updateConsultantService(@PathVariable Integer csId, @Valid @RequestBody Consultant_Service consultant_service){
        consultant_serviceService.updateConsultantService(consultant_service,csId);
        return ResponseEntity.status(200).body("consultant service update successfully");
    }

    @GetMapping("/get/by/category/name/and/price/{price}/{name}")
    public ResponseEntity getConsultantServiceByCategory(@PathVariable Integer price,@PathVariable String name){
        return ResponseEntity.status(200).body(consultant_serviceService.getByPriceAndCategoryName(price, name));
    }
}
