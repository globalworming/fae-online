package com.headissue.fate.controller;

import com.headissue.fate.model.Message;
import com.headissue.fate.model.World;
import com.headissue.fate.repository.MessagesRepository;
import com.headissue.fate.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessagesController {

  @Autowired
  private MessagesRepository messagesRepository;

  @Autowired
  private WorldRepository worldRepository;

  @GetMapping("/message/{worldName}")
  public List<Message> getMessages(@PathVariable String worldName) {
    World world = worldRepository.findByName(worldName);
    List<Message> byWorldIdRef = messagesRepository.findByWorld(world.getId());
    return byWorldIdRef;
  }

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

  /*
  @PutMapping("/questions/{questionId}")
  public Question updateQuestion(@PathVariable Long questionId,
                                 @Valid @RequestBody Question questionRequest) {
    return messagesRepository.findById(questionId)
        .map(question -> {
          question.setTitle(questionRequest.getTitle());
          question.setDescription(questionRequest.getDescription());
          return messagesRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
  }


  @DeleteMapping("/questions/{questionId}")
  public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
    return messagesRepository.findById(questionId)
        .map(question -> {
          messagesRepository.delete(question);
          return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
  }

  */
}