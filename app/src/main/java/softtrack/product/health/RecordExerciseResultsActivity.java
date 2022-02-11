package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class RecordExerciseResultsActivity extends AppCompatActivity {

    public ImageButton recordExerciseAcitvityHeaderAsideBackBtn;
    public String exerciseType;
    public TextView recordExerciseAcitvityHeaderAsideLabel;
    public TextView recordExerciseResultsActivityDetailsTrainingTimeLabel;
    public TextView recordExerciseResultsActivityDetailsTotalTimeLabel;
    public TextView recordExerciseResultsActivityTimeStart;
    public TextView recordExerciseResultsActivityTimeEnd;
    public ArrayList<String> monthsLabels;
    public ArrayList<String> weeksLabels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise_results);
        initialize();
    }

    public void initialize() {
        recordExerciseAcitvityHeaderAsideBackBtn = findViewById(R.id.record_exercise_results_activity_header_aside_back_btn);
        recordExerciseAcitvityHeaderAsideLabel = findViewById(R.id.record_exercise_results_activity_header_aside_label);
        recordExerciseResultsActivityDetailsTrainingTimeLabel = findViewById(R.id.record_exercise_results_activity_details_training_time_label);
        recordExerciseResultsActivityDetailsTotalTimeLabel = findViewById(R.id.record_exercise_results_activity_details_total_time_label);
        recordExerciseResultsActivityTimeStart = findViewById(R.id.record_exercise_results_activity_time_start);
        recordExerciseResultsActivityTimeEnd = findViewById(R.id.record_exercise_results_activity_time_end);

        monthsLabels = new ArrayList<String>();
        monthsLabels.add("января");
        monthsLabels.add("февраля");
        monthsLabels.add("марта");
        monthsLabels.add("апреля");
        monthsLabels.add("мая");
        monthsLabels.add("июня");
        monthsLabels.add("июля");
        monthsLabels.add("августа");
        monthsLabels.add("сентября");
        monthsLabels.add("октября");
        monthsLabels.add("ноября");
        monthsLabels.add("декабря");
        weeksLabels = new ArrayList<String>();
        weeksLabels.add("вс");
        weeksLabels.add("пн");
        weeksLabels.add("вт");
        weeksLabels.add("ср");
        weeksLabels.add("чт");
        weeksLabels.add("пт");
        weeksLabels.add("сб");

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        exerciseType = extras.getString("type");
        String exerciseStartTime = extras.getString("startTime");
        String exerciseDuration = extras.getString("duration");
        recordExerciseAcitvityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordExerciseResultsActivity.this, MainActivity.class);
                intent.putExtra("type", exerciseType);
                RecordExerciseResultsActivity.this.startActivity(intent);
            }
        });
        recordExerciseAcitvityHeaderAsideLabel.setText(exerciseType);
        recordExerciseResultsActivityDetailsTrainingTimeLabel.setText(exerciseDuration);
        recordExerciseResultsActivityDetailsTotalTimeLabel.setText(exerciseDuration);
        Calendar calendar = Calendar.getInstance();
        int endTimeHours = calendar.get(Calendar.HOUR_OF_DAY);
        String rawEndTimeHours = String.valueOf(endTimeHours);
        int endTimeMinutes = calendar.get(Calendar.MINUTE);
        String rawEndTimeMinutes = String.valueOf(endTimeMinutes);
        String exerciseEndTime = rawEndTimeHours + ":" + rawEndTimeMinutes;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String rawDayOfMonth = String.valueOf(dayOfMonth);
        int monthLabel = calendar.get(Calendar.MONTH);
        String rawMonthLabel = monthsLabels.get(monthLabel);
        int weekLabel = calendar.get(Calendar.DAY_OF_WEEK);
        String rawWeekLabel = weeksLabels.get(weekLabel);
        String exerciseDateTime = rawWeekLabel + ", " + rawDayOfMonth + " " + rawMonthLabel;
        recordExerciseResultsActivityTimeStart.setText(exerciseDateTime);
        String recordExerciseResultsActivityTimeEndMsg = exerciseStartTime + " " + exerciseEndTime;
        recordExerciseResultsActivityTimeEnd.setText(recordExerciseResultsActivityTimeEndMsg);

    }

}
