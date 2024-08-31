package com.example.capstion3.Controller;


import com.example.capstion3.Model.Consultant;
import com.example.capstion3.Model.User;
import com.example.capstion3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUser() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(200).body("user added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id ,@Valid @RequestBody User user) {
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body("user updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("user deleted");
    }

    @PostMapping("/assign/user/{uId}/To/reservation/{rId}")
    public ResponseEntity assignUserToReservation(@PathVariable Integer uId, @PathVariable Integer rId) {
        userService.assignUserToReservation(uId,rId);
        return ResponseEntity.status(200).body("user assigned to reservation");
    }


    @PostMapping("/admin/Aproved/Consultant/{adminId}/{consultantID}")
    public ResponseEntity approvedConsultant(@PathVariable Integer adminId, @PathVariable Integer consultantID) {
        userService.adminAprovedConsultant(adminId,consultantID);
        return ResponseEntity.status(200).body("approved consultant successfully");
    }

    @GetMapping("/getStatistics")
    public ResponseEntity getStatistics() {
        return ResponseEntity.status(200).body(userService.getStatistics());
    }

    @GetMapping("/get/by/status/false/{adminId}")
    public ResponseEntity getByStatusFalse(@PathVariable Integer adminId) {
        return ResponseEntity.status(200).body(userService.getConsultantNotApprovedByAdmin(adminId));
    }
}
