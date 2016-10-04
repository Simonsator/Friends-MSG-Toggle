package de.simonsator.partyandfriends.friendtoggle;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.api.party.PartyManager;
import de.simonsator.partyandfriends.api.party.PlayerParty;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.friends.subcommands.Message;
import de.simonsator.partyandfriends.main.Main;
import de.simonsator.partyandfriends.party.subcommand.Chat;
import de.simonsator.partyandfriends.utilities.SubCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class ChatManager implements Listener {
	private HashMap<UUID, String> players = new HashMap<>();
	private final SubCommand chatCommand = Friends.getInstance().getSubCommand(Message.class);

	@EventHandler
	public void onWrite(ChatEvent pEvent) {
		final OnlinePAFPlayer p = PAFPlayerManager.getInstance().getPlayer((ProxiedPlayer) pEvent.getSender());
		String message = pEvent.getMessage();
		if (message.startsWith("/"))
			return;
		String playerName = players.get(p.getUniqueId());
		if (playerName == null)
			return;
		pEvent.setCancelled(true);
		chatCommand.onCommand(p, ("msg " + playerName + " " + message).split(" "));
	}

	public void setPlayer(UUID pUUID, String pName) {
		players.put(pUUID, pName);
	}

	public void remove(UUID pUUID) {
		players.remove(pUUID);
	}

	public boolean contains(UUID pUUID) {
		return players.get(pUUID) != null;
	}

}
