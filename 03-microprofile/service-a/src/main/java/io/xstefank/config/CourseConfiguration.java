package io.xstefank.config;

import io.quarkus.arc.config.ConfigProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@ConfigProperties(prefix = "course")
public class CourseConfiguration {

    public String name;
    @Min(20)
    public int studentCount;
}
