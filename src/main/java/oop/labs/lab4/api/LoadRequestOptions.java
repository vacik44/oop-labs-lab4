package oop.labs.lab4.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadRequestOptions
{
    @JsonProperty("loadSolution") private final boolean loadSolution;
    public boolean solutionNeeded() { return loadSolution; }


    @JsonCreator public LoadRequestOptions(@JsonProperty("loadSolution") boolean loadSolution)
    {
        this.loadSolution = loadSolution;
    }


    public static LoadRequestOptions REQUEST_RESULT_ONLY = new LoadRequestOptions(false);
    public static LoadRequestOptions REQUEST_RESULT_SOLUTION = new LoadRequestOptions(true);
}