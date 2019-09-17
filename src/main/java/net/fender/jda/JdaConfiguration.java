package net.fender.jda;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@EnableConfigurationProperties(DiscordProperties.class)
public class JdaConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(JdaConfiguration.class);

    @Bean
    public JDA jda(DiscordProperties props,
                   List<EventListener> eventListeners)
            throws LoginException, InterruptedException {
        LOG.info("Starting JDA.");
        JDA jda = new JDABuilder(AccountType.BOT).
                setGame(Game.playing(props.getGame())).
                setToken(props.getToken()).
                addEventListener(eventListeners.toArray()).
                build().
                awaitReady();
        LOG.info("JDA status: {}.", jda.getStatus());
        return jda;
    }
}
