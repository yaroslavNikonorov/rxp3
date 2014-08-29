package test.repository;

import com.datastax.driver.core.ResultSet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import test.domain.Record;
import test.domain.Target;

import java.util.UUID;

/**
 * Created by yar on 21.07.14.
 */
//public interface TargetRepository extends CassandraRepository<Target> {
public interface TargetRepository extends CrudRepository<Target, UUID> {
    @Query(value = "UPDATE target SET records = records + {?1} where key = ?0")
    ResultSet addRecord(UUID uuid, UUID recordUuid);
}

