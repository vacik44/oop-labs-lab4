package oop.labs.lab4;

import oop.labs.lab4.data.repos.CalculationsRepositoryProperties;
import oop.labs.lab4.data.configs.MathModelMapperConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CalculationsRepositoryProperties.class, MathModelMapperConfigProperties.class})
public class MathSolverConfig {}
