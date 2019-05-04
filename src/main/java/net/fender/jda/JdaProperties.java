package net.fender.jda;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jda")
public class JdaProperties {

    private String game;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
