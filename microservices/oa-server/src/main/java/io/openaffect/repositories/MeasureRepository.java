package io.openaffect.repositories;

import io.openaffect.api.model.Measure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Olivier Liechti on 05/03/17.
 */
public interface MeasureRepository extends MongoRepository<Measure, String> {
    @Query("{trigger.href: ?0}")
    List<Measure> listByTriggerHref(String href);
}
