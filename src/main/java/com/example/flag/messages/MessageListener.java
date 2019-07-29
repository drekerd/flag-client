package com.example.flag.messages;

import com.example.flag.courses.Course;
import com.example.flag.courses.CoursesService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class MessageListener {

    private static final Logger LOGGER = Logger.getLogger("MessageListener");

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    CoursesService service;

    //MessageTest is just for debugging
    @Autowired
    MessageTest messageTest;

    @JmsListener(destination = "flag.queue")
    public void consume(String messages) {

        LOGGER.info("Consumes : started with message : " + messages);

        messageTest.setMessage(messages);

        List<Course> coursesFromJson = new GsonBuilder()
                .create()
                .fromJson(messages, new TypeToken<List<Course>>() {
                }.getType());

        service.setCourses(coursesFromJson);

        LOGGER.info("Messages consume ended : " + service.getCourses().toString());
    }
}
