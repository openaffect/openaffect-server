package io.openaffect.repositories;

import io.openaffect.api.model.Measure;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Olivier Liechti on 05/03/17.
 */
public interface MeasureRepository extends MongoRepository<Measure, String> {
    // Find by trigger href, if the href contains the given string.
    @Query("{trigger.href: {$regex: '.*?0.*'}}")
    List<Measure> findByTriggerHref(String href);

    @Query("{timestamp: {$gte: ?1, $lte: ?2}}")
    List<Measure> findBetweenDates(DateTime fromDate, DateTime toDate);

    @Query("{trigger.href: {$regex: '.*?0.*'}, timestamp: {$gte: ?1, $lte: ?2}}")
    List<Measure> findByTriggerHrefAndBetweenDates(String href, DateTime fromDate, DateTime toDate);

    @Query(value = "{trigger.href: ?0}", delete = true)
    void deleteByTriggerHref(String href);
}
