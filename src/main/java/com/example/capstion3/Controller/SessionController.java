package com.example.capstion3.Controller;



import com.example.capstion3.DTO.SessionDTO;
import com.example.capstion3.Model.Session;
import com.example.capstion3.Service.SessionService;
import com.example.capstion3.Service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;


    @GetMapping("/get")
    public ResponseEntity getAllSession() {
        return ResponseEntity.status(200).body(sessionService.getAllSession());
    }

    @PostMapping("/add/{consultantId}")
    public ResponseEntity addSession(@Valid @RequestBody SessionDTO sessionDTO,@PathVariable Integer consultantId) {
        sessionService.addNewSession(sessionDTO,consultantId);
        return ResponseEntity.status(200).body("Session added");
    }
    @PutMapping("/update")
    public ResponseEntity updateSession( @Valid@RequestBody SessionDTO sessionDTO) {
        sessionService.updateSession(sessionDTO);
        return ResponseEntity.status(200).body("Session updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSession(@PathVariable Integer id) {
        sessionService.deleteSession(id);
        return ResponseEntity.status(200).body("Session deleted");
    }



    @PostMapping("/user/join/session/{uId}/{sessionId}")
    public ResponseEntity joinSession(@PathVariable Integer uId, @PathVariable Integer sessionId) {
        sessionService.userJoinSession(uId, sessionId);
        return ResponseEntity.status(200).body("Session joined successfully");
    }
}




