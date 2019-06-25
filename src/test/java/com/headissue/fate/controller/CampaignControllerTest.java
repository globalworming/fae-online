package com.headissue.fate.controller;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CampaignControllerTest {

  @Autowired
  private CampaignController campaignController;

  @Test
  public void getWorld0Campaigns() {
    List<Campaign> campaigns = this.campaignController.getCampaigns(0L);
    assertThat(campaigns, not(nullValue()));
    assertThat(campaigns.size(), is(2));
  }
}