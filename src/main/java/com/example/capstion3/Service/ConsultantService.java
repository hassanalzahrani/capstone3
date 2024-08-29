package com.example.capstion3.Service;


import com.example.capstion3.API.APIException;
import com.example.capstion3.DTO.ConsultantDTO;
import com.example.capstion3.DTO.SessionDTO;
import com.example.capstion3.Model.Category;
import com.example.capstion3.Model.Consultant;
import com.example.capstion3.Model.Session;
import com.example.capstion3.Repository.CategoryRepository;
import com.example.capstion3.Repository.ConsultantRepository;
import com.example.capstion3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConsultantService {

    private final ConsultantRepository consultantRepository;
    private final UserRepository UserRepository;
    private final CategoryRepository categoryRepository;


    public List<Consultant> getAllConsultant() {
        return consultantRepository.findAll();

    }

    public void addConsultant(ConsultantDTO consultantDTO) {
        Category category = categoryRepository.findCategoryById(consultantDTO.getId());
        if (category == null) {
            throw new APIException("Category not found");
        } else {
            Consultant consultant = new Consultant(null,consultantDTO.getName(), consultantDTO.getAcademic_specialization(), consultantDTO.getProfessional_certifications(), consultantDTO.getProfile_summary(), consultantDTO.getExperience_years(), consultantDTO.isStatus(), category, null, null, null);
            consultantRepository.save(consultant);
        }
    }

    public void updateConsultant(ConsultantDTO consultantDTO) {
        Consultant consultant1 = consultantRepository.findConsultantById(consultantDTO.getCategory_id());
        if (consultant1 == null) {
            throw new APIException("Consultant not found");
        }
        consultant1.setExperience_years(consultantDTO.getExperience_years());
        consultant1.setAcademic_specialization(consultantDTO.getAcademic_specialization());
        consultant1.setProfessional_certifications(consultantDTO.getProfessional_certifications());
        consultant1.setProfile_summary(consultantDTO.getProfile_summary());
        consultant1.setStatus(consultantDTO.isStatus());
        consultantRepository.save(consultant1);

    }

    public void deleteConsultant(Integer id) {
        Consultant consultant = consultantRepository.findConsultantById(id);
        if (consultant == null) {
            throw new APIException("Consultant not found");
        }
        consultantRepository.deleteById(id);

    }

    //    #####################5
    public List getConsultantByCategory(Integer catId) {
        List<Consultant> getByCategory = new ArrayList<>();
        for (int i = 0; i < consultantRepository.findAll().size(); i++) {
            if (consultantRepository.findConsultantById(i).getCategory().getId() == catId) {
                if (consultantRepository.findConsultantById(i).isStatus()) {
                    getByCategory.add(consultantRepository.findConsultantById(i));
                }
            }
        }
        return getByCategory;
    }

    //###########6
    public Consultant getConsultINFOByID(Integer id) {
        Consultant consultant = consultantRepository.findConsultantById(id);
        if (consultant == null) {
            throw new APIException("Consultant not found");
        } else {
            if (consultant.isStatus()) {
                return consultant;
            }else {
                throw new APIException("Consultant status is not true to display the consultant");
            }
        }
    }
//############### i change here *****************************88
    public List<Consultant> getConsultantByExperience_years(Integer experience_years) {
        List<Consultant> getByExperience_years = new ArrayList<>();
        for (int i = 0; i < consultantRepository.findAll().size(); i++) {
            if (consultantRepository.findConsultantById(i).getExperience_years() >= experience_years) {
                getByExperience_years.add(consultantRepository.findConsultantById(i));
            }
        }
        return getByExperience_years;
    }

//######################
    public List<Consultant> getConsultantsBySpecialization(String specialization) {
        List<Consultant> allConsultants = consultantRepository.findAll();
        List<Consultant> filteredConsultants = new ArrayList<>();

        for (Consultant consultant : allConsultants) {
            if (consultant.getAcademic_specialization().equalsIgnoreCase(specialization)) {
                filteredConsultants.add(consultant);
            }
        }
        return filteredConsultants;
    }
//###########################
    public List<Consultant> getTop4ConsultantsByRating() {
        List<Consultant> topConsultants = consultantRepository.findTop4ByOrderByAvgRatingDesc();
        if (topConsultants.isEmpty()) {
            throw new APIException("not found to display Consultants");
        }
        return topConsultants;
    }
//############################
    public Consultant getConsultantById(Integer cId) {
        Consultant consultant = consultantRepository.findConsultantById(cId);
        if (consultant == null) {
            throw new APIException("Consultant not found");
        }
        return consultant;
    }
//##############################
    public Set getConsultantService(Integer cId){
        Consultant consultant = consultantRepository.findConsultantById(cId);
        if (consultant == null) {
            throw new APIException("Consultant not found");
        }
        return consultant.getConsultant_services();
    }
}

