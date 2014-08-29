package test.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import test.domain.DimTableByDimType;
import test.domain.DimTypesByMetadata;

import java.util.UUID;

/**
 * Created by yar on 21.07.14.
 */
//public interface DimTableByDimTypeRepository extends CassandraRepository<DimTableByDimType> {
public interface DimTableByDimTypeRepository extends CrudRepository<DimTableByDimType, UUID> {

//    Long deleteByLastname(String lastname);
//    List<Person> removeByLastname(String lastname);

}

