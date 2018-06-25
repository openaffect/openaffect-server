package io.openaffect.api;

import io.openaffect.api.model.Measure;
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
    public ResponseEntity<List<Measure>> listMeasures( @ApiParam(value = "the trigger href field to look for") @RequestParam(value = "triggerHref", required = false) String triggerHref,
                                                       @ApiParam(value = "the inclusive date from which the measures are collected") @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime fromDate,
                                                       @ApiParam(value = "the inclusive date to which the measures are collected") @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime toDate) {
        if (triggerHref == null) {
            return new ResponseEntity<List<Measure>>(measureRepository.findAll(), HttpStatus.OK);
        } else {
            if (fromDate == null || toDate == null) {
                return new ResponseEntity<List<Measure>>(measureRepository.findByTriggerHref(triggerHref), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Measure>>(measureRepository.findByTriggerHrefAndBetweenDates(triggerHref, fromDate, toDate), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Object> reportMeasure(@ApiParam(value = "measure reported by the sensor", required = true) @RequestBody @Valid Measure measure) {
        try {
            measureRepository.save(measure);
        } catch (Exception ex) {
            unprocessablbeEntityError.setErrors(ex.getLocalizedMessage());
            return new ResponseEntity(unprocessablbeEntityError, new HttpHeaders(), unprocessablbeEntityError.getStatus());
        }

        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

}
