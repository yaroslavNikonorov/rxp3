package test.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@Table(value = "dimensions_types_by_metadata")
public class DimTypesByMetadata {

    @PrimaryKey(value = "key")
    private DimTypesByMetadataKey key;

    @Column(value = "dimensions_types")
    private Set<UUID> dimensionsTypes = new TreeSet<UUID>();

    public DimTypesByMetadata() {
    }

    public DimTypesByMetadata(DimTypesByMetadataKey key, Set<UUID> dimensionsTypes) {
        this.key = key;
        this.dimensionsTypes = dimensionsTypes;
    }

    public DimTypesByMetadataKey getKey() {
        return key;
    }

    public void setKey(DimTypesByMetadataKey key) {
        this.key = key;
    }

    public Set<UUID> getDimensionsTypes() {
        return dimensionsTypes;
    }

    public void setDimensionsTypes(Set<UUID> dimensionsTypes) {
        this.dimensionsTypes = dimensionsTypes;
    }
}
