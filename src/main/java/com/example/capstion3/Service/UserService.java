package com.example.capstion3.Service;


import com.example.capstion3.API.APIException;
import com.example.capstion3.Model.Consultant;
import com.example.capstion3.Model.Reservation;
import com.example.capstion3.Model.Session;
import com.example.capstion3.Model.User;
import com.example.capstion3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ConsultantRepository consultantRepository;
    private final ReservationRepository reservationRepository;
    private final SessionRepository sessionRepository;
    private final OfferRepository offerRepository;
    private final Consultant_RequestRepository consultantRequestRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void updateUser(Integer id,User user){
        User u = userRepository.findUserById(id);
        if(u == null){
            throw new APIException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setRole(user.getRole());
        userRepository.save(u);
    }
    public void deleteUser(Integer id){
        User u = userRepository.findUserById(id);
        if(u == null){
            throw new APIException("User not found");
        }
        userRepository.delete(u);
    }
//    #######13
    public void adminAprovedConsultant(Integer adminId, Integer cId){
        User u = userRepository.findUserById(adminId);
        if (u==null){
            throw new APIException("User not found");
        }else {
            if (u.getRole().equalsIgnoreCase("admin")) {
                Consultant consultant = consultantRepository.findConsultantById(cId);
                if (consultant == null) {
                    throw new APIException("Consultant not found");
                }else {

                    consultant.setStatus(true);
                    consultantRepository.save(consultant);
                }
            }else {
                throw new APIException("You are not an admin");
            }
        }
    }
//    ==============14
    public void assignUserToReservation(Integer uid,Integer rId){
        User u = userRepository.findUserById(uid);
        Reservation r=reservationRepository.findReservationById(rId);
        if (u==null||r==null){
            throw new APIException("can not assign user to reservation");
        }else {
            u.setReservation(r);
            userRepository.save(u);
        }
    }
//    #################
    public List getStatistics(){
        List statistics=new ArrayList();
        statistics.add("consultant: "+consultantRepository.countApprovedConsultants());
        statistics.add("offer"+offerRepository.countApprovedOffers());
        statistics.add("consultant Request: "+consultantRequestRepository.countConsultantRequests());
        return statistics;
    }
//    ######################
    public List getAllUserSubscribe(Integer adminId) {
        List subscribe = new ArrayList();
        User u = userRepository.findUserById(adminId);
        if (u == null) {
            throw new APIException("User not found");
        } else {
            if (u.getRole().equalsIgnoreCase("admin")) {
                for (User user : userRepository.findAll()) {
                    if (user.getSubscription().getSubscriptionType().equalsIgnoreCase("subscribe")) {
                        subscribe.add(user);
                    }
                }
                return subscribe;
            } else {
                throw new APIException("You are not admin");
            }
        }
    }
//    ########################
    public List getConsultantNotApprovedByAdmin(Integer adminId) {
        List notApproved = new ArrayList();
        User u = userRepository.findUserById(adminId);
        if (u == null) {
            throw new APIException("User not found");
        }else {
            if (u.getRole().equalsIgnoreCase("admin")) {
                for (Consultant consultant : consultantRepository.findAll()) {
                    if (!consultant.isStatus()){
                        notApproved.add(consultant);
                    }
                }
            }
            return notApproved;
        }
    }


}
