package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.manere.dataapi.impl.DataAPIBuilderImpl;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * The DataAPIBuilder interface provides methods for building and configuring DataAPI instances.
 * It allows setting the source, folder, and root for the DataAPI instance before registration.
 */
public interface DataAPIBuilder {
    /**
     * Creates a new instance of DataAPIBuilder.
     *
     * @return A new DataAPIBuilder instance.
     */
    static @NotNull DataAPIBuilder of() {
        return new DataAPIBuilderImpl();
    }

    /**
     * Sets the JavaPlugin source for the DataAPI instance.
     *
     * @param source The JavaPlugin source to be set.
     * @return The DataAPIBuilder instance for method chaining.
     */
    @CanIgnoreReturnValue
    @NotNull DataAPIBuilder source(final @NotNull JavaPlugin source);

    /**
     * Sets the folder name for storing data in the DataAPI instance.
     *
     * @param folderName The folder name to be set.
     * @return The DataAPIBuilder instance for method chaining.
     */
    @CanIgnoreReturnValue
    @NotNull DataAPIBuilder folder(final @NotNull String folderName);

    /**
     * Sets the root folder path for storing data in the DataAPI instance.
     *
     * @param root The root folder path to be set.
     * @return The DataAPIBuilder instance for method chaining.
     */
    @CanIgnoreReturnValue
    @NotNull DataAPIBuilder rootChar(final @NotNull String root);

    /**
     * Registers and returns the configured DataAPI instance.
     *
     * @return The configured DataAPI instance.
     */
    @CanIgnoreReturnValue
    @NotNull DataAPI register();
}
