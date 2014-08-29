package test.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import test.domain.DimTypesByMetadata;
import test.domain.DimTypesByMetadataKey;
import test.domain.Target;

/**
 * Created by yar on 21.07.14.
 */
public interface DimTypesByMetadataRepository extends CrudRepository<DimTypesByMetadata, DimTypesByMetadataKey> {
//public interface DimTypesByMetadataRepository extends CassandraRepository<DimTypesByMetadata> {

//    Long deleteByLastname(String lastname);
//    List<Person> removeByLastname(String lastname);
//    DimTypesByMetadata findOneByDimTypesByMetadataKey(DimTypesByMetadataKey key);

}

