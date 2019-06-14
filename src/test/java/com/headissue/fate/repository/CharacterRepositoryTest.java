package com.headissue.fate.repository;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Character;
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
public class CharacterRepositoryTest {

  @Autowired
  private CharacterRepository characterRepository;

  @Autowired
  private AspectRepository aspectRepository;

  @Test
  public void testCharacterExists() {
    Character character = characterRepository.getOne(0L);
    assertThat(character.getName(), Is.is("kratos"));
    assertThat(character.getHighConcept().getDescription(), Is.is("i am the god of war"));
    Set<Aspect> aspects = character.getAspects();
    assertThat(aspects.size(), Is.is(3));
    assertThat(aspects, IsCollectionContaining.hasItem(aspectRepository.getOne(3L)));
    assertThat(aspects, IsCollectionContaining.hasItem(aspectRepository.getOne(4L)));
    assertThat(aspects, IsCollectionContaining.hasItem(aspectRepository.getOne(5L)));
  }
}