package de.itsfabidev.bot.commands;

import de.itsfabidev.bot.Constants;
import de.itsfabidev.bot.objects.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class PingCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong!").queue((message) ->
                message.editMessageFormat("Ping The PONG! Dein Ping ist %sms", event.getJDA().getPing()).queue()
        );
    }

    @Override
    public String getHelp() {
        return "Pong!\n" +
                "Usage: `" + Constants.PREFIX + getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "ping";
    }
}

