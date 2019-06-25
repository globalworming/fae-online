package com.headissue.fate.controller;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Scenario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScenarioControllerTest {

  @Autowired
  private ScenarioController scenarioController;

  @Test
  public void getCampaign0Scenarios() {
    List<Scenario> scenarios = scenarioController.getScenarios(0L);
    assertThat(scenarios, not(nullValue()));
    assertThat(scenarios.size(), is(2));
  }
}