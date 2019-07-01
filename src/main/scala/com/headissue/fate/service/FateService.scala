package com.headissue.fate.service

import java.util

import com.headissue.fate.controller.{MessagesController, WorldController}
import com.headissue.fate.model.{Aspect, Campaign, Character, HasAspects, HasCharacters, IsContent, Scenario, Scene, World}
import com.headissue.fate.repository._
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

import scala.collection.mutable
import scala.jdk.CollectionConverters._

@Service
class FateService(
                   worldController: WorldController,
                   worldRepository: WorldRepository,
                   campaignRepository: CampaignRepository,
                   scenarioRepository: ScenarioRepository,
                   sceneRepository: SceneRepository,
                   characterRepository: CharacterRepository,
                   aspectRepository: AspectRepository,
                   mookRepository: MookRepository,
                   messagesController: MessagesController
) extends api.FateService {

  override def enterWorld(name: String): World = worldController.getWorldByName(name)

  override def updateWorldDescription(world: World, newDescription: String): World = {
    val storedWorld = worldRepository.getOne(world.getId)
    storedWorld.setDescription(newDescription)
    worldRepository.save(storedWorld)
  }

  def addContent(content: IsContent, repository: JpaRepository[IsContent, Long]): IsContent = {
    repository.save(content)
  }

  override def addContent(content: IsContent): IsContent = {
    content match {
      case campaign: Campaign => addContent(campaign, campaignRepository.asInstanceOf[JpaRepository[IsContent, Long]])
      case scenario: Scenario=> addContent(scenario, scenarioRepository.asInstanceOf[JpaRepository[IsContent, Long]])
      case scene: Scene=> addContent(scene, sceneRepository.asInstanceOf[JpaRepository[IsContent, Long]])
    }
  }

  override def createCharacter: Character = {
    characterRepository.save(new Character)
  }


  def addCharacterTo(hasCharactersId: Long, jpaRepository: JpaRepository[HasCharacters, Long], character: Character): Character = {
    val hasCharacters = jpaRepository.getOne(hasCharactersId)
    hasCharacters.getCharacters.add(character)
    characterRepository.save(character)
    jpaRepository.save(hasCharacters)
    character
  }

  override def addCharacterTo(hasCharacters: HasCharacters, character: Character): Character = {
    hasCharacters match {
      case world: World => addCharacterTo(world.getId, worldRepository.asInstanceOf[JpaRepository[HasCharacters, Long]], character)
      //case campaign: Campaign => addCharacterToCampaign(campaign, character)
    }
    character
  }

  def addCharacterToWorld(world: World, character: Character): World = {
    val w = worldRepository.getOne(world.getId)
    w.getCharacters.add(character)
    worldRepository.save(w)
  }
  def addCharacterToCampaign(campaign: Campaign, character: Character): Campaign = {
    val c = campaignRepository.getOne(campaign.getId)
    c.getCharacters.add(character)
    campaignRepository.save(c)
  }


  override def getCharacters(hasCharacters: HasCharacters): mutable.Buffer[Character] = {
    hasCharacters match {
      case world: World => getCharacters(world)
      case campaign: Campaign => getCharacters(campaign)
    }
  }

  def getCharacters(campaign: Campaign): mutable.Buffer[Character] = {
    new util.ArrayList[Character](campaignRepository.getOne(campaign.getId)
      .getCharacters).asScala
  }

  def getCharacters(world: World): mutable.Buffer[Character] = {
    new util.ArrayList[Character](worldRepository.getOne(world.getId)
      .getCharacters).asScala
  }

  override def addAspectTo(hasAspects: HasAspects, aspect: Aspect): Aspect = {
    hasAspects match {
      case world: World => addAspectTo(world.getId, worldRepository.asInstanceOf[JpaRepository[HasAspects, Long]], aspect)
      /*case campaign: Campaign => addAspectTo(campaignRepository, aspect)
      case scenario: Scenario => addAspectTo(sceneRepository, aspect)
      case scene: Scene => addAspect(sceneRepository, aspect)
      case character: Character => addAspectT(characterRepository, aspect)
      case mook: Mook => addAspectTo(mookRepository, aspect)*/
    }
    aspect
  }

  private def addAspectTo(hasAspectsId: Long, repository: JpaRepository[HasAspects, Long], aspect: Aspect): Aspect = {
    val hasAspects = repository.getOne(hasAspectsId)
    val aspects = hasAspects.getAspects
    aspects.add(aspect)
    repository.save(hasAspects)
    aspectRepository.save(aspect)
  }

  def getAspects(hasAspectsId: Long, jpaRepository: JpaRepository[HasAspects, Long]): mutable.Buffer[Aspect] = {
    val hasAspects = jpaRepository.getOne(hasAspectsId)
    new util.ArrayList(hasAspects.getAspects).asScala
  }

  override def getAspects(hasAspects: HasAspects): mutable.Buffer[Aspect] = {
    hasAspects match {
      case world: World => getAspects(world.getId, worldRepository.asInstanceOf[JpaRepository[HasAspects, Long]])
    }
  }

  def removeAspectFrom(hasAspectsId: Long, jpaRepository: JpaRepository[HasAspects, Long], aspect: Aspect): Aspect = {
    val hasAspects = jpaRepository.getOne(hasAspectsId)
    hasAspects.getAspects.remove(aspectRepository.getOne(aspect.getId))
    jpaRepository.save(hasAspects)
    aspect
  }

  def removeAspectFrom(hasAspects: HasAspects, aspect: Aspect): Aspect = {
    hasAspects match {
      case world: World => removeAspectFrom(world.getId, worldRepository.asInstanceOf[JpaRepository[HasAspects, Long]], aspect)
    }
    aspect
  }

  override def changeAspectToCharacter(aspect: Aspect, hasAspects: HasAspects, hasCharacters: HasCharacters): Character = {
    val character = new Character
    character.setName(aspect.getName)
    removeAspectFrom(hasAspects, aspect)
    addCharacterTo(hasCharacters, character)
  }

  override def updateCharacter(character: Character): Character = {
    val storedCharacter = characterRepository.getOne(character.getId)
    storedCharacter.update(character)
    characterRepository.save(storedCharacter)
  }

  override def updateAspect(aspect: Aspect): Aspect = {
    val storedAspect = aspectRepository.getOne(aspect.getId)
    storedAspect.update(aspect)
    aspectRepository.save(aspect)
  }

  override def createAspect: Aspect = {
    aspectRepository.save(new Aspect())
  }
}