package com.example.capstion3.Service;

import com.example.capstion3.API.APIException;
import com.example.capstion3.Model.*;
import com.example.capstion3.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final ConsultantRepository consultantRepository;
    private final Consultant_RequestRepository consultantRequestRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository UserRepository;
    private final SessionRepository SessionRepository;
    public List<Offer> getAllOffers() {
        if (offerRepository.findAll().isEmpty()){
            throw new APIException("offer not found in DataBase");
        }else {
            return offerRepository.findAll();
        }
    }
    //######################
    public void addNewOffer(Offer offer,Integer consultantID,Integer consultantRQID) {
        Consultant consultant = consultantRepository.findConsultantById(consultantID);
        Consultant_Request consultant_request=consultantRequestRepository.findConsultant_RequestById(consultantRQID);
        if (consultant_request==null||consultant==null){
            throw new APIException("can not add new offer because consultant or consultant request is null");
        }else {
            if (consultant.isStatus()){
                offer.setConsultant(consultant);
                offer.setConsultant_request(consultant_request);
                offerRepository.save(offer);
            }else {
                throw new APIException("can not add new offer because consultant status is false");
            }
        }
    }

    public void updateOffer(Offer offer,Integer id) {
        if (offerRepository.findOfferById(id)==null){
            throw new APIException("offer not found in to updated");
        }else {
            Offer updatedOffer = offerRepository.findOfferById(id);
            updatedOffer.setDescription(offer.getDescription());
            updatedOffer.setPrice(offer.getPrice());
            offerRepository.save(updatedOffer);
        }
    }

    public void deleteOffer(Integer id) {
        if (offerRepository.findOfferById(id)==null){
            throw new APIException("offer not found in to deleted");
        }else {
            offerRepository.deleteById(id);
        }
    }
//###################20
    public List<Offer> getOffersApprovedRecently() {
        List<Offer> allOffers = offerRepository.findAll();
        List<Offer> recentApprovedOffers = new ArrayList<>();
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        for (Offer offer : allOffers) {
            if (offer.isApproved() && offer.getApprovalDate().isAfter(thirtyDaysAgo)) {
                recentApprovedOffers.add(offer);
            }
        }

        return recentApprovedOffers;
    }
//######################
    public void offerApproved(Integer offerId) {
        Offer offer = offerRepository.findOfferById(offerId);
        if (offer==null){
            throw new APIException("Can not approved this offer");
        }
        Consultant_Request consultant_request=consultantRequestRepository.findConsultant_RequestById(offer.getConsultant_request().getId());
        Reservation reservation=new Reservation(null,offer.getApprovalDate(),consultant_request.getStatus(),consultant_request,null,null,null);
        offer.setApproved(true);
        offerRepository.save(offer);
        reservationRepository.save(reservation);
    }
}
