package oop.labs.lab4.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import oop.labs.lab4.service.CalculationsProvider;
import oop.labs.lab4.service.math.MathModelExternMapper;
import oop.labs.lab4.service.math.MathModelRecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculations")
public class SubmitCalculation
{
    Logger log = LoggerFactory.getLogger(SubmitCalculation.class);

    private final JsonMapper jsonMapper = new JsonMapper();
    private final MathModelExternMapper modelMapper;

    private final CalculationsProvider submitter;


    SubmitCalculation(CalculationsProvider submitter, MathModelExternMapper modelMapper)
    {
        this.submitter = submitter;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/{solver-id}")
    public ResponseEntity<String> submitCalculation(@PathVariable("solver-id") String solverId, String payload)
    {
        log.info("New calculation submit request for solver-id '%s'".formatted(solverId));

        try
        {
            var mathData = modelMapper.parseJsonEntity(jsonMapper.readTree(payload), "condition");
            return ResponseEntity.status(HttpStatus.OK).body(submitter.submitCalculation(solverId, mathData));
        }
        catch (JsonProcessingException | MathModelRecognitionException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
