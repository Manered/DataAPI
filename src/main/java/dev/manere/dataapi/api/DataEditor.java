package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.manere.dataapi.util.NodePath;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static dev.manere.dataapi.util.NodePathInternal.nameOnly;
import static dev.manere.dataapi.util.NodePathInternal.pathOnly;

/**
 * The DataEditor interface provides methods for storing, retrieving, and manipulating data in a resource.
 * It supports various operations such as storing values, retrieving values, working with lists and sections, and more.
 *
 * @param <R> The type of the resource being edited.
 */
public interface DataEditor<R> {
    /**
     * Comments a node.
     *
     * @param comments The comment
     */
    void comment(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Collection<String> comments);

    /**
     * Comments a node.
     *
     * @param comments The comment
     */
    default void comment(final @NotNull NodePath parent, final @NotNull String name, final @NotNull String... comments) {
        comment(parent, name, Arrays.asList(comments));
    }

    /**
     * Comments a node.
     *
     * @param comments The comment
     */
    default void comment(final @NotNull String path, final @NotNull List<String> comments) {
        comment(pathOnly(path), nameOnly(path), comments);
    }

    /**
     * Comments a node.
     *
     * @param comments The comment
     */
    default void comment(final @NotNull String path, final @NotNull String... comments) {
        comment(path, Arrays.asList(comments));
    }

    /**
     * Sets the file header.
     *
     * @param header The header
     */
    void header(final @NotNull Collection<String> header);

    /**
     * Sets the file header.
     *
     * @param header The header
     */
    default void header(final @NotNull String... header) {
        header(Arrays.asList(header));
    }

    /**
     * Sets the file footer.
     *
     * @param footer The footer
     */
    void footer(final @NotNull Collection<String> footer);

    /**
     * Sets the file footer.
     *
     * @param footer The footer
     */
    default void footer(final @NotNull String... footer) {
        footer(Arrays.asList(footer));
    }

    /**
     * Retrieves the resource associated with this DataEditor.
     *
     * @return The resource.
     */
    @NotNull R resource();

    /**
     * Stores a value in the specified node path.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @param value  The value to store.
     */
    void store(final @NotNull NodePath parent, final @NotNull String name, final @Nullable Object value);

    /**
     * Stores a value in the specified node path.
     *
     * @param path   The node path.
     * @param value  The value to store.
     */
    default void store(final @NotNull String path, final @Nullable Object value) {
        store(
                pathOnly(path), nameOnly(path),
                value
        );
    }

    /**
     * Retrieves the value associated with the specified node path and name.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @return The retrieved value, or null if not found.
     */
    @Nullable Object retrieve(final @NotNull NodePath parent, final @NotNull String name);

    /**
     * Retrieves the value associated with the specified node path and name.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @param def    The default value.
     * @return The retrieved value, or the default value if not found.
     */
    @SuppressWarnings("unchecked")
    default @NotNull <D> D retrieve(final @NotNull NodePath parent, final @NotNull String name, final D def) {
        final Object nullable = retrieve(parent, name);
        if (nullable == null) return def;
        return (D) nullable;
    }

    /**
     * Retrieves the value associated with the specified node path and name.
     *
     * @param path   The node path.
     * @return The retrieved value, or null if not found.
     */
    default @Nullable Object retrieve(final @NotNull String path) {
        return retrieve(
                pathOnly(path), nameOnly(path)
        );
    }

    /**
     * Retrieves the value associated with the specified node path and name.
     *
     * @param path   The node path.
     * @param def    The default value.
     * @return The retrieved value, or the default value if not found.
     */
    default @NotNull <D> D retrieve(final @NotNull String path, final D def) {
        return retrieve(pathOnly(path), nameOnly(path), def);
    }

    /**
     * Retrieves the value associated with the specified node path and name, casting it to the specified type.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @param type   The expected type of the value.
     * @param <V>    The type parameter.
     * @return The retrieved value, or null if not found or not of the expected type.
     */
    @Nullable <V> V retrieve(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Class<V> type);

    /**
     * Retrieves the value associated with the specified node path and name, casting it to the specified type.
     *
     * @param path   The node path.
     * @param type   The expected type of the value.
     * @param <V>    The type parameter.
     * @return The retrieved value, or null if not found or not of the expected type.
     */
    default @Nullable <V> V retrieve(final @NotNull String path, final @NotNull Class<V> type) {
        return retrieve(
                pathOnly(path), nameOnly(path),
                type
        );
    }

    /**
     * Retrieves a list of values associated with the specified node path and name, casting them to the specified type.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @param type   The expected type of the values in the list.
     * @param <E>    The type parameter.
     * @return The retrieved list of values, or an empty list if not found or not of the expected type.
     */
    @NotNull <E> List<E> retrieveList(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Class<E> type);

    /**
     * Retrieves a list of values associated with the specified node path and name, casting them to the specified type.
     *
     * @param path   The node path.
     * @param type   The expected type of the values in the list.
     * @param <E>    The type parameter.
     * @return The retrieved list of values, or an empty list if not found or not of the expected type.
     */
    default @Nullable <E> List<E> retrieveList(final @NotNull String path, final @NotNull Class<E> type) {
        return retrieveList(
                pathOnly(path), nameOnly(path),
                type
        );
    }

    @NotNull Map<String, Object> pairs();

    @NotNull <V> Map<String, V> pairs(@NotNull Class<V> requiredType);

    /**
     * Retrieves a list of all values stored in the resource.
     *
     * @return A list of all values.
     */
    @NotNull List<Object> values();

    /**
     * Retrieves a list of all keys in the resource.
     *
     * @return A list of all keys.
     */
    @NotNull List<String> keys();

    /**
     * Retrieves a map of scalar nodes (nodes with primitive values) in the resource.
     *
     * @return A map of scalar nodes.
     */
    @NotNull Map<String, Object> nodes();

    /**
     * Retrieves a list of values from the resource, casting them to the specified type.
     *
     * @param requiredType The expected type of the values.
     * @param <V>          The type parameter.
     * @return A list of values of the specified type.
     */
    @NotNull <V> List<V> values(final @NotNull Class<V> requiredType);

    /**
     * Retrieves a filtered list of values from the resource based on the provided condition.
     *
     * @param condition The condition to filter values.
     * @param <V>       The type parameter.
     * @return A filtered list of values.
     */
    @NotNull <V> List<V> values(final @NotNull Predicate<V> condition);

    /**
     * Retrieves a ConfigurationSection associated with the specified node path and name.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @return The retrieved ConfigurationSection, or null if not found.
     */
    @Nullable ConfigurationSection retrieveSection(final @NotNull NodePath parent, final @NotNull String name);

    /**
     * Retrieves a ConfigurationSection associated with the specified node path and name.
     *
     * @param path   The node path.
     * @return The retrieved ConfigurationSection, or null if not found.
     */
    default @Nullable ConfigurationSection retrieveSection(final @NotNull String path) {
        return retrieveSection(pathOnly(path), nameOnly(path));
    }

    /**
     * Stores an empty ConfigurationSection in the specified node path.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @return The created or existing ConfigurationSection.
     */
    @CanIgnoreReturnValue
    @NotNull ConfigurationSection storeSection(final @NotNull NodePath parent, final @NotNull String name);

    /**
     * Stores an empty ConfigurationSection in the specified node path.
     *
     * @param path   The node path.
     * @return       The created or existing ConfigurationSection.
     */
    @CanIgnoreReturnValue
    default @NotNull ConfigurationSection storeSection(final @NotNull String path) {
        return storeSection(pathOnly(path), nameOnly(path));
    }

    /**
     * Stores a ConfigurationSection with the provided children in the specified node path.
     *
     * @param parent   The parent node path.
     * @param name     The name of the node.
     * @param children The children to be stored in the ConfigurationSection.
     * @return The created or existing ConfigurationSection.
     */
    @CanIgnoreReturnValue
    @NotNull ConfigurationSection storeSection(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Map<?, ?> children);

    /**
     * Stores a ConfigurationSection with the provided children in the specified node path.
     *
     * @param path   The node path.
     * @param children The children to be stored in the ConfigurationSection.
     * @return The created or existing ConfigurationSection.
     */
    @CanIgnoreReturnValue
    default @NotNull ConfigurationSection storeSection(final @NotNull String path, final @NotNull Map<?, ?> children) {
        return storeSection(pathOnly(path), nameOnly(path), children);
    }
}
