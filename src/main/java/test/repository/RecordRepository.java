package test.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import test.domain.Record;

import java.util.UUID;

/**
 * Created by yar on 21.07.14.
 */
//public interface RecordRepository extends CassandraRepository<Record> {
public interface RecordRepository extends CrudRepository<Record, UUID> {
}

