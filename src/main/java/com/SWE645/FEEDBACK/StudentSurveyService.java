/*
 * Group Members:
 * Pranav Manish Reddi Madduri - G01504276
 * Lavanya Jillela - G01449670
 * Sneha Rathi - G01449688
 * Chennu Naga Venkata Sai - G01514409
 */
// This code is part of a Spring Boot application for managing student surveys. It includes the service layer for handling survey data.
package com.SWE645.FEEDBACK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentSurveyService {

    @Autowired
    private StudentSurveyRepository repository;

    public StudentSurvey createSurvey(StudentSurvey survey) {
        return repository.save(survey);
    }

    public List<StudentSurvey> getAllSurveys() {
        return repository.findAll();
    }

    public StudentSurvey getSurveyById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public StudentSurvey updateSurvey(Long id, StudentSurvey survey) {
        if (repository.existsById(id)) {
            survey.setId(id);
            return repository.save(survey);
        }
        return null;
    }

    public boolean deleteSurvey(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
