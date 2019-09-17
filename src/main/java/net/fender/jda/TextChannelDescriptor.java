package net.fender.jda;

public class TextChannelDescriptor {

    private String name;
    private String topic;
    private boolean nsfw = false;
    private String parent;
    private Integer slowmode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getSlowmode() {
        return slowmode;
    }

    public void setSlowmode(Integer slowmode) {
        this.slowmode = slowmode;
    }
}
