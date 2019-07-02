package com.headissue.fate.controller;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.EditWorldMessage;
import com.headissue.fate.model.Player;
import com.headissue.fate.model.World;
import com.headissue.fate.repository.CampaignRepository;
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
import java.util.List;

@RestController
public class CampaignController {

  @Autowired
  private CampaignRepository campaignRepository;

  @Autowired
  private WorldRepository worldRepository;

  @GetMapping("/world/{worldId}/campaigns")
  public List<Campaign> getCampaigns (@PathVariable long worldId) {
    return campaignRepository.findByContainer(worldRepository.getOne(worldId));
  }
}