package com.swagbear.chalk_mobile.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.entities.Exercise;

import java.util.List;

/**
 * This adapter is used to display the list of exercises in the workout guide
 *
 * As the Guide iterates through the exercise list the adapter will bold the
 * current exercise
 *
 * Only the exercise name is displayed
 */
public class DisplayExerciseForGuideAdapter extends ArrayAdapter<Exercise> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    private int selectedExercisePosition;

    //This class is the outline of views used to display data
    static class ViewHolder {
        TextView exerciseName;
    }

    public DisplayExerciseForGuideAdapter(@NonNull Context context, int resource, @NonNull List<Exercise> exercises) {
        super(context,resource,exercises);
        mResource = resource;
        mContext = context;
        selectedExercisePosition = 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String selectedExerciseName = getItem(position).getName();
        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.exerciseName = convertView.findViewById(R.id.txtWorkoutGuideExercise);

            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        //This will optimize the scrolling of add exercise drop down list, so that when
        // it has a large amount of items there will be no lag in the UI
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.exerciseName.setText(selectedExerciseName);

        holder.exerciseName.setTypeface(null, Typeface.NORMAL);

        if (position == selectedExercisePosition) {
            holder.exerciseName.setTypeface(null, Typeface.BOLD);
            holder.exerciseName.setText("> " + holder.exerciseName.getText().toString());
        }

        return convertView;
    }

    public void setSelectedExerciseIndex(int index) {
        this.selectedExercisePosition = index;
    }
}