package me.stylite.lux

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import me.stylite.lux.PrefixManager

object CommandManager extends ListenerAdapter {
  val PREFIX = PrefixManager.PREFIX
  val commands: Set[BotCommand] = Set(
  )

  override def onMessageReceived(event: MessageReceivedEvent): Unit = {

    if (event.getMessage.getContentRaw.startsWith(PREFIX)){
      handleCommand(new CommandContext(event))
    }
  }

  private def handleCommand(context: CommandContext): Unit = {
    commands.find(_.doesMatch(context.name)) match {
      case Some(c) => c.execute(context)
      case None => return
    }
  }

}
