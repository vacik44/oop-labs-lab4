package oop.labs.lab4;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("unused")
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
                private ExternProperties extern;

                public ExternProperties getExternProperties() { return extern; }
                public void setExternProperties(ExternProperties extern) { this.extern = extern; }

                public static class ExternProperties
                {
                    private String mapFilePath;

                    public String getMapFilePath() { return mapFilePath; }
                    public void setMapFilePath(String mapFilePath) { this.mapFilePath = mapFilePath; }
                }
            }


            private EvaluationProperties eval;

            public EvaluationProperties getEvaluationProperties() { return eval; }
            public void setEvaluationProperties(EvaluationProperties eval) { this.eval = eval; }

            public static class EvaluationProperties
            {
                private SolversProperties solvers;

                public SolversProperties getSolversProperties() { return solvers; }
                public void setSolversProperties(SolversProperties solvers) { this.solvers = solvers; }

                public static class SolversProperties
                {
                    private String mapFilePath;

                    public String getMapFilePath() { return mapFilePath; }
                    public void setMapFilePath(String mapFilePath) { this.mapFilePath = mapFilePath; }
                }
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
