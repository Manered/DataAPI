package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

/**
 * The PlayerDataResource interface extends DataResourceBase and provides additional methods specific to player-related data resources.
 */
public interface PlayerDataResource extends DataResourceBase<PlayerDataResource> {
    /**
     * {@inheritDoc}
     */
    @Override
    @CanIgnoreReturnValue
    @NotNull PlayerDataResource save();

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull DataEditor<PlayerDataResource> editor();

    /**
     * {@inheritDoc}
     */
    @Override
    void reload();

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull File file();

    /**
     * Retrieves the UUID associated with the player data resource.
     *
     * @return The UUID of the player.
     */
    @NotNull UUID uuid();
}
