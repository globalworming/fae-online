package com.headissue.fate.controller;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Scenario;
import com.headissue.fate.repository.CampaignRepository;
import com.headissue.fate.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScenarioController {

  @Autowired
  private ScenarioRepository scenarioRepository;

  @GetMapping("/campaigns/{campaignId}/scenarios")
  public List<Scenario> getScenarios(@PathVariable long campaignId) {
    return scenarioRepository.findByCampaignId(campaignId);
  }
}