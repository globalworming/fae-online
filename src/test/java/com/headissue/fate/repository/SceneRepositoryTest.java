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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SceneRepositoryTest {

  @Autowired
  private SceneRepository sceneRepository;

  @Test
  public void testSceneExists() {
    Scene scene = sceneRepository.getOne(0L);
    assertThat(scene.getName(), Is.is("scene 0"));
  }
}