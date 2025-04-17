/*
 * Group Members:
 * Pranav Manish Reddi Madduri - G01504276
 * Lavanya Jillela - G01449670
 * Sneha Rathi - G01449688
 * Chennu Naga Venkata Sai - G01514409
 */
package com.SWE645.FEEDBACK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSurveyRepository extends JpaRepository<StudentSurvey, Long> {
}
