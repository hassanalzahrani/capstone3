package com.example.capstion3.Controller;


import com.example.capstion3.DTO.ConsultantDTO;
import com.example.capstion3.Service.ConsultantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultant")
@RequiredArgsConstructor
public class ConsultantController {
    private final ConsultantService consultantService;


    @GetMapping("/get")
    public ResponseEntity getAllConsultant() {
        return ResponseEntity.status(200).body(consultantService.getAllConsultant());
    }

    @PostMapping("/add/{cId}")
    public ResponseEntity addConsultant(@Valid @RequestBody ConsultantDTO consultantDTO) {
        consultantService.addConsultant(consultantDTO);
        return ResponseEntity.status(200).body("Consultant added");
    }
    @PutMapping("/update")
    public ResponseEntity updateConsultant(@Valid@RequestBody ConsultantDTO consultantDTO) {
        consultantService.updateConsultant(consultantDTO);
        return ResponseEntity.status(200).body("Consultant updated");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCConsultant(@PathVariable Integer id) {
        consultantService.deleteConsultant(id);
        return ResponseEntity.status(200).body("Consultant deleted");
    }

    @GetMapping("/get/by/category/{catId}")
    public ResponseEntity getConsultantByCategory(@PathVariable Integer catId) {
        return ResponseEntity.status(200).body(consultantService.getConsultantByCategory(catId));
    }

    @GetMapping("/get/Consultant/by/id/{cid}")
    public ResponseEntity getConsultantByID(@PathVariable Integer cid) {
        return ResponseEntity.status(200).body(consultantService.getConsultINFOByID(cid));
    }

    @GetMapping("/get/consultant/by/experience/{exYears}")
    public ResponseEntity getConsultantByExperience(@PathVariable Integer exYears) {
        return ResponseEntity.status(200).body(consultantService.getConsultantByExperience_years(exYears));
    }

    @GetMapping("/get/consultant/by/specialization/{specialization}")
    public ResponseEntity getConsultantBySpecialization(@PathVariable String specialization) {
        return ResponseEntity.status(200).body(consultantService.getConsultantsBySpecialization(specialization));
    }

    @GetMapping("/get/top/4")
    public ResponseEntity getTopConsultant() {
        return ResponseEntity.status(200).body(consultantService.getTop4ConsultantsByRating());
    }

    @GetMapping("/get/Consultant/Service/{cId}")
    public ResponseEntity getConsultantByService(@PathVariable Integer cId) {
        return ResponseEntity.status(200).body(consultantService.getConsultantService(cId));
    }

    @GetMapping("/getConsultant/By/{cId}")
    public ResponseEntity getConsultantById(@PathVariable Integer cId) {
        return ResponseEntity.status(200).body(consultantService.getConsultantById(cId));
    }
}


