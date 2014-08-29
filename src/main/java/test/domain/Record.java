package test.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yar on 22.07.14.
 */
@Table
public class Record {

    @PrimaryKey
    private UUID key = UUID.randomUUID();

    private String broker;
    private String client;
    private String recEntity;
    private String fund;
    private String side;
    private String recontype = "TRANX";
    private String platform;
    private String dimensionType;

    private Map<String, String> fields = new HashMap<String, String>();

    public Record() {
    }

    public Record(String broker, String client, Map<String, String> fields, String recEntity) {
        this.broker = broker;
        this.client = client;
        this.fields = fields;
        this.recEntity=recEntity;
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

    public String getRecEntity() {
        return recEntity;
    }

    public void setRecEntity(String recEntity) {
        this.recEntity = recEntity;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getRecontype() {
        return recontype;
    }

    public void setRecontype(String recontype) {
        this.recontype = recontype;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}



