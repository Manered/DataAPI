package dev.manere.dataapi.impl;

import dev.manere.dataapi.api.DataAPI;
import dev.manere.dataapi.api.DataResource;
import dev.manere.dataapi.api.PlayerDataResource;
import dev.manere.dataapi.util.FileResources;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
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
        if (source == null) throw new NullPointerException("source cannot be null");
        if (folderName == null) folder(source.getName());
        if (root == null) rootChar('~');

        final File pluginsFolder = source.getServer().getPluginsFolder();
        final File dataFolder = FileResources.file(pluginsFolder, "/data/");
        final File registeredFolder = FileResources.file(dataFolder, "/" + folderName + "/");

        if (!dataFolder.exists()) dataFolder.mkdirs();
        if (!registeredFolder.exists()) registeredFolder.mkdirs();
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
