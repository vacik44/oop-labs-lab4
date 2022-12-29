package oop.labs.lab4.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import oop.labs.lab4.service.CalculationsProvider;
import oop.labs.lab4.service.math.MathExternModelMapper;
import oop.labs.lab4.service.math.exceptions.MathExternModelRecognitionException;
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
    private final JsonMapper jsonMapper = new JsonMapper();
    private final MathExternModelMapper modelMapper;

    private final CalculationsProvider submitter;


    SubmitCalculation(CalculationsProvider submitter, MathExternModelMapper modelMapper)
    {
        this.submitter = submitter;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/{solver-id}")
    public ResponseEntity<String> submitCalculation(@PathVariable("solver-id") String solverId, String payload)
    {
        try
        {
            if (payload == null) throw new NullPointerException("No condition received");
            var mathData = modelMapper.json2Entity(jsonMapper.readTree(payload), "condition");
            return ResponseEntity.status(HttpStatus.OK).body(submitter.submitCalculation(solverId, mathData).toString());
        }
        catch (NullPointerException | JsonProcessingException | MathExternModelRecognitionException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
