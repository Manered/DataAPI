package dev.manere.dataapi.util;

import dev.manere.dataapi.impl.DataAPIImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The NodePath class represents a path of string nodes, typically used for data resource hierarchy.
 */
public class NodePath {
    private final List<String> parents;

    /**
     * Constructs an empty NodePath.
     */
    public NodePath() {
        this.parents = new ArrayList<>();
    }

    /**
     * Constructs a NodePath with the specified parent nodes.
     *
     * @param parents The parent nodes.
     */
    public NodePath(final @NotNull String... parents) {
        this.parents = new ArrayList<>(Arrays.asList(parents));
    }

    /**
     * Constructs a NodePath with the specified list of parent nodes.
     *
     * @param parents The list of parent nodes.
     */
    public NodePath(final @NotNull List<String> parents) {
        this.parents = parents;
    }

    /**
     * Creates a new NodePath with the specified parent nodes.
     *
     * @param parents The parent nodes.
     * @return The created NodePath instance.
     */
    public static @NotNull NodePath parents(final @NotNull String... parents) {
        return new NodePath(parents);
    }

    /**
     * Creates a new NodePath with the specified list of parent nodes.
     *
     * @param parents The list of parent nodes.
     * @return The created NodePath instance.
     */
    public static @NotNull NodePath parents(final @NotNull List<String> parents) {
        return new NodePath(parents);
    }

    /**
     * Creates a new NodePath representing the root node.
     *
     * @return The created NodePath instance representing the root.
     */
    public static @NotNull NodePath root() {
        return new NodePath(new ArrayList<>(Collections.singletonList(DataAPIImpl.apiRoot())));
    }

    /**
     * Retrieves the list of parent nodes.
     *
     * @return The list of parent nodes.
     */
    public @NotNull List<String> parents() {
        return parents;
    }

    /**
     * Converts the NodePath to a dot-separated string.
     *
     * @return The dot-separated string representation of the NodePath.
     */
    public @NotNull String convert() {
        final StringBuilder builder = new StringBuilder();

        for (final String parent : parents) {
            builder.append(parent).append(".");
        }

        return builder.toString();
    }

    /**
     * Converts the specified NodePath to a dot-separated string.
     *
     * @param path The NodePath to convert.
     * @return The dot-separated string representation of the NodePath.
     */
    public static @NotNull String convert(final @NotNull NodePath path) {
        return path.convert();
    }
}
