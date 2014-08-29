package test.domain;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@PrimaryKeyClass
public class DimTypesByMetadataKey implements Serializable{

    @PrimaryKeyColumn(name = "client", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String client;

    @PrimaryKeyColumn(name = "broker", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String broker;

    @PrimaryKeyColumn(name = "recontype", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String recontype;

    public DimTypesByMetadataKey() {
    }

    public DimTypesByMetadataKey(String client, String broker, String recontype) {
        this.client = client;
        this.broker = broker;
        this.recontype = recontype;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getRecontype() {
        return recontype;
    }

    public void setRecontype(String recontype) {
        this.recontype = recontype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DimTypesByMetadataKey)) return false;

        DimTypesByMetadataKey that = (DimTypesByMetadataKey) o;

        if (!broker.equals(that.broker)) return false;
        if (!client.equals(that.client)) return false;
        if (!recontype.equals(that.recontype)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = client.hashCode();
        result = 31 * result + broker.hashCode();
        result = 31 * result + recontype.hashCode();
        return result;
    }
}
