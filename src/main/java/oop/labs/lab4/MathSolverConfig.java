package oop.labs.lab4;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MathSolverProperties.class})
public class MathSolverConfig {}
