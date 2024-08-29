package com.example.capstion3.Service;


import com.example.capstion3.API.APIException;
import com.example.capstion3.DTO.SessionDTO;
import com.example.capstion3.Model.Reservation;
import com.example.capstion3.Model.Session;
import com.example.capstion3.Model.User;
import com.example.capstion3.Repository.ReservationRepository;
import com.example.capstion3.Repository.SessionRepository;
import com.example.capstion3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }



//######################10
    public void addNewSession(SessionDTO sessionDTO , Integer consultantId) {
        Reservation reservation=reservationRepository.findReservationById(sessionDTO.getReservation_id());
        if (reservation==null) {
            throw new APIException("can not add session because reservation not found");
        }else {
            if (reservation.getConsultantService().getConsultant().getId()==consultantId){
                Session session=new Session(null,sessionDTO.getSession_date(), sessionDTO.getDuration_minutes(), sessionDTO.getNotes(),sessionDTO.getSeat(),sessionDTO.getSession_link(),null,null,null);
                sessionRepository.save(session);
            }else {
                throw new APIException("can not add session because consultant is not the consultant");
            }
        }
    }
    public void userJoinSession(Integer uId,Integer sessionId){
        User user=userRepository.findUserById(uId);
        if (user==null) {
            throw new APIException("can not join session because user not found");
        }else {
            Session session=sessionRepository.findSessionById(sessionId);
            if (session.getSeat()>0) {
                session.setSeat(session.getSeat() - 1);
                session.setUser(user);
                sessionRepository.save(session);
            }else {
                throw new APIException("can not join session because seat not available");
            }
        }
    }
    public void updateSession(SessionDTO sessionDTO) {

        if (sessionRepository.findSessionById(sessionDTO.getReservation_id())== null) {
            throw new APIException("Session not found");
        }
        Session session1=sessionRepository.findSessionById(sessionDTO.getReservation_id());
        session1.setSession_date(sessionDTO.getSession_date());
        session1.setNotes(sessionDTO.getNotes());
        session1.setDuration_minutes(sessionDTO.getDuration_minutes());
        session1.setSeat(sessionDTO.getSeat());
        sessionRepository.save(session1);
    }

    public void deleteSession(Integer id) {
        Session session = sessionRepository.findSessionById(id);
        if (session == null) {
            throw new APIException("Session not found");
        }
        sessionRepository.delete(session);

    }
}
