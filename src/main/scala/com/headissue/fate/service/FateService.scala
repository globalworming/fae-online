package com.headissue.fate.service

import com.headissue.fate.controller.{MessagesController, WorldController}
import com.headissue.fate.model.{Campaign, IsContent, Scenario, Scene, World}
import com.headissue.fate.repository.{CampaignRepository, ScenarioRepository, SceneRepository, WorldRepository}
import org.springframework.stereotype.Service

@Service
class FateService(
                   worldController: WorldController,
                   worldRepository: WorldRepository,
                   campaignRepository: CampaignRepository,
                   scenarioRepository: ScenarioRepository,
                   sceneRepository: SceneRepository,
                   messagesController: MessagesController
) extends api.FateService {

  override def enterWorld(name: String): World = worldController.getWorldByName(name)

  override def updateWorldDescription(world: World, newDescription: String): World = {
    val storedWorld = worldRepository.getOne(world.getId)
    storedWorld.setDescription(newDescription)
    worldRepository.save(storedWorld)
  }

  override def addContent(content: IsContent): IsContent = {
    content match {
      case campaign: Campaign => addCampaign(campaign)
      case scenario: Scenario => addScenario(scenario)
      case campaign: Campaign => addCampaign(campaign)
    }
  }

  def addCampaign(campaign: Campaign): IsContent = {
    campaignRepository.save(campaign)
  }

  def addScenario(scenario: Scenario): IsContent = {
    scenarioRepository.save(scenario)
  }

  def addScene(scene: Scene): IsContent = {
    sceneRepository.save(scene)
  }

}