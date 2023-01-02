package oop.labs.lab4.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.service.exceptions.EvaluationFailedException;
import oop.labs.lab4.service.exceptions.SolverNotFoundException;
import oop.labs.lab4.service.providers.EvaluationProvider;
import oop.labs.lab4.service.providers.SerializationProvider;
import oop.labs.lab4.service.providers.StorageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/calculations")
public class SubmitCalculation
{
    private final SerializationProvider serializationProvider;
    private final EvaluationProvider evaluationProvider;
    private final StorageProvider storageProvider;


    SubmitCalculation(SerializationProvider serializationProvider, EvaluationProvider evaluationProvider, StorageProvider storageProvider)
    {
        this.serializationProvider = serializationProvider;
        this.evaluationProvider = evaluationProvider;
        this.storageProvider = storageProvider;
    }


    @PostMapping("/{solver-id}")
    public ResponseEntity<String> submitCalculation(@PathVariable("solver-id") String solverId, @RequestBody String payload)
    {
        try
        {
            var condition = serializationProvider.mapper().readValue(ApiMissingRequestPayloadException.throwIfMissing(payload), EvalCondition.class);
            var results = evaluationProvider.evaluate(solverId, condition);
            var id = storageProvider.storeValue(results);

            return ResponseEntity.status(HttpStatus.OK).body(id.toString());
        }
        catch (ApiException | JsonProcessingException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (SolverNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (EvaluationFailedException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
