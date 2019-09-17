package net.fender.jda;

import net.dv8tion.jda.core.Permission;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDescriptor {

    private String name;
    private Color color;
    private List<Permission> permissions = new ArrayList<>();
    private boolean mentionable = false;
    private boolean hoisted = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean isMentionable() {
        return mentionable;
    }

    public void setMentionable(boolean mentionable) {
        this.mentionable = mentionable;
    }

    public boolean isHoisted() {
        return hoisted;
    }

    public void setHoisted(boolean hoisted) {
        this.hoisted = hoisted;
    }
}
