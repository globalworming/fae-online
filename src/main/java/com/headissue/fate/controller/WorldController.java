package com.headissue.fate.controller;

import com.headissue.fate.model.EditWorldMessage;
import com.headissue.fate.model.Player;
import com.headissue.fate.model.World;
import com.headissue.fate.repository.PlayerRepository;
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
  private PlayerRepository playerRepository;

  @Autowired
  private WebSocketController webSocketController;

  @GetMapping("/world/byName/{worldName}")
  public World getWorldByName(@PathVariable String worldName) {
    World world = worldRepository.findByName(worldName);
    /*if (world == null) {
      World newWorld = new World();
      newWorld.setName(worldName);
      Player gameMaster = new Player();
      gameMaster.setName("game master");
      playerRepository.save(gameMaster);
      newWorld.setGameMaster(gameMaster);
      world = worldRepository.save(newWorld);
    }*/
    return world;
  }

  @GetMapping("/world/{worldId}")
  public World getWorld(@PathVariable long worldId) {
    return worldRepository.findById(worldId).orElse(null);
  }

  @PutMapping("/world/{worldId}/description")
  public void putWorldDescription(@PathVariable long worldId, @Valid @RequestBody String description) {
    World world = worldRepository.findById(worldId).orElseGet(null);
    world.setDescription(description);
    worldRepository.save(world);

    EditWorldMessage editWorldMessage = new EditWorldMessage();
    editWorldMessage.setMessageType(EditWorldMessage.MessageType.UPDATE_WORLD);
    editWorldMessage.setContent(description);
    webSocketController.sendMessage(worldId, editWorldMessage);
  }

  @DeleteMapping("/world/{worldId}/description")
  public void deleteWorldDescription(@PathVariable long worldId) {
    World world = worldRepository.findById(worldId).orElseGet(null);
    world.setDescription("");
    worldRepository.save(world);

    EditWorldMessage editWorldMessage = new EditWorldMessage();
    editWorldMessage.setMessageType(EditWorldMessage.MessageType.UPDATE_WORLD);
    editWorldMessage.setContent("");
    webSocketController.sendMessage(worldId, editWorldMessage);
  }
}