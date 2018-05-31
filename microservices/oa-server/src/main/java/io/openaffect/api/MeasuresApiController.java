package io.openaffect.api;

import io.openaffect.api.model.Measure;
import io.openaffect.repositories.MeasureRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public ResponseEntity<List<Measure>> listMeasures(@ApiParam(value = "the trigger href field to look for") @RequestParam(value = "triggerHref", required = false) String triggerHref) {
        if (triggerHref == null) {
            ResponseEntity<List<Measure>> response = new ResponseEntity<List<Measure>>(measureRepository.findAll(), HttpStatus.OK);
            return response;
        }

        List<Measure> measures = measureRepository.listByTriggerHref(triggerHref);

        if (measures.isEmpty()){
            notFoundError.setErrors(new StringBuilder().append("Measures with trigger href '").append(triggerHref).append("' cannot be found.").toString());
            return new ResponseEntity(notFoundError, new HttpHeaders(), notFoundError.getStatus());
        }

        ResponseEntity<List<Measure>> response = new ResponseEntity<List<Measure>>(measures, HttpStatus.OK);
        return response;
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
