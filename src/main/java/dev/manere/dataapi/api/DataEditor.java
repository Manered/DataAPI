package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.manere.dataapi.util.NodePath;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * The DataEditor interface provides methods for storing, retrieving, and manipulating data in a resource.
 * It supports various operations such as storing values, retrieving values, working with lists and sections, and more.
 *
 * @param <T> The type of the resource being edited.
 */
public interface DataEditor<T> {
    /**
     * Retrieves the resource associated with this DataEditor.
     *
     * @return The resource.
     */
    @NotNull T resource();

    /**
     * Stores a value in the specified node path.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @param value  The value to store.
     */
    void store(final @NotNull NodePath parent, final @NotNull String name, final @Nullable Object value);

    /**
     * Retrieves the value associated with the specified node path and name.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @return The retrieved value, or null if not found.
     */
    @Nullable Object retrieve(final @NotNull NodePath parent, final @NotNull String name);

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
     * Retrieves a list of values associated with the specified node path and name, casting them to the specified type.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @param type   The expected type of the values in the list.
     * @param <E>    The type parameter.
     * @return The retrieved list of values, or an empty list if not found or not of the expected type.
     */
    @Nullable <E> List<E> retrieveList(final @NotNull NodePath parent, final @NotNull String name, final @NotNull Class<E> type);

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
     * Stores an empty ConfigurationSection in the specified node path.
     *
     * @param parent The parent node path.
     * @param name   The name of the node.
     * @return The created or existing ConfigurationSection.
     */
    @CanIgnoreReturnValue
    @NotNull ConfigurationSection storeSection(final @NotNull NodePath parent, final @NotNull String name);

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
}
