import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import org.jsoup.Jsoup

class DiscordListener(val bot: Bot) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        println("${event.author.name}: ${event.message.contentRaw}")



        if(event.author.isBot())
            return
        if(event.message.contentRaw == "!")
            event.channel.sendMessage(
                    "List of Commands: \n\n!duckduckgo = d (Query)\n!youtube =y (Query)\n!google = g (Query)\n!bing = b (Query)").queue()


        if(event.author.isBot())
            return
        if(event.message.contentRaw == "Hi" || event.message.contentRaw == "hi" || event.message.contentRaw == "hello" || event.message.contentRaw == "Hello")
        { event.channel.sendMessage(
                    "Hi. My name is HackBot \nI'm here to assist you in your exotic adventure to find the perfect tutorial for your Hackaton app").queue()}

        if(event.author.isBot())
            return

        if(event.message.contentRaw.take(2) == "!d")
        {
            try {
                val query = event.message.contentRaw.takeLast(event.message.contentRaw.length - 3)
                val spacesRemoved = query.replace(" ", "+")
                event.channel.sendMessage("Your search result: " + event.message.contentRaw.takeLast(event.message.contentRaw.length - 2)).queue()
                event.channel.sendMessage("https://duckduckgo.com/?q=" + spacesRemoved).queue()
            } catch(e: IllegalArgumentException) {
                event.channel.sendMessage("Please Type a Query").queue()
            }
        }


        if(event.author.isBot())
            return
        if (event.message.contentRaw.take(2) == "!g") {

            try {
                val query = event.message.contentRaw.takeLast(event.message.contentRaw.length - 3)
                val spacesRemoved = query.replace(" ", "+")
                event.channel.sendMessage("You search result: " + event.message.contentRaw.takeLast(event.message.contentRaw.length - 2)).queue()

                Jsoup.connect("https://www.google.com/search?q=" + event.message.contentRaw.takeLast(event.message.contentRaw.length - 2)).get().run {
                    {
                        select("div.rc").forEachIndexed { index, element ->
                            val titleAnchor = element.select("h3 a")
                            val title = titleAnchor.text()
                            val url = titleAnchor.attr("href")
                            // Dumping Search Index, Title and URL on the stdout.
                            event.channel.sendMessage("inside").queue()
                            event.channel.sendMessage("$index. $title ($url)").queue()

                        }
                    }
                }

                event.channel.sendMessage("https://www.google.com/search?q=" + spacesRemoved).queue()
            } catch(e: IllegalArgumentException){
                event.channel.sendMessage("Please Type a Query").queue()
            }
        }


        if(event.author.isBot())
                return
        if (event.message.contentRaw.take(2) == "!b") {

            try {
                val query = event.message.contentRaw.takeLast(event.message.contentRaw.length - 3)
                val spacesRemoved = query.replace(" ", "+")
                event.channel.sendMessage("You search result: " + event.message.contentRaw.takeLast(event.message.contentRaw.length - 2)).queue()
                event.channel.sendMessage("https://www.bing.com/search?q=" + spacesRemoved).queue()
            } catch (e: IllegalArgumentException) {
                event.channel.sendMessage("Please Type a Query").queue()
            }
        }

        if(event.author.isBot())
            return
        if (event.message.contentRaw.take(2) == "!y") try {
            val query = event.message.contentRaw.takeLast(event.message.contentRaw.length - 3)
            val spacesRemoved = query.replace(" ", "+")
            event.channel.sendMessage("You search result: " + event.message.contentRaw.takeLast(event.message.contentRaw.length - 2)).queue()
            event.channel.sendMessage("https://www.youtube.com/results?search_query=" + spacesRemoved).queue()
        } catch(e: IllegalArgumentException){
            event.channel.sendMessage("Please Type a Query").queue()
        }
    }
}
