package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<ProjectModel, Integer> {}
