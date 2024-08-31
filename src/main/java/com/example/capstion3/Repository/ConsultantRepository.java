package com.example.capstion3.Repository;

import com.example.capstion3.Model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

    Consultant findConsultantById(Integer id);

    @Query("SELECT c FROM Consultant c JOIN c.ratings r GROUP BY c ORDER BY AVG(r.rating_score) DESC")
    List<Consultant> findTop4ByOrderByAvgRatingDesc();

    @Query("SELECT COUNT(c) FROM Consultant c WHERE c.status = true")
    int countApprovedConsultants();


}
