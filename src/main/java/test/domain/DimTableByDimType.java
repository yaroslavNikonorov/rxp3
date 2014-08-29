package test.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 * Created by yar on 22.07.14.
 */
@Table(value = "dimensions_table_by_dimentions_type")
public class DimTableByDimType {
    @PrimaryKey
    private UUID key=UUID.randomUUID();

    @Column(value = "dimensions_columns")
    private Set<String> dimensionsColumns = new TreeSet<String>();

    @Column(value = "dimensions_composite_columns")
    private Set<String> DimensionsCompositeColumns = new TreeSet<String>();

    @Column(value = "dimensions_table")
    private String dimensionsTable;

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public Set<String> getDimensionsColumns() {
        return dimensionsColumns;
    }

    public void setDimensionsColumns(Set<String> dimensionsColumns) {
        this.dimensionsColumns = dimensionsColumns;
    }

    public Set<String> getDimensionsCompositeColumns() {
        return DimensionsCompositeColumns;
    }

    public void setDimensionsCompositeColumns(Set<String> dimensionsCompositeColumns) {
        DimensionsCompositeColumns = dimensionsCompositeColumns;
    }

    public String getDimensionsTable() {
        return dimensionsTable;
    }

    public void setDimensionsTable(String dimensionsTable) {
        this.dimensionsTable = dimensionsTable;
    }
}
