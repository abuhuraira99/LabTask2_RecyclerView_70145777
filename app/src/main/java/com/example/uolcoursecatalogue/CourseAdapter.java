package com.example.uolcoursecatalogue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter that writes the data of each Course into its card (item_course.xml).
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    /** Tells the activity which card was tapped. */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<Course> courses;
    private final OnItemClickListener listener;

    public CourseAdapter(List<Course> courses, OnItemClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate one card; RecyclerView reuses it later for other courses
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.tvCode.setText(course.getCode());
        holder.tvTitle.setText(course.getTitle());
        holder.tvCreditHours.setText(holder.itemView.getContext()
                .getString(R.string.credit_hours_short, course.getCreditHours()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    /**
     * Keeps the three TextViews of one card, so findViewById() runs only once per card
     * and not every time the card is reused for another course.
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        final TextView tvCode;
        final TextView tvTitle;
        final TextView tvCreditHours;

        public CourseViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCreditHours = itemView.findViewById(R.id.tvCreditHours);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                // NO_POSITION is returned while the list is being updated
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}
