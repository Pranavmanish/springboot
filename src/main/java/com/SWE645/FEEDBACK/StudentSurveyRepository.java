package com.SWE645.FEEDBACK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSurveyRepository extends JpaRepository<StudentSurvey, Long> {
}
