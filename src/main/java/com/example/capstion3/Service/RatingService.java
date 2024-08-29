package com.example.capstion3.Service;




import com.example.capstion3.API.APIException;
import com.example.capstion3.Model.Consultant;
import com.example.capstion3.Model.Rating;
import com.example.capstion3.Model.Session;
import com.example.capstion3.Model.User;
import com.example.capstion3.Repository.ConsultantRepository;
import com.example.capstion3.Repository.RatingRepository;
import com.example.capstion3.Repository.SessionRepository;
import com.example.capstion3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {


    private final RatingRepository ratingRepository;
    private final ConsultantRepository consultantRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    public void addRating(Rating rating,Integer consultantId,Integer uId) {
        Consultant consultant=consultantRepository.findConsultantById(consultantId);
        User user=userRepository.findUserById(uId);
        if (consultant==null||user==null) {
            throw new APIException("can not add rating because consultant not found or user not found");
        }else {
            rating.setConsultant(consultant);
            rating.setUser(user);
            ratingRepository.save(rating);
        }
    }

    public void updateRating(Integer id, Rating rating) {
        Rating rating1 = ratingRepository.findRatingById(id);
        if (rating1 == null) {
            throw new APIException("Rating not found");


        }
        rating1.setRating_score(rating.getRating_score());
        rating1.setReview(rating.getReview());

        ratingRepository.save(rating1);

    }

    public void deleteRating(Integer id) {
        Rating rating = ratingRepository.findRatingById(id);
        if (rating == null) {
            throw new APIException("Rating not found");
        }
        ratingRepository.deleteById(id);

    }
//    ######################################8
    public List getByRating(Integer rate) {
        List getAll=new ArrayList<>();
        for (Rating rating : ratingRepository.findAll()) {
            if (rating.getRating_score() == rate) {
                getAll.add(rating);
            }
        }
        if (getAll.isEmpty()){
            throw new APIException("not found by your choice");
        }
        return getAll;
    }
}


