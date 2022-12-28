package oop.labs.lab4.data.repos;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.data.repos")
public class CalculationsRepositoryProperties
{
    private String repositoryPath;

    public String getRepositoryPath() { return repositoryPath; }
    public void setRepositoryPath(String repositoryPath) { this.repositoryPath = repositoryPath; }
}
