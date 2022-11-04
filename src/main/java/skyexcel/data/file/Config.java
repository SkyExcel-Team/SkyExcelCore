package skyexcel.data.file;

import com.google.common.io.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
public class Config implements DefaultConfig {
    public FileConfiguration config;
    public File file;
    private String name;
    private ConfigurationSection section;

    private Plugin plugin;

    public Config(String name) {
        this.name = name;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setSection(ConfigurationSection section) {
        this.section = section;
    }

    public void reloadConfig() {
        if (this.config == null)
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);

        InputStream inputStream = plugin.getResource(name + ".yml");
        if (inputStream != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
            this.config.setDefaults(config);
        }
    }


    public FileConfiguration loadDefualtConfig() {
        config = new YamlConfiguration();
        File file = new File(plugin.getDataFolder() + File.separator + name + ".yml");
        try {
            config.loadFromString(Files.toString(file, Charset.forName("UTF-8")));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }

    public boolean isFileExist() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file.exists();
    }

    public void deleteFile() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.file.delete();
    }

    public void renameFile(String name) {
        this.file = new File(plugin.getDataFolder(), this.name + ".yml");
        File file = new File(plugin.getDataFolder(), name + ".yml");

        this.file.renameTo(file);
    }

    /***
     * 파일을 다른 경로로 보내고, 원래 있는 파일은 지운다.
     * @param path 파일이 이동할 경로
     */
    public boolean moveTo(String path) {

        File newFile = new File(plugin.getDataFolder(), name + ".yml");

        try {
            System.out.println(config.getDefaults() + " 테스트");

            getConfig().save(newFile);
            saveConfig();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return config;
    }

    public boolean saveConfig() {
        if (this.config == null || this.file == null) return false;
        try {
            getConfig().save(this.file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadDefaultPluginConfig() {
        File fconfig = new File(plugin.getDataFolder(), this.name + ".yml");
        if (!fconfig.exists()) {
            plugin.saveResource(this.name + ".yml", false);
        }
    }

    public void saveDefualtConfig() {
        if (this.file == null)
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        if (!this.file.exists()) {
            file = new File(plugin.getDataFolder(), name + ".yml");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setString(String path, String msg) {
        getConfig().set(path, msg);
        saveConfig();
    }


    @Override
    public void setInteger(String path, int value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setBoolean(String path, boolean Boolean) {
        getConfig().set(path, Boolean);
        this.saveConfig();
    }


    @Override
    public void setLong(String path, long value) {
        getConfig().set(path, value);
        saveConfig();
    }

    public void setFloat(String path, float value) {

        getConfig().set(path, value);

        saveConfig();
    }

    public float getFloat(String path) {
        return (float) getConfig().get(path);
    }

    public void setDouble(String path, double value) {

        getConfig().set(path, value);
        this.saveConfig();

    }

    /**
     * 확률을 백분율 형태로 저장 할 수 있습니다.
     *
     * @param path    저장할 곳
     * @param percent 백분율 ex) 100, 50%, 23%
     */
    public void setPercent(String path, double percent) {
        setDouble(path, percent);
    }


    /***
     *  이 함수는, 퍼센트를 불러와, 해당 확률을 실행 시킨 후, True 일때 작동하는 함수 입니다.
     * @return 확률 통과 여부
     */
    public boolean getPercent(String path) {
        double percent = getDouble(path);
        double result = (double) percent / (double) 100 * 100.0;
        int rand = new Random().nextInt(100) + 1;
        return rand <= result;
    }

    public void setLocation(String path, Location value) {
        getConfig().set(path + ".world", value.getWorld().getName());
        getConfig().set(path + ".x", value.getX());
        getConfig().set(path + ".y", value.getY());
        getConfig().set(path + ".z", value.getZ());
        getConfig().set(path + ".pitch", value.getPitch());
        getConfig().set(path + ".yaw", value.getYaw());
        saveConfig();
    }


    public void setSound(String path, Sound value) {
        setString(path, value.name());
    }

    @Override
    public void setList(String path, List<Object> value) {
        config.set(path, value);
        saveConfig();
    }

    @Override
    public void setObject(String path, Object value) {

        getConfig().set(path, value);
        this.saveConfig();

    }

    @Override
    public int getInteger(String path) {
        return getConfig().getInt(path);
    }


    @Override
    public boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }


    public Sound getSound(String path) {

        Sound sound = Sound.valueOf(getString(path));
        Objects.requireNonNull(sound, "sound 변수를 찾을 수 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getSound, sound=?");

        return sound;
    }

    public Location getLocation(String path) {
        if (getConfig().get(path) != null) {
            return new Location(Bukkit.getWorld(getString(path + ".world")),
                    getDouble(path + ".x"), getDouble(path + ".y"), getDouble(path + ".z"), (float) getDouble(path + ".yaw"), (float) getDouble(path + ".pitch"));

        }
        return null;
    }

    @Override
    public String getString(String path) {
        return getConfig().getString(path);
    }


    @Override
    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    @Override
    public long getLong(String path) {
        if (config.get(path) != null) {
            Long value = config.getLong(path);
            return value != null ? value : -1;
        }
        return -1;
    }

    @Override
    public Object getObject(String path) {
        return null;
    }


    public boolean delete() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        return this.file.delete();
    }


    public void removeKey(String key) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        Set<String> toRemove = new HashSet<>(); // could also be TreeSet, or LinkedHashSet, or anything else, but of those HashSet performs the best
        toRemove.add(key);

        for (String remove : toRemove) {
            getConfig().set(remove, null);
        }
        saveConfig();
    }


    public void newArrayList(String path) {
        if (getConfig().getList(path) == null) {
            this.getConfig().set(path, new ArrayList<>());
            this.saveConfig();
        } else {
            System.out.print("해당 리스트는 이미 생성 되었습니다.");
        }
    }

    /***
     *@implSpec {@code This method Get A}
     * <pre>{@code
     *
     *   ConfigManager config;
     *   public void CreatePlayer(Player player){
     *   config = new ConfigManager(player.getUniqueId().toString());
     *   config.newArrayList("player", player);
     *   }
     *   public void addPlayer(Player player){
     *   config.addObject("player", player);
     *   }
     *
     * }</pre>
     * @return If the object is contained in List from path
     */
    public boolean AddArrayObject(String path, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);

        if (!list.contains(obj) && list != null) {
            list.add(obj);

            this.saveConfig();
            return true;
        }

        return false;
    }

    public Object getArrayObject(String path, int index) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);

        if (list != null) {
            return list.get(index);
        }
        return null;
    }

    public boolean removeArrayObject(String path, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);
        if (list.contains(obj)) {
            list.remove(obj);
            this.saveConfig();
            return true;
        }
        return false;
    }


    public boolean setArrayObject(String path, int index, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);
        if (!list.contains(obj)) {
            list.set(index, obj);
            return true;
        }

        return false;
    }

    /**
     * The name value of this class to be directory of file. so The name should be like "test/" this.
     *
     * @param results
     */
    public void setFileListTabComplete(ArrayList<String> results) {
        this.file = new File(plugin.getDataFolder(), name);
        File[] test = this.file.listFiles();
        for (File file : test) {
            if (file != null) {

                String name = file.getName();
                name = name.replaceAll(".yml", "");
                results.add(name);
            }
        }
    }

    public List<String> fileListName() {
        this.file = new File(plugin.getDataFolder(), name);
        ArrayList<String> newArray = new ArrayList<>();
        File[] test = this.file.listFiles();
        if (test != null) {
            for (File file : test) {
                if (file != null) {
                    String name = file.getName();
                    name = name.replaceAll(".yml", "");
                    newArray.add(name);
                }
            }
        }
        return newArray;
    }

    public File[] getFileList() {
        this.file = new File(plugin.getDataFolder(), name);
        return this.file.listFiles();
    }

    public ConfigurationSection newSection(String path) {
        return getConfig().createSection(path);
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
