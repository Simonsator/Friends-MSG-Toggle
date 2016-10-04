package de.simonsator.partyandfriends.friendtoggle;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class PTConfig extends ConfigurationCreator {

	protected PTConfig(File file) throws IOException {
		super(file);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Names", "toggle", "toggle-msg");
		set("Priority", 1000);
		set("Messages.Activated",
				"&7From now on all you write will be automatically written to [PLAYER], until you leave the server or you execute again /friends toggle");
		set("Messages.Disabled",
				"&7From now on you can write again normal into the chat");
		set("Messages.Help",
				"&8/&5friend list &8- &7Toggles if you msg directly to a friend");
	}

	@Override
	public void reloadConfiguration() throws IOException {
		configuration = (new PTConfig(FILE)).getCreatedConfiguration();
	}
}
