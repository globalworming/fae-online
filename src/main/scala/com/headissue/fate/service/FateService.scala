package com.headissue.fate.service

import com.headissue.fate.controller.{MessagesController, WorldController}
import com.headissue.fate.model.World
import com.headissue.fate.repository.WorldRepository
import org.springframework.stereotype.Service

@Service
class FateService(
                   worldController: WorldController,
                   worldRepository: WorldRepository,
                   messagesController: MessagesController
) extends api.FateService {

  def getMessage: String = {
    s"The service says: hello"
  }

  override def enterWorld(name: String): World = worldController.getWorldByName(name)

  override def updateWorldDescription(world: World, newDescription: String): World = {
    val storedWorld = worldRepository.getOne(world.getId)
    storedWorld.setDescription(newDescription);
    worldRepository.save(storedWorld)
  }
}