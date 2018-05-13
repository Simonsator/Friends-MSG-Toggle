package de.simonsator.partyandfriends.friendtoggle;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.friends.commands.Friends;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class FTMain extends PAFExtension {

	@Override
	public void onEnable() {
		Configuration config = null;
		try {
			config = (new PTConfig(new File(getDataFolder(), "config.yml"), this)).getCreatedConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChatManager chatManager = new ChatManager();
		ProxyServer.getInstance().getPluginManager().registerListener(this, chatManager);
		ProxyServer.getInstance().getPluginManager().registerListener(this, new RemoveManager(chatManager));
		Friends.getInstance().addCommand(
				new FriendToggle(config.getStringList("Names").toArray(new String[0]),
						config.getInt("Priority"), config.getString("Messages.Help"), chatManager, config));
	}

}
