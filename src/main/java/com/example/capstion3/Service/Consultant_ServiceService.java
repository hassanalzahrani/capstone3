package com.example.capstion3.Service;

import com.example.capstion3.API.APIException;
import com.example.capstion3.Model.Consultant;
import com.example.capstion3.Model.Consultant_Service;
import com.example.capstion3.Model.User;
import com.example.capstion3.Repository.ConsultantRepository;
import com.example.capstion3.Repository.Consultant_ServiceRepository;
import com.example.capstion3.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class Consultant_ServiceService {
    private final Consultant_ServiceRepository consultant_ServiceRepository;
    private final ConsultantRepository consultantRepository;
    private final UserRepository userRepository;

//############################4
    public List getAllConsultantService(Integer uId) {
        List<Consultant_Service> services = consultant_ServiceRepository.findAll();
        User user=userRepository.findUserById(uId);
        if (services.isEmpty()) {
            throw new APIException("Consultant_Service is Empty");
        } else if (user.getSubscription().getSubscriptionType()==null) {
             List result =new ArrayList<>();
             result.add(services);
            return result;
        } else {
            return null;
        }

    }


    //    ##########3
    public void addNewConsultantService(Consultant_Service consultant_Service,Integer cId)  {
        if (consultantRepository.findConsultantById(cId)==null){
            throw new APIException("Consultant is not found");
        }else {
            Consultant consultant=consultantRepository.findConsultantById(cId);
            if (consultant==null){
                throw new APIException("can not assign");
            }else {
                if (consultant.isStatus()) {
                    consultant_Service.setConsultant(consultant);
                    consultant_ServiceRepository.save(consultant_Service);
                } else {
                    throw new APIException("You can not add");
                }
            }
        }
    }

    public void updateConsultantService(Consultant_Service consultant_Service,Integer id)  {
        if (consultant_ServiceRepository.findConsultant_ServiceById(id)==null){
            throw new APIException("not found by this id to update");
        }else {
            Consultant_Service updateConsultant_Service = consultant_ServiceRepository.findConsultant_ServiceById(id);
            updateConsultant_Service.setDescription(consultant_Service.getDescription());
            updateConsultant_Service.setPrice(consultant_Service.getPrice());
            consultant_ServiceRepository.save(updateConsultant_Service);
        }
    }

    public void deleteConsultantService(Integer id)  {
        if (consultant_ServiceRepository.findConsultant_ServiceById(id)==null){
            throw new APIException("not found by this id to delete");
        }else {
            consultant_ServiceRepository.deleteById(id);
        }
    }
//    ###################2
    public List getByPriceAndCategoryName(Integer price,String categoryName)  {
        List getAll=new ArrayList<>();
        for (int i=0; i<consultant_ServiceRepository.findAll().size();i++){
            Consultant_Service consultantService=consultant_ServiceRepository.findConsultant_ServiceById(i);
            if (consultantService.getPrice()<=price&&consultantService.getCategory().getName().equalsIgnoreCase(categoryName)){
                getAll.add(consultantService);
            }
        }
        return getAll;
    }
//    ################
    public Set getServicesByConsultantId(Integer consultantId)  {
        Consultant consultant=consultantRepository.findConsultantById(consultantId);
        if (consultant==null){
            throw new APIException("consultant is null");
        }
        return consultant.getConsultant_services();
    }
}
