package com.gmail.codervortex;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public abstract class MessageGUI
{
    protected Player p;
    MessageType type;
    private CustomPlayerConnection customcon;
    protected MessageHandler handler;
    protected List<String> messages = new ArrayList<String>();

    public MessageGUI(Player p, MessageType type, MessageHandler handler)
    {
        this.p = p;
        this.type = type;
        this.handler = handler;
        this.customcon = new CustomPlayerConnection(p, ((CraftPlayer)p).getHandle().playerConnection, this);
    }

    public static MessageGUI createCommandBlockGUI(Player p, MessageHandler handler)
    {
        return createCommandBlockGUI(p, handler, "");
    }

    public static MessageGUI createCommandBlockGUI(Player p, MessageHandler handler, String def)
    {
        return new CommandBlockGUI(p, handler, def);
    }
    
    public static void createSingleCommandBlock(Player p, String msg)
    {
        new CommandBlockGUI(p, msg);
    }

    public abstract MessageGUI start(String placeholder, String message);

    public abstract MessageGUI start();

    protected abstract void callback(String resp);

    public void finish()
    {
        customcon.restore();
    }

    public class MessageCompleteEvent
    {
        private List<String> messages;

        public MessageCompleteEvent(List<String> messages)
        {
            this.messages = messages;
        }

        public List<String> getMessages()
        {
            return messages;
        }
    }

    public abstract class MessageReceivedEvent
    {
        private boolean cancel = false;
        private boolean complete = false;
        private String message;

        public MessageReceivedEvent(String message)
        {
            this.message = message;
        }

        public boolean isCancelled()
        {
            return cancel;
        }

        public void setCancelled(boolean cancel)
        {
            this.cancel = cancel;
        }

        public boolean isComplete()
        {
            return complete;
        }

        public void setComplete(boolean complete)
        {
            this.complete = complete;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }
    }

    public interface MessageHandler
    {
        public void onMessageReceived(MessageReceivedEvent e);

        public void onComplete(MessageCompleteEvent e);
    }

    public enum MessageType
    {
        COMMAND_BLOCK
    }
}
