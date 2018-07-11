package io.openaffect.api;

import io.openaffect.api.model.Measure;
import io.openaffect.entities.MeasureEntity;
import io.openaffect.repositories.MeasureRepository;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-05T13:15:18.047+01:00")

@Controller
public class MeasuresApiController implements MeasuresApi {

    @Autowired
    MeasureRepository measureRepository;

    private ApiError unprocessablbeEntityError = new ApiError(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "The request was well-formed but was unable to be followed due to semantic errors.",
            ""
    );

    private ApiError notFoundError = new ApiError(
            HttpStatus.NOT_FOUND,
            "A needed resource could not be found but may be available in the future.",
            ""
    );

    @Override
    public ResponseEntity<Void> deleteMeasuresByTrigger(@NotNull @ApiParam(value = "the trigger's href of the measures to delete", required = true) @RequestParam(value = "triggerHref", required = true) String triggerHref) {
        try {
            measureRepository.deleteByTriggerHref(triggerHref);
        } catch (Exception ex) {
            unprocessablbeEntityError.setErrors(ex.getLocalizedMessage());
            return new ResponseEntity(unprocessablbeEntityError, new HttpHeaders(), unprocessablbeEntityError.getStatus());
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> editMeasure(@NotNull @ApiParam(value = "the trigger's href of the measure", required = true) @RequestParam(value = "triggerHref", required = true) String triggerHref,
                                               @ApiParam(value = "", required = true) @RequestBody @Valid Measure editedMeasure) {
        List<MeasureEntity> measureEntities = measureRepository.findByTriggerHref(triggerHref);

        if (measureEntities.isEmpty()) {
            notFoundError.setErrors(new StringBuilder().append("The measure whose trigger's href is '").append(triggerHref).append("' cannot be found.").toString());
            return new ResponseEntity(notFoundError, new HttpHeaders(), notFoundError.getStatus());
        }

        MeasureEntity measureEntity = measureEntities.get(0);

        measureEntity.setEmotion(editedMeasure.getEmotion());
        measureEntity.setSensor(editedMeasure.getSensor());
        measureEntity.setSubject(editedMeasure.getSubject());
        measureEntity.setTimestamp(editedMeasure.getTimestamp());
        measureEntity.setTrigger(editedMeasure.getTrigger());

        try {
            measureRepository.save(measureEntity);
        } catch (Exception ex) {
            unprocessablbeEntityError.setErrors(ex.getLocalizedMessage());
            return new ResponseEntity(unprocessablbeEntityError, new HttpHeaders(), unprocessablbeEntityError.getStatus());
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Measure>> listMeasures( @ApiParam(value = "the trigger href field to look for") @RequestParam(value = "triggerHref", required = false) String triggerHref,
                                                    @ApiParam(value = "a string that the trigger href field must contain") @RequestParam(value = "triggerHrefLike", required = false) String triggerHrefLike,
                                                    @ApiParam(value = "the inclusive yyyy-MM-dd'T'HH:mm:ss.SSSZ date from which the measures are collected (only works if one of the triggerHref parameters was given), for example 2018-07-02T10:18:32.123Z") @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime fromDate,
                                                    @ApiParam(value = "the inclusive yyyy-MM-dd'T'HH:mm:ss.SSSZ date to which the measures are collected (only works if one of the triggerHref parameter was given), for example 2018-07-03T00:00:00.000Z") @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime toDate) {
        if (triggerHref == null && triggerHrefLike == null) {
            return new ResponseEntity<List<Measure>>(measureRepository.findAll().stream().map(measureEntity -> toMeasure(measureEntity)).collect(Collectors.toList()), HttpStatus.OK);
        } else if (triggerHrefLike != null) {
            if (fromDate == null || toDate == null) {
                return new ResponseEntity<List<Measure>>(measureRepository.findIfTriggerHrefContains(triggerHrefLike).stream().map(measureEntity -> toMeasure(measureEntity)).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Measure>>(measureRepository.findIfTriggerHrefContainsAndIsBetweenDates(triggerHrefLike, fromDate, toDate).stream().map(measureEntity -> toMeasure(measureEntity)).collect(Collectors.toList()), HttpStatus.OK);
            }
        } else {
            if (fromDate == null || toDate == null) {
                return new ResponseEntity<List<Measure>>(measureRepository.findByTriggerHref(triggerHref).stream().map(measureEntity -> toMeasure(measureEntity)).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Measure>>(measureRepository.findByTriggerHrefAndBetweenDates(triggerHref, fromDate, toDate).stream().map(measureEntity -> toMeasure(measureEntity)).collect(Collectors.toList()), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Object> reportMeasure(@ApiParam(value = "measure reported by the sensor", required = true) @RequestBody @Valid Measure measure) {
        try {
            measureRepository.save(toMeasureEntity(measure));
        } catch (Exception ex) {
            unprocessablbeEntityError.setErrors(ex.getLocalizedMessage());
            return new ResponseEntity(unprocessablbeEntityError, new HttpHeaders(), unprocessablbeEntityError.getStatus());
        }

        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    private MeasureEntity toMeasureEntity(Measure measure) {
        MeasureEntity entity = new MeasureEntity();
        entity.setEmotion(measure.getEmotion());
        entity.setSensor(measure.getSensor());
        entity.setSubject(measure.getSubject());
        entity.setTimestamp(measure.getTimestamp());
        entity.setTrigger(measure.getTrigger());
        return entity;
    }

    private Measure toMeasure(MeasureEntity measureEntity) {
        Measure measure = new Measure();
        measure.setEmotion(measureEntity.getEmotion());
        measure.setSensor(measureEntity.getSensor());
        measure.setSubject(measureEntity.getSubject());
        measure.setTimestamp(measureEntity.getTimestamp());
        measure.setTrigger(measureEntity.getTrigger());
        return measure;
    }

}
