package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.manere.dataapi.impl.DataAPIImpl;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * The DataAPI interface provides methods for managing and accessing data resources.
 * It allows initialization, configuration, and retrieval of data resources.
 */
public interface DataAPI {
    /**
     * Initializes the DataAPI with the provided JavaPlugin as the data src.
     *
     * @param source The JavaPlugin to be used as the data src.
     * @return The initialized DataAPI instance.
     */
    @CanIgnoreReturnValue
    static @NotNull DataAPI init(final @NotNull JavaPlugin source) {
        final DataAPIImpl handler = new DataAPIImpl();

        handler.source(source);
        handler.folder(source.getName());
        handler.register();

        return handler;
    }

    /**
     * Initializes the DataAPI with the provided JavaPlugin and folder name.
     *
     * @param source      The JavaPlugin to be used as the data src.
     * @param folderName  The name of the folder to store data.
     * @return The initialized DataAPI instance.
     */
    @CanIgnoreReturnValue
    static @NotNull DataAPI init(final @NotNull JavaPlugin source, final @NotNull String folderName) {
        final DataAPIImpl handler = new DataAPIImpl();

        handler.source(source);
        handler.folder(folderName);
        handler.register();

        return handler;
    }

    /**
     * Creates a new DataAPIBuilder instance for building DataAPI objects.
     *
     * @return A DataAPIBuilder instance.
     */
    @CanIgnoreReturnValue
    static @NotNull DataAPIBuilder builder() {
        return DataAPIBuilder.of();
    }

    /**
     * Initializes the DataAPI with the provided JavaPlugin, folder name and root character.
     *
     * @param source      The JavaPlugin to be used as the data src.
     * @param folderName  The name of the folder to store data.
     * @return The initialized DataAPI instance.
     */
    @CanIgnoreReturnValue
    static @NotNull DataAPI init(final @NotNull JavaPlugin source, final @NotNull String folderName, final @NotNull String rootChar) {
        final DataAPIImpl handler = new DataAPIImpl();

        handler.source(source);
        handler.folder(folderName);
        handler.register();

        return handler;
    }

    /**
     * Sets the JavaPlugin src for the DataAPI instance.
     *
     * @param source The JavaPlugin src to be set.
     */
    void source(final @NotNull JavaPlugin source);

    /**
     * Sets the folder name for storing data in the DataAPI instance.
     *
     * @param folderName The folder name to be set.
     */
    void folder(final @NotNull String folderName);

    /**
     * Retrieves a DataResource instance based on the parent and name.
     *
     * @param parent The parent folder.
     * @param name   The name of the data resource.
     * @return A DataResource instance.
     */
    @NotNull DataResource data(final @NotNull String parent, final @NotNull String name);

    /**
     * Retrieves a DataResource instance based on the name.
     *
     * @param name The name of the data resource.
     * @return A DataResource instance.
     */
    @NotNull DataResource data(final @NotNull String name);

    /**
     * Retrieves a PlayerDataResource instance based on the parent folder and Player object.
     *
     * @param parent The parent folder.
     * @param player The Player object.
     * @return A PlayerDataResource instance.
     */
    default @NotNull PlayerDataResource player(final @NotNull String parent, final @NotNull Player player) {
        return player(parent, player.getUniqueId());
    }

    /**
     * Retrieves a PlayerDataResource instance based on the parent folder and OfflinePlayer object.
     *
     * @param parent The parent folder.
     * @param player The OfflinePlayer object.
     * @return A PlayerDataResource instance.
     */
    default @NotNull PlayerDataResource player(final @NotNull String parent, final @NotNull OfflinePlayer player) {
        return player(parent, player.getUniqueId());
    }

    /**
     * Retrieves a PlayerDataResource instance based on the parent folder and UUID.
     *
     * @param parent The parent folder.
     * @param uuid   The UUID of the player.
     * @return A PlayerDataResource instance.
     */
    @NotNull PlayerDataResource player(final @NotNull String parent, final @NotNull UUID uuid);

    /**
     * Retrieves a PlayerDataResource instance based on the UUID.
     *
     * @param uuid The UUID of the player.
     * @return A PlayerDataResource instance.
     */
    @NotNull PlayerDataResource player(final @NotNull UUID uuid);

    /**
     * Retrieves the JavaPlugin src for the DataAPI instance.
     *
     * @return The JavaPlugin src.
     */
    @NotNull JavaPlugin source();

    /**
     * Retrieves the folder name for storing data in the DataAPI instance.
     *
     * @return The folder name.
     */
    @NotNull String folder();

    /**
     * Retrieves the root folder path for storing data in the DataAPI instance.
     *
     * @return The root folder path.
     */
    @NotNull String rootChar();

    /**
     * Sets the root folder path for storing data in the DataAPI instance.
     *
     * @param root The root folder path to be set.
     */
    void rootChar(final @NotNull String root);

    /**
     * Registers the DataAPI instance, initializing and configuring it for use.
     */
    void register();
}
