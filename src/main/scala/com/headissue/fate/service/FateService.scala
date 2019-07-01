package com.headissue.fate.service

import java.util

import com.headissue.fate.controller.{MessagesController, WorldController}
import com.headissue.fate.model.{Campaign, Character, HasCharacters, IsContent, Scenario, Scene, World}
import com.headissue.fate.repository.{CampaignRepository, CharacterRepository, ScenarioRepository, SceneRepository, WorldRepository}
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

import scala.collection.mutable._
import scala.jdk.CollectionConverters._

@Service
class FateService(
                   worldController: WorldController,
                   worldRepository: WorldRepository,
                   campaignRepository: CampaignRepository,
                   scenarioRepository: ScenarioRepository,
                   sceneRepository: SceneRepository,
                   characterRepository: CharacterRepository,
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


  override def addCharacterTo(hasCharacters: HasCharacters, character: Character) = {
    hasCharacters match {
      case world: World => addCharacterToWorld(world, character)
      case campaign: Campaign => addCharacterToCampaign(campaign, character)
    }
  }

  def addCharacterToWorld(world: World, character: Character) = {
    val w = worldRepository.getOne(world.getId)
    w.getCharacters.add(character)
    worldRepository.save(w)
  }
  def addCharacterToCampaign(campaign: Campaign, character: Character) = {
    val c = campaignRepository.getOne(campaign.getId)
    c.getCharacters.add(character)
    campaignRepository.save(c)
  }


  override def getCharacters(hasCharacters: HasCharacters): Buffer[Character] = {
    hasCharacters match {
      case world: World => getCharacters(world)
      case campaign: Campaign => getCharacters(campaign)
    }
  }

  def getCharacters(campaign: Campaign): Buffer[Character] = {
    new util.ArrayList[Character](campaignRepository.getOne(campaign.getId)
      .getCharacters).asScala
  }

  def getCharacters(world: World): Buffer[Character] = {
    new util.ArrayList[Character](worldRepository.getOne(world.getId)
      .getCharacters).asScala
  }
}