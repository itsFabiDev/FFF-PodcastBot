package de.itsfabidev.bot.commands;

import de.itsfabidev.bot.objects.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class CatCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        WebUtils.ins.scrapeWebPage("https://api.thecatapi.com/api/images/get?format=xml&results_per_page=1").async( (document) -> {
            String url = document.getElementsByTag("url").first().html();
            MessageEmbed embed = EmbedUtils.embedImage(url).build();
            event.getChannel().sendMessage(embed).queue();

        });

    }

    @Override
    public String getHelp() {
        return "Zeigt dir eine random Katze.:)";
    }

    @Override
    public String getInvoke() {
        return "cat";
    }
}
