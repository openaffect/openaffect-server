package io.openaffect.repositories;

import io.openaffect.api.model.Measure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Olivier Liechti on 05/03/17.
 */
public interface MeasureRepository extends MongoRepository<Measure, String> {
    // Find by trigger href, if the href contains the given string.
    @Query("{trigger.href: {$regex: '.*?0.*'}}")
    List<Measure> listByTriggerHref(String href);

    @Query(value = "{trigger.href: ?0}", delete = true)
    void deleteByTriggerHref(String href);
}
