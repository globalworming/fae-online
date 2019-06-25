package com.headissue.fate.repository;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {


  List<Scenario> findByCampaignId(long campaignId);
}
