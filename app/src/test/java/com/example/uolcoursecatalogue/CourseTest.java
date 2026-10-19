package com.example.uolcoursecatalogue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Checks that the constructor of Course stores every field and that each getter returns it.
 */
public class CourseTest {

    @Test
    public void constructor_setsEveryField() {
        Course course = new Course("CS02310", "Mobile Application Development",
                "Sir Arslan", "arslan@uol.edu.pk", "Android apps in Java.", 3);

        assertEquals("CS02310", course.getCode());
        assertEquals("Mobile Application Development", course.getTitle());
        assertEquals("Sir Arslan", course.getInstructor());
        assertEquals("arslan@uol.edu.pk", course.getEmail());
        assertEquals("Android apps in Java.", course.getDescription());
        assertEquals(3, course.getCreditHours());
    }
}
