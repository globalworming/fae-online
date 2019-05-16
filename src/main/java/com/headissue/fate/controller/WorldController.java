package com.headissue.fate.controller;

import com.headissue.fate.model.ChatMessage;
import com.headissue.fate.model.EditWorldMessage;
import com.headissue.fate.model.World;
import com.headissue.fate.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WorldController {

  @Autowired
  private WorldRepository worldRepository;

  @Autowired
  private WebSocketController webSocketController;

  @GetMapping("/{worldName}/id")
  public long getWorldId (@PathVariable String worldName) {
    World world = worldRepository.findByName(worldName);
    if (world == null) {
      World newWorld = new World();
      newWorld.setName(worldName);
      world = worldRepository.save(newWorld);
    }
    return world.getId();
  }

  @GetMapping("/world/{worldId}")
  public World getWorld (@PathVariable long worldId) {
    return worldRepository.findById(worldId).orElse(null);
  }

  @PutMapping("/world/{worldId}/description")
  public void putWorldDescription(@PathVariable long worldId, @Valid @RequestBody String description) {
    World world = worldRepository.findById(worldId).orElseGet(null);
    world.setDescription(description);
    worldRepository.save(world);

    EditWorldMessage editWorldMessage = new EditWorldMessage();
    editWorldMessage.setType(EditWorldMessage.MessageType.UPDATE_DESCRIPTION);
    editWorldMessage.setContent(description);
    webSocketController.sendMessage(worldId, editWorldMessage);
  }

  @DeleteMapping("/world/{worldId}/description")
  public void deleteWorldDescription(@PathVariable long worldId) {
    World world = worldRepository.findById(worldId).orElseGet(null);
    world.setDescription("");
    worldRepository.save(world);

    EditWorldMessage editWorldMessage = new EditWorldMessage();
    editWorldMessage.setType(EditWorldMessage.MessageType.UPDATE_DESCRIPTION);
    editWorldMessage.setContent("");
    webSocketController.sendMessage(worldId, editWorldMessage);
  }
}