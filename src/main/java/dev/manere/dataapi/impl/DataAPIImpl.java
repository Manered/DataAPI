package dev.manere.dataapi.impl;

import dev.manere.dataapi.api.DataAPI;
import dev.manere.dataapi.api.DataResource;
import dev.manere.dataapi.api.PlayerDataResource;
import dev.manere.dataapi.util.FileResources;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DataAPIImpl implements DataAPI {
    private static JavaPlugin source;
    private static String folderName;
    private static String root;

    /**
     * {@inheritDoc}
     */
    @Override
    public void source(final @NotNull JavaPlugin source) {
        DataAPIImpl.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void folder(final @NotNull String folderName) {
        DataAPIImpl.folderName = folderName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rootChar(final @NotNull String root) {
        DataAPIImpl.root = root;
    }

    /**
     * {@inheritDoc}
     */
    public void rootChar(final char root) {
        DataAPIImpl.root = String.valueOf(root);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataResource data(final @NotNull String parent, @NotNull String name) {
        return new DataResourceImpl(parent, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataResource data(final @NotNull String name) {
        return new DataResourceImpl(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull PlayerDataResource player(final @NotNull String parent, @NotNull UUID uuid) {
        return new PlayerDataResourceImpl(parent, uuid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull PlayerDataResource player(final @NotNull UUID uuid) {
        return new PlayerDataResourceImpl(uuid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull JavaPlugin source() {
        return source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull String folder() {
        return folderName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull String rootChar() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void register() {
        if (source == null) throw new NullPointerException("src cannot be null");
        if (folderName == null) folder(source.getName());
        if (root == null) rootChar('~');

        final File pluginsFolder = source.getServer().getPluginsFolder();
        final File dataFolder = FileResources.file(pluginsFolder, "/data/");
        final File registeredFolder = FileResources.file(dataFolder, "/" + folderName + "/");

        if (!dataFolder.exists()) dataFolder.mkdirs();
        if (!registeredFolder.exists()) registeredFolder.mkdirs();

        final File txtFile = FileResources.file(dataFolder, "/read_me_if_you_want_to.yml");
        if (txtFile.exists()) return;
        try {
            txtFile.createNewFile();
        } catch (IOException ignored) {}

        final FileConfiguration configuration = YamlConfiguration.loadConfiguration(txtFile);
        configuration.options().setHeader(List.of(
                "This file is automatically created by an API - DataAPI.",
                "==============================",
                "If you're not into making plugins or are new to them,",
                "here's what an API is and what APIs are used for.",
                "==============================",
                "Suppose you’re building a calculator app,",
                "but instead of actually writing the code to do the calculations,",
                "you’ll use a library (or API) that’s efficient and well tested.",
                "==============================",
                "The code that performs addition may look something like this:",
                "def to_add_numbers(x, y):",
                "  print(library_or_api.add_together(x + y))",
                "==============================",
                "As for the explanation:",
                "An API (Application Programming Interface) is",
                "the point where two pieces of software communicate with each other.",
                "==============================",
                "Putting all this together, you should find out that DataAPI is an API",
                "that helps with managing/storing/caching/retrieving information/data",
                "within a file system."
        ));

        configuration.setComments("source_code_github", List.of("The src code of this API"));
        configuration.set("source_code_github", "https://github.com/Manered/DataAPI");

        try {
            configuration.save(txtFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull String apiRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull JavaPlugin apiSource() {
        return source;
    }

    /**
     * {@inheritDoc}
     */
    public static @NotNull String apiFolder() {
        return folderName;
    }
}
