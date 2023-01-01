package oop.labs.lab4.api;

import com.fasterxml.jackson.databind.json.JsonMapper;
import oop.labs.lab4.math.MathematicalException;
import oop.labs.lab4.service.providers.CalculationsProvider;
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
    private final CalculationsProvider submitter;


    SubmitCalculation(CalculationsProvider submitter)
    {
        this.submitter = submitter;
    }


    @PostMapping("/{solver-id}")
    public ResponseEntity<String> submitCalculation(@PathVariable("solver-id") String solverId, String payload)
    {
        try
        {
            if (payload == null) throw new NullPointerException("No condition received");
            //var mathData = modelMapper.json2Entity(jsonMapper.readTree(payload), "condition");
            //return ResponseEntity.status(HttpStatus.OK).body(submitter.submitCalculation(solverId, mathData).toString());
            return null;
        }
        catch (NullPointerException e)//| JsonProcessingException | MappingException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (MathematicalException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
