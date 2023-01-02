package oop.labs.lab4.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.service.exceptions.CalculationNotFoundException;
import oop.labs.lab4.service.providers.SerializationProvider;
import oop.labs.lab4.service.providers.StorageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

@RestController
@RequestMapping("/calculations/repository")
public class LoadCalculationResults
{
    private final SerializationProvider serializationProvider;
    private final StorageProvider storageProvider;


    LoadCalculationResults(SerializationProvider serializationProvider, StorageProvider storageProvider)
    {
        this.serializationProvider = serializationProvider;
        this.storageProvider = storageProvider;
    }


    @GetMapping("/{calculation-id}")
    public ResponseEntity<String> loadCalculationResults(@PathVariable("calculation-id") BigInteger calculationId, @RequestBody String payload)
    {
        try
        {
            var options = serializationProvider.mapper().readValue(ApiMissingRequestPayloadException.throwIfMissing(payload), LoadRequestOptions.class);
            var results = storageProvider.getValue(calculationId, EvalResults.class);
            var response = serializationProvider.mapper().writeValueAsString(options.solutionNeeded() ? results : results.result());

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (ApiMissingRequestPayloadException | JsonProcessingException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CalculationNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private static class LoadRequestOptions
    {
        @JsonProperty("loadSolution") private boolean loadSolution;
        public boolean solutionNeeded() { return loadSolution; }
    }
}
