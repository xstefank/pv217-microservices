package io.xstefank.config;

import io.xstefank.Course;
import io.xstefank.Student;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CourseConverter implements Converter<Course> {

    @Override
    public Course convert(String value) {
        String[] split = value.split(";");
        String name = split[0];
        int studentsCount = Integer.parseInt(split[1]);
        List<Student> students = Arrays.stream(split[2].split(","))
            .map(s -> new Student(s, 25))
            .collect(Collectors.toList());

        return new Course(name, studentsCount, students);
    }
}
