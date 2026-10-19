package com.example.uolcoursecatalogue;

/**
 * Model class that holds the data of one course of the BS-SE programme.
 * All fields are private and are set once through the constructor.
 */
public class Course {

    private final String code;
    private final String title;
    private final String instructor;
    private final String email;
    private final String description;
    private final int creditHours;

    public Course(String code, String title, String instructor, String email,
                  String description, int creditHours) {
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.email = email;
        this.description = description;
        this.creditHours = creditHours;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public int getCreditHours() {
        return creditHours;
    }
}
