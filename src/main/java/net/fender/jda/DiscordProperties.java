package net.fender.jda;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("discord")
public class DiscordProperties {

    private String game;
    private String token;
    private List<RoleDescriptor> roles = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private List<TextChannelDescriptor> textChannels = new ArrayList<>();

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<RoleDescriptor> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDescriptor> roles) {
        this.roles = roles;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<TextChannelDescriptor> getTextChannels() {
        return textChannels;
    }

    public void setTextChannels(List<TextChannelDescriptor> textChannels) {
        this.textChannels = textChannels;
    }

}
