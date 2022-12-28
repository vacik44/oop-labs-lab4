package oop.labs.lab4.data.repos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CalculationsRepository
{
    CalculationsRepository(@Value("${app.data.repos.repository-path}") String path)
    {

    }
}
