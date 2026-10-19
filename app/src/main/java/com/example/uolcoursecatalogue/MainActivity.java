package com.example.uolcoursecatalogue;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private final ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Light status bar icons, because the toolbar behind them is dark green
        EdgeToEdge.enable(this,
                SystemBarStyle.dark(Color.TRANSPARENT),
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT));
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle(R.string.list_subtitle);

        RecyclerView rvCourses = findViewById(R.id.rvCourses);
        applyInsets(toolbar, rvCourses);

        loadCourses();

        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(new CourseAdapter(courses, this));
    }

    /** Called by CourseAdapter with the position of the card that was tapped. */
    @Override
    public void onItemClick(int position) {
        Course course = courses.get(position);

        // Explicit Intent: names CourseDetailActivity and carries only this course's data
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra(CourseDetailActivity.EXTRA_CODE, course.getCode());
        intent.putExtra(CourseDetailActivity.EXTRA_TITLE, course.getTitle());
        intent.putExtra(CourseDetailActivity.EXTRA_INSTRUCTOR, course.getInstructor());
        intent.putExtra(CourseDetailActivity.EXTRA_EMAIL, course.getEmail());
        intent.putExtra(CourseDetailActivity.EXTRA_DESCRIPTION, course.getDescription());
        intent.putExtra(CourseDetailActivity.EXTRA_CREDIT_HOURS, course.getCreditHours());
        startActivity(intent);
    }

    /** Courses of the BS Software Engineering programme shown in the list. */
    private void loadCourses() {
        courses.add(new Course("CS02310", "Mobile Application Development",
                "Sir Arslan", "arslan@uol.edu.pk",
                "Design and development of native Android applications in Java using Android Studio. "
                        + "Covers activities, layouts, intents, RecyclerView, data storage and publishing an app.",
                3));
        courses.add(new Course("SE03401", "Software Construction and Development",
                "Mr. Usman Tariq", "usman.tariq@uol.edu.pk",
                "Turning a software design into working, readable code. Covers coding standards, "
                        + "defensive programming, refactoring, unit testing and version control with Git.",
                3));
        courses.add(new Course("SE03402", "Software Quality Engineering",
                "Dr. Ayesha Khan", "ayesha.khan@uol.edu.pk",
                "Quality models, reviews and inspections, test planning, black-box and white-box testing, "
                        + "test automation and software quality metrics.",
                3));
        courses.add(new Course("SE04101", "Software Project Management",
                "Mr. Bilal Ahmed", "bilal.ahmed@uol.edu.pk",
                "Planning, estimating and tracking software projects. Covers scope, scheduling, cost, "
                        + "risk management, team organisation and agile methods such as Scrum.",
                3));
        courses.add(new Course("CS02308", "Web Engineering",
                "Ms. Fatima Noor", "fatima.noor@uol.edu.pk",
                "Building web applications with HTML, CSS and JavaScript on the client side and a "
                        + "server-side framework with a database, including REST APIs and web security basics.",
                4));
        courses.add(new Course("CS03201", "Artificial Intelligence",
                "Dr. Hamza Ali", "hamza.ali@uol.edu.pk",
                "Intelligent agents, search algorithms, knowledge representation, reasoning under "
                        + "uncertainty and an introduction to machine learning, with lab work in Python.",
                4));
        courses.add(new Course("SE02203", "Human Computer Interaction",
                "Ms. Sana Javed", "sana.javed@uol.edu.pk",
                "User-centred design, usability principles, prototyping and user evaluation of "
                        + "interfaces for desktop, web and mobile applications.",
                3));
        courses.add(new Course("CS02105", "Information Security",
                "Mr. Kashif Mehmood", "kashif.mehmood@uol.edu.pk",
                "Security goals, cryptography, authentication, access control, network and web "
                        + "application security, and secure software development practices.",
                3));
        courses.add(new Course("SE04102", "Software Re-Engineering",
                "Dr. Mariam Saeed", "mariam.saeed@uol.edu.pk",
                "Maintenance and evolution of legacy systems. Covers reverse engineering, program "
                        + "comprehension, restructuring, data re-engineering and migration strategies.",
                3));
        courses.add(new Course("SE04190", "Final Year Project I",
                "Ms. Hira Aslam", "hira.aslam@uol.edu.pk",
                "First part of the capstone project. Students form a group, propose a real problem, "
                        + "gather requirements and present the design of their software system.",
                3));
        courses.add(new Course("HU04101", "Professional Practices",
                "Mr. Ali Raza", "ali.raza@uol.edu.pk",
                "Ethics and professional responsibility in computing, intellectual property, "
                        + "software contracts, cyber laws of Pakistan and workplace communication.",
                2));
    }

    /** The toolbar colour runs behind the status bar, the list ends above the navigation bar. */
    private void applyInsets(View toolbar, View list) {
        int toolbarTop = toolbar.getPaddingTop();
        int listBottom = list.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            toolbar.setPadding(toolbar.getPaddingLeft(), toolbarTop + bars.top,
                    toolbar.getPaddingRight(), toolbar.getPaddingBottom());
            list.setPadding(list.getPaddingLeft(), list.getPaddingTop(),
                    list.getPaddingRight(), listBottom + bars.bottom);
            v.setPadding(bars.left, 0, bars.right, 0);
            return insets;
        });
    }
}
