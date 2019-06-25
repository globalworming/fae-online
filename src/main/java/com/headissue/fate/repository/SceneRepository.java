package com.headissue.fate.repository;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Scene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneRepository extends JpaRepository<Scene, Long> {


  List<Scene> findByScenarioId(long scenarioId);
}
