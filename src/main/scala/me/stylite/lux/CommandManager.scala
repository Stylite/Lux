package me.stylite.lux

import me.stylite.lux.command.commands.misc.PingCommand
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

object CommandManager extends ListenerAdapter {
  val PREFIX = "!>"

  val commands: Set[BotCommand] = Set(
    PingCommand
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
