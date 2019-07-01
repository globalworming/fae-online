package com.headissue.fate.controller

import com.headissue.fate.model.World
import com.headissue.fate.service.FateService
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

@RestController
class FateServiceController(fateService: FateService) {


  @GetMapping(path = Array("/service/enterWorld/{name}"))
  def handleRequest(@PathVariable name: String): World = {
    fateService.enterWorld(name)
  }


}
