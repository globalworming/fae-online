package com.headissue.fate.service.api

import com.headissue.fate.model.World

trait FateService {

  def enterWorld(name: String): World

}
