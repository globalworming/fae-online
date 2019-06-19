package com.headissue.fate.repository;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Scenario;
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
public class AspectRepositoryTest {

  @Autowired
  private AspectRepository aspectRepository;

  @Test
  public void testAspectExists() {
    Aspect aspect = aspectRepository.getOne(0L);
    assertThat(aspect.getDescription(), Is.is("aspect 0"));
  }
}