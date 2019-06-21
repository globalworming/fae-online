package com.headissue.fate.repository;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Mook;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MookRepositoryTest {

  @Autowired
  private MookRepository mookRepository;

  @Autowired
  private AspectRepository aspectRepository;

  @Test
  public void testMookExists() {
    Mook mook = mookRepository.getOne(0L);
    assertThat(mook.getName(), Is.is("Boy"));
    assertThat(mook.getGoodAt(), Is.is("shooting with the bow"));
    Set<Aspect> aspects = mook.getAspects();
    assertThat(aspects.size(), Is.is(1));
    assertThat(aspects, IsCollectionContaining.hasItem(aspectRepository.getOne(6L)));
  }
}