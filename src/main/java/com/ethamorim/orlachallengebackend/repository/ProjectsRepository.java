package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Set;

public interface ProjectsRepository extends JpaRepository<ProjectModel, Integer> {
    @Query(
            "SELECT e " +
            "FROM ProjectModel p " +
            "JOIN EmployeesProjectsModel ep ON p.id = ep.projects.id " +
            "JOIN EmployeeModel e ON ep.employees.id = e.id " +
            "WHERE p.id = ?1")
    Set<EmployeeModel> findMembersByProjectId(int id);
}
