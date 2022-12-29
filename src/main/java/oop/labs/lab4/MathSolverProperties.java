package oop.labs.lab4;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class MathSolverProperties
{
    private ServiceProperties service;

    public ServiceProperties getServiceProperties() { return service; }
    public void setServiceProperties(ServiceProperties service) { this.service = service; }

    public static class ServiceProperties
    {
        private MathProperties math;

        public MathProperties getMathProperties() { return math; }
        public void setMathProperties(MathProperties math) { this.math = math; }

        public static class MathProperties
        {
            private ModelProperties model;

            public ModelProperties getModelProperties() { return model; }
            public void setModelProperties(ModelProperties model) { this.model = model; }

            public static class ModelProperties
            {
                private String externMapperCfgPath;

                public String getExternMapperCfgPath() { return externMapperCfgPath; }
                public void setExternMapperCfgPath(String externMapperCfgPath) { this.externMapperCfgPath = externMapperCfgPath; }
            }


            private SolversProperties solvers;

            public SolversProperties getSolversProperties() { return solvers; }
            public void setSolversProperties(SolversProperties solvers) { this.solvers = solvers; }

            public static class SolversProperties
            {
                private String mapperCfgPath;

                public String getMapperCfgPath() { return mapperCfgPath; }
                public void setMapperCfgPath(String mapperCfgPath) { this.mapperCfgPath = mapperCfgPath; }
            }
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
