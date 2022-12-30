package oop.labs.lab4;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("unused")
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties
{
    private ServiceProperties service;

    public ServiceProperties getServiceProperties() { return service; }
    public void setServiceProperties(ServiceProperties service) { this.service = service; }

    public static class ServiceProperties
    {
        private MappingProperties mapping;

        public MappingProperties getMappingProperties() { return mapping; }
        public void setMappingProperties(MappingProperties mapping) { this.mapping = mapping; }


        public static class MappingProperties
        {
            private String mathObjMapFilePath;

            public String getMathObjMapFilePath() { return mathObjMapFilePath; }
            public void setMathObjMapFilePath(String mathObjMapFilePath) { this.mathObjMapFilePath = mathObjMapFilePath; }


            private String mathSolversMapFilePath;

            public String getMathSolversMapFilePath() { return mathSolversMapFilePath; }
            public void setMathSolversMapFilePath(String mathSolversMapFilePath) { this.mathSolversMapFilePath = mathSolversMapFilePath; }
        }
    }


    private DataProperties data;

    public DataProperties getDataProperties() { return data; }
    public void setDataProperties(DataProperties data) { this.data = data; }

    public static class DataProperties
    {
        private RepositoryProperties repos;

        public RepositoryProperties getReposProperties() { return repos; }
        public void setReposProperties(RepositoryProperties repos) { this.repos = repos; }

        public static class RepositoryProperties
        {
            private String repositoryPath;

            public String getRepositoryPath() { return repositoryPath; }
            public void setRepositoryPath(String repositoryPath) { this.repositoryPath = repositoryPath; }
        }
    }
}
