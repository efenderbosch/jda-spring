package net.fender.jda.listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.fender.jda.filters.ChannelTypeFilter.PRIVATE_CHANNEL_FILTER;
import static net.fender.jda.filters.ChannelTypeFilter.TEXT_CHANNEL_FILTER;

public class CommandParser implements Function<MessageReceivedEvent, List<String>> {

    private final Pattern pattern;

    public CommandParser(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public List<String> apply(MessageReceivedEvent messageReceivedEvent) {
        if (!TEXT_CHANNEL_FILTER.test(messageReceivedEvent) &&
                !PRIVATE_CHANNEL_FILTER.test(messageReceivedEvent)) {
            return Collections.emptyList();
        }

        String content = messageReceivedEvent.getMessage().getContentRaw();
        Matcher matcher = pattern.matcher(content);
        if (!matcher.matches()) return Collections.emptyList();
        // group 0 is the entire match
        int count = matcher.groupCount() + 1;
        List<String> parts = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            parts.add(matcher.group(i));
        }
        return parts;
    }
}
