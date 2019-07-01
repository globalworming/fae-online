package com.headissue.fate.repository;

import com.headissue.fate.model.Aspect;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
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
    assertThat(aspect.getName(), is("aspect 0"));
  }

  @Test
  public void testRemoveAspect() {
    Aspect storedAspect = aspectRepository.save(new Aspect());
    assertThat(aspectRepository.findAll(), hasItem(aspectRepository.getOne(storedAspect.getId())));
    aspectRepository.delete(storedAspect);
    assertThat(aspectRepository.findAll(),
        not(hasItem(aspectRepository.getOne(storedAspect.getId()))));
  }
}