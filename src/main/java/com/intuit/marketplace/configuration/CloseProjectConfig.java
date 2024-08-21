package com.intuit.marketplace.configuration;

import com.intuit.marketplace.service.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CloseProjectConfig {
    private static final int INTERVAL = 1000*10;

    @Autowired
    MarketplaceService marketplaceService;

    @Scheduled(fixedDelay = INTERVAL)
    public void scheduleProjectClose() {
        marketplaceService.closeAllExpiredProjects();
    }
}
