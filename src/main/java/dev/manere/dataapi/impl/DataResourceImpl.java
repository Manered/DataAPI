package dev.manere.dataapi.impl;

import dev.manere.dataapi.api.DataEditor;
import dev.manere.dataapi.api.DataResource;
import dev.manere.dataapi.api.PlayerDataResource;
import dev.manere.dataapi.util.FileResources;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class DataResourceImpl implements DataResource {
    private final String parent;
    private final String name;

    public DataResourceImpl(final @NotNull String parent, final @NotNull String name) {
        this.parent = parent;
        this.name = name;
    }

    public DataResourceImpl(final @NotNull String name) {
        this.parent = null;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataResource save() {
        final File file = file();

        final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataEditor<DataResource> editor() {
        return new DataEditorImpl<>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        YamlConfiguration.loadConfiguration(file());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public @NotNull File file() {
        final File root = DataAPIImpl.apiSource().getServer().getPluginsFolder();
        final File dataFolder = FileResources.file(root, "/data/");
        final File pluginFolder = FileResources.file(dataFolder, "/" + DataAPIImpl.apiFolder() + "/");

        if (!pluginFolder.exists()) pluginFolder.mkdirs();

        File file;

        if (parent == null) file = FileResources.file(pluginFolder, "/" + name + ".yml");
        else file = FileResources.file(FileResources.file(pluginFolder, "/" + parent + "/"), "/" + name + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file;
    }
}
