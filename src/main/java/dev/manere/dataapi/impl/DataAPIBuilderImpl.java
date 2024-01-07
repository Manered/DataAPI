package dev.manere.dataapi.impl;

import dev.manere.dataapi.api.DataAPI;
import dev.manere.dataapi.api.DataAPIBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DataAPIBuilderImpl implements DataAPIBuilder {
    private JavaPlugin source;
    private String folderName;
    private String root;

    public DataAPIBuilderImpl() {
        this.source = null;
        this.folderName = null;
        this.root = "~";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataAPIBuilder source(final @NotNull JavaPlugin source) {
        this.source = source;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataAPIBuilder folder(final @NotNull String folderName) {
        this.folderName = folderName;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataAPIBuilder rootChar(final @NotNull String root) {
        this.root = root;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull DataAPI register() {
        final DataAPI api = DataAPI.init(source, folderName);

        api.rootChar(root);
        api.register();

        return api;
    }
}
