package com.headissue.fate.repository;

import com.headissue.fate.model.Scenario;
import com.headissue.fate.model.Scene;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScenarioRepositoryTest {

  @Autowired
  private ScenarioRepository scenarioRepository;

  @Test
  public void testScenarioExists() {
    Scenario scenario = scenarioRepository.getOne(0L);
    assertThat(scenario.getName(), Is.is("an unexpected visitor"));
  }
}