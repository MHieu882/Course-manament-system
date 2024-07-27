package Group1.Mock.scheduler;

import Group1.Mock.entity.JwtToken;
import Group1.Mock.reponsitory.JwtTokenRepository;
import Group1.Mock.service.JWTService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtScheduler {
    private static Logger logger = LoggerFactory.getLogger(JwtScheduler.class);

    @Autowired
    private JWTService service;
    // 0 0 0 * *
    @Scheduled(cron = "0 0 0 * * ?")
    public void onScheduled() {
        logger.info("Running cron job");
        deleteOldToken();
    }

    @PostConstruct
    public void onStartup() {
        deleteOldToken();
    }

    public void deleteOldToken() {
        logger.info("Purging old JWT token");
        List<JwtToken> tokens = service.getAll();
        for(JwtToken token: tokens) {
            if (token.getExpiryDate().before(new Date())) {
//                logger.info("Find a JWT that expired");
                service.deleteToken(token.getToken());
            }
        }
    }
}
