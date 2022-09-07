package skyexcel.data.file;

import org.bukkit.configuration.ConfigurationSection;

public class Section {
    private ConfigurationSection section;

    private Config config;

    public Section(Config config) {
        this.config = config;
    }

    public ConfigurationSection addArray(String path, String newpath) {

        this.section = config.getConfig().getConfigurationSection(path + "." + config.getConfig().getConfigurationSection(path).getKeys(false).size());

        if (config.getConfig().getConfigurationSection(path) != null) {
            if (section != null) {
                return section;
            }
        } else {
            return config.getConfig().createSection(section.getCurrentPath() + "." + newpath);
        }
        return null;
    }
}
