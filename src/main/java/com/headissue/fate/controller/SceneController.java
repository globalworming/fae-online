package com.headissue.fate.controller;

import com.headissue.fate.model.Scenario;
import com.headissue.fate.model.Scene;
import com.headissue.fate.repository.ScenarioRepository;
import com.headissue.fate.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SceneController {

  @Autowired
  private SceneRepository sceneRepository;

  @GetMapping("/scenario/{scenarioId}/scenes")
  public List<Scene> getScenes(@PathVariable long scenarioId) {
    return sceneRepository.findByScenarioId(scenarioId);
  }
}