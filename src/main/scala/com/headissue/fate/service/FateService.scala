package com.headissue.fate.service

import com.headissue.fate.controller.WorldController
import com.headissue.fate.model.World
import org.springframework.stereotype.Service

@Service
class FateService(worldController: WorldController) extends api.FateService {

  def getMessage: String = {
    s"The service says: hello"
  }

  override def enterWorld(name: String): World = worldController.getWorldByName(name)
}