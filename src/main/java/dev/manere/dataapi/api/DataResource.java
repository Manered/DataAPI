package dev.manere.dataapi.api;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * The DataResource interface extends DataResourceBase and provides additional methods specific to general data resources.
 */
public interface DataResource extends DataResourceBase<DataResource> {
    /**
     * {@inheritDoc}
     */
    @Override
    @CanIgnoreReturnValue
    @NotNull DataResource save();

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull DataEditor<DataResource> editor();

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
}
