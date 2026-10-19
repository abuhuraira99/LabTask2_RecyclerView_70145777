package com.example.uolcoursecatalogue;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Shows the details of the one course that was tapped in the list.
 */
public class CourseDetailActivity extends AppCompatActivity {

    /** Keys used to pass the tapped course's data from MainActivity. */
    public static final String EXTRA_CODE = "com.example.uolcoursecatalogue.EXTRA_CODE";
    public static final String EXTRA_TITLE = "com.example.uolcoursecatalogue.EXTRA_TITLE";
    public static final String EXTRA_INSTRUCTOR = "com.example.uolcoursecatalogue.EXTRA_INSTRUCTOR";
    public static final String EXTRA_EMAIL = "com.example.uolcoursecatalogue.EXTRA_EMAIL";
    public static final String EXTRA_DESCRIPTION = "com.example.uolcoursecatalogue.EXTRA_DESCRIPTION";
    public static final String EXTRA_CREDIT_HOURS = "com.example.uolcoursecatalogue.EXTRA_CREDIT_HOURS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this,
                SystemBarStyle.dark(Color.TRANSPARENT),
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT));
        setContentView(R.layout.activity_course_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
        applyInsets(toolbar, findViewById(R.id.content));

        // Read the values that MainActivity put into the Intent
        Intent intent = getIntent();
        String code = intent.getStringExtra(EXTRA_CODE);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String instructor = intent.getStringExtra(EXTRA_INSTRUCTOR);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        int creditHours = intent.getIntExtra(EXTRA_CREDIT_HOURS, 0);

        // The screen title is the course code
        setTitle(code);

        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvInstructor = findViewById(R.id.tvInstructor);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvDetailCreditHours = findViewById(R.id.tvDetailCreditHours);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnEmail = findViewById(R.id.btnEmail);

        tvDetailTitle.setText(title);
        tvInstructor.setText(instructor);
        tvEmail.setText(email);
        tvDetailCreditHours.setText(getResources()
                .getQuantityString(R.plurals.credit_hours_long, creditHours, creditHours));
        tvDescription.setText(description);

        btnEmail.setOnClickListener(v -> emailInstructor(email));
    }

    /**
     * Opens an email app addressed only to this course's instructor. ACTION_SENDTO with a
     * mailto: Uri is answered by email apps only, not by every app that can share text.
     */
    private void emailInstructor(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.toast_no_email_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void applyInsets(View toolbar, View content) {
        int toolbarTop = toolbar.getPaddingTop();
        int contentBottom = content.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            toolbar.setPadding(toolbar.getPaddingLeft(), toolbarTop + bars.top,
                    toolbar.getPaddingRight(), toolbar.getPaddingBottom());
            content.setPadding(content.getPaddingLeft(), content.getPaddingTop(),
                    content.getPaddingRight(), contentBottom + bars.bottom);
            v.setPadding(bars.left, 0, bars.right, 0);
            return insets;
        });
    }
}
