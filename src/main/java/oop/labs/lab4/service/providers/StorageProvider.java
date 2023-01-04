package oop.labs.lab4.service.providers;

import oop.labs.lab4.data.repos.RecordsRepository;
import oop.labs.lab4.data.repos.RepositoryRecordNotFoundException;
import oop.labs.lab4.service.exceptions.CalculationNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.math.BigInteger;

@Service
@SuppressWarnings("unused")
public class StorageProvider
{
    private final RecordsRepository primaryRepository;


    public StorageProvider(@Qualifier("PrimaryCalculationsRepository") RecordsRepository primaryRepository)
    {
        this.primaryRepository = primaryRepository;
    }


    public void clearValues()
    {
        primaryRepository.clearAllRecords();
    }

    public void dropValue(BigInteger id)
    {
        primaryRepository.deleteRecord(id);
    }

    public BigInteger storeValue(Object value)
    {
        return primaryRepository.makeRecord(value);
    }

    public <TValue> TValue getValue(BigInteger id, Class<TValue> type)
    {
        try { return primaryRepository.getRecord(id, type); }
        catch (RepositoryRecordNotFoundException e) { throw CalculationNotFoundException.forId(id); }
    }
}
