package com.headissue.fate.controller;

import com.headissue.fate.model.Message;
import com.headissue.fate.repository.MessagesRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MessagesController {

  @Autowired
  private MessagesRepository messagesRepository;

  @GetMapping("/message")
  public Page<Message> getMessages(Pageable pageable) {
    return messagesRepository.findAll(pageable);
  }

  @PostMapping("/message")
  public Message createQuestion(@Valid @RequestBody Message message) {
    return messagesRepository.save(message);
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