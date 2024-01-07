package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * The DataResourceBase interface provides basic methods for managing data resources.
 *
 * @param <T> The type of the data resource.
 */
public interface DataResourceBase<T> {
    /**
     * Saves changes made to the data resource.
     *
     * @return The updated data resource instance.
     */
    @CanIgnoreReturnValue
    @NotNull T save();

    /**
     * Retrieves a DataEditor instance for editing the content of the data resource.
     *
     * @return A DataEditor instance.
     */
    @NotNull DataEditor<T> editor();

    /**
     * Reloads the content of the data resource.
     */
    void reload();

    /**
     * Retrieves the File associated with the data resource.
     *
     * @return The File representing the data resource.
     */
    @NotNull File file();
}
