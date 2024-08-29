package com.example.capstion3.Controller;


import com.example.capstion3.Model.Reservation;
import com.example.capstion3.Service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/get")

    public ResponseEntity getReservation() {
        return ResponseEntity.status(200).body(reservationService.getAllReservations());
    }

    @PostMapping("/add/{consultantSId}")
    public ResponseEntity addReservation(@Valid@RequestBody Reservation reservation,@PathVariable Integer consultantSId) {
        reservationService.addReservation(reservation,consultantSId);
        return ResponseEntity.status(200).body("reservation added");

    }
    @GetMapping("/get/service/{serviceId}")
    public ResponseEntity getReservationsByServiceId(@PathVariable Integer serviceId) {
        return ResponseEntity.status(200).body(reservationService.getReservationsByServiceId(serviceId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReservation(@PathVariable Integer id, @Valid@RequestBody Reservation reservation) {
        reservationService.updateReservation(id, reservation);
        return ResponseEntity.status(200).body("Reservation updated");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.status(200).body("Reservation deleted");
    }
}


