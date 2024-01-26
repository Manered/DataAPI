package dev.manere.dataapi.impl;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.manere.dataapi.api.DataEditor;
import dev.manere.dataapi.api.PlayerDataResource;
import dev.manere.dataapi.util.FileResources;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerDataResourceImpl implements PlayerDataResource {
    private final String parent;
    private final String name;
    private final UUID uuid;

    public PlayerDataResourceImpl(final @NotNull String parent, final @NotNull UUID uuid) {
        this.parent = parent;
        this.name = uuid.toString();
        this.uuid = uuid;
    }

    public PlayerDataResourceImpl(final @NotNull UUID uuid) {
        this.parent = null;
        this.name = uuid.toString();
        this.uuid = uuid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull PlayerDataResource save() {
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
    public @NotNull DataEditor<PlayerDataResource> editor() {
        return new DataEditorImpl<>(this);
    }

    /**
     * {@inheritDoc}
     */
    @CanIgnoreReturnValue
    @NotNull
    @Override
    public PlayerDataResource reload() {
        CompletableFuture.runAsync(() -> YamlConfiguration.loadConfiguration(file()));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @CanIgnoreReturnValue
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

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull UUID uuid() {
        return uuid;
    }
}
