package dev.manere.dataapi.impl;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.manere.dataapi.api.DataEditor;
import dev.manere.dataapi.api.DataResourceBase;
import dev.manere.dataapi.util.NodePath;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class DataEditorImpl<T extends DataResourceBase<T>> implements DataEditor<T> {
    private final T resource;

    public DataEditorImpl(final @NotNull T resource) {
        this.resource = resource;

        resource().reload();
    }

    /**
     * {@inheritDoc}
     */
    @CanIgnoreReturnValue
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private @NotNull FileConfiguration config() {
        final File file = resource.file();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull T resource() {
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(final @NotNull NodePath parent, final @NotNull String name, final @Nullable Object value) {
        final String path = parent.convert() + name;
        config().set(path, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable Object retrieve(final @NotNull NodePath parent, final @NotNull String name) {
        final String path = parent.convert() + name;
        return config().get(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> @Nullable V retrieve(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Class<V> type) {
        return type.cast(retrieve(parent, name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable <E> List<E> retrieveList(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Class<E> type) {
        //noinspection unchecked
        return (List<E>) retrieve(parent, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull List<Object> values() {
        final Collection<Object> collection = config().getValues(true).values();
        return new ArrayList<>(collection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull List<String> keys() {
        final Set<String> set = config().getValues(true).keySet();
        return new ArrayList<>(set);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Map<String, Object> nodes() {
        return config().getValues(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull <V> List<V> values(final @NotNull Class<V> requiredType) {
        final List<V> values = new ArrayList<>();

        for (final Object object : values()) {
            if (requiredType.isInstance(object)) {
                values.add(requiredType.cast(object));
            }
        }

        return values;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public @NotNull <V> List<V> values(final @NotNull Predicate<V> condition) {
        final List<V> values = new ArrayList<>();

        for (final Object object : values()) {
            if (condition.test((V) object)) {
                values.add((V) object);
            }
        }

        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable ConfigurationSection retrieveSection(final @NotNull NodePath parent, final @NotNull String name) {
        final String path = parent.convert() + name;
        return config().getConfigurationSection(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull ConfigurationSection storeSection(final @NotNull NodePath parent, final @NotNull String name) {
        final String path = parent.convert() + name;
        return config().createSection(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull ConfigurationSection storeSection(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Map<?, ?> children) {
        final String path = parent.convert() + name;
        return config().createSection(path, children);
    }
}
