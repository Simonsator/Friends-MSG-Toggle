package de.simonsator.partyandfriends.friendtoggle;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.api.party.abstractcommands.PartySubCommand;
import de.simonsator.partyandfriends.utilities.PatterCollection;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

import java.util.regex.Matcher;

import static de.simonsator.partyandfriends.main.Main.getInstance;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class FriendToggle extends FriendSubCommand {
	private final ChatManager CHAT_MANAGER;
	private final Configuration CONFIG;

	protected FriendToggle(String[] pCommands, int pPriority, String pHelp, ChatManager pChatManager, Configuration pConfig) {
		super(pCommands, pPriority, pHelp);
		CHAT_MANAGER = pChatManager;
		CONFIG = pConfig;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (CHAT_MANAGER.contains(pPlayer.getUniqueId())) {
			if (args.length == 1) {
				pPlayer.sendMessage(PREFIX + CONFIG.getString("Messages.Disabled"));
				CHAT_MANAGER.remove(pPlayer.getUniqueId());
				return;
			}
		}
		if (!isPlayerGiven(pPlayer, args))
			return;
		if (!isAFriendOf(pPlayer, PAFPlayerManager.getInstance().getPlayer(args[1])))
			return;
		pPlayer.sendMessage(PREFIX + CONFIG.getString("Messages.Activated").replace("[PLAYER]", args[1]));
		CHAT_MANAGER.setPlayer(pPlayer.getUniqueId(), args[1]);
	}

}
