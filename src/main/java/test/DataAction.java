package test;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import test.domain.*;
import test.repository.DimTableByDimTypeRepository;
import test.repository.DimTypesByMetadataRepository;
import test.repository.RecordRepository;
import test.repository.TargetRepository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by yar on 25.07.14.
 */

@Component
public class DataAction {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private DimTypesByMetadataRepository dimTypesByMetadataRepository;

    @Autowired
    private DimTableByDimTypeRepository dimTableByDimTypeRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @PostConstruct
    public void start() {
//        UUID uuid = UUID.fromString("25bbee41-b2c1-4d47-818d-77bcc627ea35");
//        recordHandler(recordRepository.findOne(uuid));
        for (Record record : recordRepository.findAll()) {
            recordHandler(record);
        }
    }

    public void recordHandler(Record record) {
        DimTypesByMetadataKey key = new DimTypesByMetadataKey(record.getClient(), record.getBroker(), record.getRecontype());
        DimTypesByMetadata dimTypesByMetadata = dimTypesByMetadataRepository.findOne(key);
        for (UUID uuid : dimTypesByMetadata.getDimensionsTypes()) {
            DimTableByDimType dimTableByDimType = dimTableByDimTypeRepository.findOne(uuid);
            if (dimTableByDimType != null && dimColumnsIsInRecordFields(record, dimTableByDimType)) {
                targetByRecHandler(record, dimTableByDimType);
            }
        }
    }

    private void targetByRecHandler(Record record, DimTableByDimType dimTableByDimType) {
        Select select = QueryBuilder.select().from(dimTableByDimType.getDimensionsTable());
        select.where(QueryBuilder.eq("recentity", record.getRecEntity()));
        for (String column : dimTableByDimType.getDimensionsColumns()) {
            select.where().and(QueryBuilder.eq(column.toLowerCase(), record.getFields().get(column.toLowerCase())));
        }
        List result = cassandraOperations.queryForListOfMap(select);
        if (result != null && result.size() > 0) {
            UUID targetUUID = null;
            for (Object resultObj : result) {
                Map resultMap = (Map<String, Object>) resultObj;
                if (compareDimCompositeColumns(record, dimTableByDimType, resultMap)) {
                    targetUUID = (UUID) resultMap.get("target_key");
                    break;
                }
            }
            if (targetUUID != null) {
                targetAddRecord(record, targetUUID);
            } else {
                Target target = targetAddNew(record, dimTableByDimType);
                targetByRecAdd(record, dimTableByDimType, target);
            }
        } else {
            Target target = targetAddNew(record, dimTableByDimType);
            targetByRecAdd(record, dimTableByDimType, target);
        }
    }

    private boolean compareDimCompositeColumns(Record record, DimTableByDimType dimTableByDimType, Map resultMap) {
        for (String dimCompositeColumn : dimTableByDimType.getDimensionsCompositeColumns()) {
            if (record.getFields().containsKey(dimCompositeColumn.toLowerCase())
                    && record.getFields().get(dimCompositeColumn.toLowerCase()).equals(resultMap.get(dimCompositeColumn.toLowerCase()))) {
                return true;
            }
        }
        return false;
    }


    private boolean dimColumnsIsInRecordFields(Record record, DimTableByDimType dimTableByDimType) {
        if (record.getRecEntity() == null || record.getRecEntity().isEmpty()) {
            return false;
        }
        for (String column : dimTableByDimType.getDimensionsColumns()) {
            if (!record.getFields().containsKey(column.toLowerCase())
                    || record.getFields().get(column.toLowerCase()) == null
                    || record.getFields().get(column.toLowerCase()).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void targetAddRecord(Record record, UUID targetUUID) {
        System.out.println("Add record to target");
        targetRepository.addRecord(targetUUID, record.getKey());
    }

    private Target targetAddNew(Record record, DimTableByDimType dimTableByDimType) {
        System.out.println("Add new target");
        Map<String, String> dimensions = new HashMap<String, String>();
        for (String dimField : dimTableByDimType.getDimensionsColumns()) {
            dimensions.put(dimField.toLowerCase(), record.getFields().get(dimField.toLowerCase()));
        }
        for (String dimComField : dimTableByDimType.getDimensionsCompositeColumns()) {
            if (record.getFields().containsKey(dimComField.toLowerCase())) {
                dimensions.put(dimComField.toLowerCase(), record.getFields().get(dimComField.toLowerCase()));
            }
        }
        Set<UUID> recordUUID = new TreeSet<UUID>();
        recordUUID.add(record.getKey());
        Target target = new Target(record.getClient(), record.getBroker(), dimensions, recordUUID, record.getRecEntity());
        targetRepository.save(target);
        return target;
    }


    private void targetByRecAdd(Record record, DimTableByDimType dimTableByDimType, Target target) {
        System.out.println("Add new dim");
        Insert insert = QueryBuilder.insertInto(dimTableByDimType.getDimensionsTable());
        insert.value("client", record.getClient());
        insert.value("broker", record.getBroker());
        insert.value("target_key", target.getKey());
        insert.value("recentity", target.getRecentity());
        for (String field : dimTableByDimType.getDimensionsColumns()) {
            insert.value(field.toLowerCase(), record.getFields().get(field.toLowerCase()));
        }
        for (String compositeField : dimTableByDimType.getDimensionsCompositeColumns()) {
            if (record.getFields().containsKey(compositeField.toLowerCase())) {
                insert.value(compositeField.toLowerCase(), record.getFields().get(compositeField.toLowerCase()));
            }
        }
//        System.out.println(insert.toString());
        cassandraOperations.execute(insert);
    }
}
