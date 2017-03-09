package io.openaffect.repositories;

import io.openaffect.api.model.Measure;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Olivier Liechti on 05/03/17.
 */
public interface MeasureRepository extends MongoRepository<Measure, String> {
}
