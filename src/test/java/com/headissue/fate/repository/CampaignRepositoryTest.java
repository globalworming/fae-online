package com.headissue.fate.repository;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Player;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CampaignRepositoryTest {

  @Autowired
  private CampaignRepository campaignRepository;

  @Test
  public void testCampaignExists() {
    Campaign campaign = campaignRepository.getOne(0L);
    assertThat(campaign.getName(), Is.is("spread the ashes"));
  }
}