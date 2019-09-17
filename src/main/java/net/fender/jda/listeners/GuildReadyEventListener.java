package net.fender.jda.listeners;

import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.GuildReadyEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.restaction.ChannelAction;
import net.fender.jda.DiscordProperties;
import net.fender.jda.RoleDescriptor;
import net.fender.jda.TextChannelDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class GuildReadyEventListener extends BaseEventListener<GuildReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(GuildReadyEventListener.class);

    private final DiscordProperties props;

    @Inject
    public GuildReadyEventListener(DiscordProperties props) {
        super(GuildReadyEvent.class);
        this.props = props;
    }

    @Override
    protected void processEvent(GuildReadyEvent event) {
        Guild guild = event.getGuild();
        String guildName = guild.getName();
        LOG.info("Setting up guild '{}'.", guildName);

        GuildController guildController = guild.getController();
        for (RoleDescriptor roleDescriptor : props.getRoles()) {
            Role role = guildController.createRole().
                    setColor(roleDescriptor.getColor()).
                    setName(roleDescriptor.getName()).
                    setPermissions(roleDescriptor.getPermissions()).
                    setMentionable(roleDescriptor.isMentionable()).
                    setHoisted(roleDescriptor.isHoisted()).
                    complete();
            LOG.info("Created role '{}' in guild '{}'.", role.getName(), guildName);
        }

        for (String categoryName : props.getCategories()) {
            Channel channel = guildController.createCategory(categoryName).complete();
            LOG.info("Created category '{}' in guild '{}.", channel.getName(), guildName);
        }

        Map<String, Category> categoriesByName = guild.getCategories().stream().
                collect(toMap(Category::getName, identity()));

        for (TextChannelDescriptor textChannelDescriptor : props.getTextChannels()) {
            ChannelAction channelAction = guildController.createTextChannel(textChannelDescriptor.getName()).
                    setNSFW(textChannelDescriptor.isNsfw());

            String parent = textChannelDescriptor.getParent();
            if (parent != null) {
                Category category = categoriesByName.get(parent);
                if (category != null) {
                    channelAction.setParent(category);
                } else {
                    LOG.warn("Category '{}' not found in guild '{}'.", parent, guildName);
                }
            }

            if (textChannelDescriptor.getTopic() != null) {
                channelAction.setTopic(textChannelDescriptor.getTopic());
            }

            if (textChannelDescriptor.getSlowmode() != null) {
                channelAction.setSlowmode(textChannelDescriptor.getSlowmode());
            }

            Channel channel = channelAction.complete();
            LOG.info("Created channel '{}' in guild '{}'.", channel.getName(), guildName);
        }

        LOG.info("Setup for guild '{}' complete.", guildName);
    }
}
