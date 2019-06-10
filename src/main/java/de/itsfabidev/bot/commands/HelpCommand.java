package de.itsfabidev.bot.commands;

import de.itsfabidev.bot.CommandManager;
import de.itsfabidev.bot.Constants;
import de.itsfabidev.bot.objects.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        if(args.isEmpty()) {
            generateAndSendEmbed(event);
            return;
        }

        String joined = String.join("", args);

        ICommand command = manager.getCommand(joined);

        if(command == null) {
            event.getChannel().sendMessage("Der Befehl `" + joined + "`existiert nicht\n" +
                    "Benutze `" + Constants.PREFIX + getInvoke() + "` für eine Liste aller Befehle!").queue();

        }

        String message = "Command help for `" + command.getInvoke() + "`\n" + command.getHelp();

        event.getChannel().sendMessage(message).queue();

    }

    private void generateAndSendEmbed(GuildMessageReceivedEvent event) {

        EmbedBuilder builder = EmbedUtils.defaultEmbed().setTitle("Eine Liste all meiner Befehle:");

        StringBuilder descriptionBuilder = builder.getDescriptionBuilder();

        manager.getCommands().forEach(
                (command) -> descriptionBuilder.append('`').append(command.getInvoke()).append("`\n")
        );

        event.getChannel().sendMessage(builder.build()).queue();

    }



    @Override
    public String getHelp() {
        return "Hier siehst du eine Liste aller Befehle.\n" +
                "Usage:     " + Constants.PREFIX + getInvoke() + " [command]";
    }

    @Override
    public String getInvoke() {
        return "help";
    }
}
