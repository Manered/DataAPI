package dev.manere.dataapi.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for working with node paths.
 * This class provides methods to extract path-only or name-only components from a full node path.
 */
public class NodePathInternal {
    /**
     * Extracts the path-only component from a full node path.
     *
     * @param entirety The full node path.
     * @return A NodePath object representing the path-only component.
     * @throws IllegalArgumentException if the input node path is null.
     */
    @NotNull
    public static NodePath pathOnly(final @NotNull String entirety) {
        final String[] splitRaw = entirety.split("\\.");
        final List<String> split = new ArrayList<>(Arrays.asList(splitRaw));
        if (split.get(split.size() - 1) != null) split.remove(split.size() - 1);
        return NodePath.parents(split);
    }

    /**
     * Extracts the name-only component from a full node path.
     *
     * @param entirety The full node path.
     * @return The name-only component as a String. Returns an empty string if the input is empty.
     * @throws IllegalArgumentException if the input node path is null.
     */
    @NotNull
    public static String nameOnly(final @NotNull String entirety) {
        final String[] splitRaw = entirety.split("\\.");
        final List<String> split = new ArrayList<>(Arrays.asList(splitRaw));

        if (!split.isEmpty()) {
            final String name = split.get(split.size() - 1);
            return name == null ? "" : name;
        }

        return "";
    }
}
