package com.example.capstion3.Service;



import com.example.capstion3.API.APIException;
import com.example.capstion3.DTO.SubscriptionDTO;
import com.example.capstion3.Model.Subscription;
import com.example.capstion3.Model.User;
import com.example.capstion3.Repository.SubscriptionRepository;
import com.example.capstion3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
 
    //##############Hassan alzahrani
    public void addSubscription(SubscriptionDTO subscriptionDTO) {
        User user = userRepository.findUserById(subscriptionDTO.getUser_id());
        if (user == null) {
            throw new APIException("User not found");
        } else {
            if (subscriptionRepository.findSubscriptionById(subscriptionDTO.getUser_id())==null) {
                LocalDate startDate=LocalDate.now();
                LocalDate endDate=startDate.plusMonths(1);
                Subscription subscription = new Subscription(null, subscriptionDTO.getSubscriptionType(), subscriptionDTO.getEmail_address(), startDate, endDate, user);
                subscriptionRepository.save(subscription);
            }else {
                throw new APIException("to re Subscription use update subscription");
            }
        }
    }

    public void updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription sub = subscriptionRepository.findSubscriptionById(subscriptionDTO.getUser_id());
        if (sub == null) {
            throw new APIException("Subscription not found");

        }
        sub.setSubscriptionType(subscriptionDTO.getSubscriptionType());
        sub.setEmail_address(subscriptionDTO.getEmail_address());
        sub.setStart_date(subscriptionDTO.getStart_date());
        sub.setEnd_date(subscriptionDTO.getEnd_date());
        subscriptionRepository.save(sub);
    }

    public void deleteSubscription(Integer id) {
        Subscription sub = subscriptionRepository.findSubscriptionById(id);
        if (sub == null) {
            throw new APIException("Subscription not found");
        }
        subscriptionRepository.delete(sub);
    }

    //    #####################11
    public List searchSubscription(Integer uid) {
        User user = userRepository.findUserById(uid);
        List howSubscribe = new ArrayList<>();
        if (user == null) {
            throw new APIException("User not found");
        } else {
            if (user.getRole().equalsIgnoreCase("admin")) {
                for (int i = 0; i < userRepository.findAll().size(); i++) {
                    User user1 = userRepository.findUserById(i);
                    if (user1.getSubscription().getSubscriptionType().equalsIgnoreCase("subscribe")) {
                        howSubscribe.add(user1);
                    }
                }
            }
            return howSubscribe;
        }
    }

    //#################################################
    public void checkSubscriptionOrNonSubscription() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        for (Subscription sub : subscriptions) {
            if (sub.getEnd_date().isBefore(LocalDate.now())) {
                sub.setSubscriptionType("non-subscribe");
                subscriptionRepository.save(sub);
            }
        }
    }
}



