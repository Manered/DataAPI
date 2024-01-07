package dev.manere.dataapi.util;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * The FileResources class provides utility methods for working with files and file paths.
 */
public class FileResources {
    /**
     * Creates a new File instance using the specified parent and child path elements.
     *
     * @param parent The parent path.
     * @param child  The child path.
     * @return The created File instance.
     */
    @CanIgnoreReturnValue
    public static @NotNull File file(final @NotNull String parent, final @NotNull String child) {
        return new File(parent, child);
    }

    /**
     * Creates a new File instance using the specified path.
     *
     * @param path The path of the file.
     * @return The created File instance.
     */
    @CanIgnoreReturnValue
    public static @NotNull File file(final @NotNull String path) {
        return new File(path);
    }

    /**
     * Creates a new File instance using the specified parent File and child path.
     *
     * @param parent The parent File.
     * @param child  The child path.
     * @return The created File instance.
     */
    @CanIgnoreReturnValue
    public static @NotNull File file(final @NotNull File parent, final @NotNull String child) {
        return new File(parent, child);
    }
}
