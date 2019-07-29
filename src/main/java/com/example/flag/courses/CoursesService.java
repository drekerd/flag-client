package com.example.flag.courses;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CoursesService {

    List<Course> courses;
}
