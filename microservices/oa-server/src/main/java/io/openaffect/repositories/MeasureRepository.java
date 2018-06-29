package io.openaffect.repositories;

import io.openaffect.entities.MeasureEntity;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Olivier Liechti on 05/03/17.
 */
public interface MeasureRepository extends MongoRepository<MeasureEntity, String> {
    // Find by trigger href, if the href contains the given string.
    @Query("{trigger.href: ?0}")
    List<MeasureEntity> findByTriggerHref(String href);

    @Query("{trigger.href: ?0, timestamp: {$gte: ?1, $lte: ?2}}")
    List<MeasureEntity> findByTriggerHrefAndBetweenDates(String href, DateTime fromDate, DateTime toDate);

    @Query(value = "{trigger.href: ?0}", delete = true)
    void deleteByTriggerHref(String href);
}
