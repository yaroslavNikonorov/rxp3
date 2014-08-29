package test.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.*;


@Table
public class Target {

    @PrimaryKey
    private UUID key = UUID.randomUUID();

    private String broker;
    private String client;

    private Map<String, String> dimensions = new HashMap<String, String>();
    private Set<UUID> records = new TreeSet<UUID>();
    private String recentity;


    public Target(String broker, String client, Map<String, String> dimensions, Set<UUID> records, String recentity) {
        this.broker = broker;
        this.client = client;
        this.dimensions = dimensions;
        this.records = records;
        this.recentity = recentity;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Map<String, String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, String> dimensions) {
        this.dimensions = dimensions;
    }

    public Set<UUID> getRecords() {
        return records;
    }

    public void setRecords(Set<UUID> records) {
        this.records = records;
    }

    public String getRecentity() {
        return recentity;
    }

    public void setRecentity(String recentity) {
        this.recentity = recentity;
    }
}




