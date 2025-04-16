package com.SWE645.FEEDBACK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "*")

public class StudentSurveyController {

    @Autowired
    private StudentSurveyService service;

    @PostMapping
    public ResponseEntity<StudentSurvey> createSurvey(@RequestBody StudentSurvey survey) {
        return new ResponseEntity<>(service.createSurvey(survey), HttpStatus.CREATED);
    }

    @GetMapping
    public List<StudentSurvey> getAllSurveys() {
        return service.getAllSurveys();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentSurvey> getSurveyById(@PathVariable Long id) {
        StudentSurvey survey = service.getSurveyById(id);
        return survey != null ? new ResponseEntity<>(survey, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentSurvey> updateSurvey(@PathVariable Long id, @RequestBody StudentSurvey survey) {
        StudentSurvey updatedSurvey = service.updateSurvey(id, survey);
        return updatedSurvey != null ? new ResponseEntity<>(updatedSurvey, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        return service.deleteSurvey(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
