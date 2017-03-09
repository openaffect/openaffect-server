package io.openaffect.api;

import io.openaffect.api.model.Measure;
import io.openaffect.repositories.MeasureRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-05T13:15:18.047+01:00")

@Controller
public class MeasuresApiController implements MeasuresApi {

    @Autowired
    MeasureRepository measureRepository;

    @Override
    public ResponseEntity<List<Measure>> listMeasures() {
        ResponseEntity<List<Measure>> response = new ResponseEntity<List<Measure>>(measureRepository.findAll(), HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Object> reportMeasure(@ApiParam(value = "measure reported by the sensor", required=true) @RequestBody Measure measure) {
        // do some magic!
        measureRepository.save(measure);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

}
