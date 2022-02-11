package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class RecordStartedExerciseActivity extends AppCompatActivity {

    public TextView recordExerciseStartedActivityHeaderAsideLabel;
    public ImageButton recordExerciseStartedActivityHeaderAsideBackBtn;
    public String exerciseType;
    public Button recordExerciseStartedActivityFooterStoppedBtnsFinishBtn;
    public Button recordExerciseStartedActivityFooterStoppedBtnsResumeBtn;
    public Button recordExerciseStartedActivityFooterStartedBtnsPauseBtn;
    public ImageButton recordExerciseStartedActivityFooterStartedBtnsLockBtn;
    public LinearLayout recordExerciseStartedActivityFooterStartedBtns;
    public LinearLayout recordExerciseStartedActivityFooterStoppedBtns;
    public int isVisible;
    public int isUnVisible;
    public Timer timer;
    public int initialSeconds = 0;
    public int countSecondsInMinute = 60;
    public int countMinutesInHour = 60;
    public int initialMinutes = 0;
    public String oneCharPrefix = "0";
    public TextView recordExerciseStartedActivityBodyDurationTitle;
    public String timePartsSeparator = ":";
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise_started);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        recordExerciseStartedActivityHeaderAsideLabel = findViewById(R.id.record_exercise_started_activity_header_aside_label);
        recordExerciseStartedActivityHeaderAsideBackBtn = findViewById(R.id.record_exercise_started_activity_header_aside_back_btn);
        recordExerciseStartedActivityFooterStartedBtnsLockBtn = findViewById(R.id.record_exercise_started_activity_footer_started_btns_lock_btn);
        recordExerciseStartedActivityFooterStartedBtnsPauseBtn = findViewById(R.id.record_exercise_started_activity_footer_started_btns_pause_btn);
        recordExerciseStartedActivityFooterStoppedBtnsResumeBtn = findViewById(R.id.record_exercise_started_activity_footer_stopped_btns_resume_btn);
        recordExerciseStartedActivityFooterStoppedBtnsFinishBtn = findViewById(R.id.record_exercise_started_activity_footer_stopped_btns_finish_btn);
        recordExerciseStartedActivityFooterStartedBtns = findViewById(R.id.record_exercise_started_activity_footer_started_btns);
        recordExerciseStartedActivityFooterStoppedBtns = findViewById(R.id.record_exercise_started_activity_footer_stopped_btns);
        recordExerciseStartedActivityBodyDurationTitle = findViewById(R.id.record_exercise_started_activity_body_duration_title);
        isVisible = View.VISIBLE;
        isUnVisible = View.GONE;
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        exerciseType = extras.getString("type");
        recordExerciseStartedActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(RecordStartedExerciseActivity.this, MainActivity.class);
                RecordStartedExerciseActivity.this.startActivity(intent);
            }
        });
        recordExerciseStartedActivityHeaderAsideLabel.setText(exerciseType);
        recordExerciseStartedActivityFooterStartedBtnsPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordExerciseStartedActivityFooterStartedBtns.setVisibility(isUnVisible);
                recordExerciseStartedActivityFooterStoppedBtns.setVisibility(isVisible);
                stopTimer();
            }
        });
        recordExerciseStartedActivityFooterStoppedBtnsResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordExerciseStartedActivityFooterStartedBtns.setVisibility(isVisible);
                recordExerciseStartedActivityFooterStoppedBtns.setVisibility(isUnVisible);
                startTimer();
            }
        });
        recordExerciseStartedActivityFooterStoppedBtnsFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String duration = "00:00:00";
                CharSequence rawDuration = recordExerciseStartedActivityBodyDurationTitle.getText();
                duration = rawDuration.toString();
                Intent intent = new Intent(RecordStartedExerciseActivity.this, RecordExerciseResultsActivity.class);
                intent.putExtra("type", exerciseType);
                Cursor foodRecordsCursor = db.rawQuery("Select * from indicators", null);
                foodRecordsCursor.moveToFirst();
                String startTime = foodRecordsCursor.getString(5);
                intent.putExtra("startTime", startTime);
                CharSequence rawCachedDuration = recordExerciseStartedActivityBodyDurationTitle.getText();
                String cachedDuration = rawCachedDuration.toString();
                intent.putExtra("duration", cachedDuration);
                RecordStartedExerciseActivity.this.startActivity(intent);
                ContentValues contentValues = new ContentValues();
                contentValues.put("is_exercise_enabled", 0);
                contentValues.put("exercise_duration", "00:00:00");
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String rawDay = String.valueOf(day);
                int month = calendar.get(Calendar.MONTH);
                String rawMonth = String.valueOf(month);
                int year = calendar.get(Calendar.YEAR);
                String rawYear = String.valueOf(year);
                String date = rawDay + "." + rawMonth + "." + rawYear;
                String dateTime = date + " " + startTime;
                db.execSQL("INSERT INTO \"exercise_records\"(type, datetime, duration) VALUES (\"" + exerciseType + "\", \"" + dateTime + "\", \""  + duration + "\");");
            }
        });
        Cursor foodRecordsCursor = db.rawQuery("Select * from indicators", null);
        foodRecordsCursor.moveToFirst();
        String rawDuration = foodRecordsCursor.getString(7);
        recordExerciseStartedActivityBodyDurationTitle.setText(rawDuration);
        startTimer();
    }

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            CharSequence rawSecondsText = recordExerciseStartedActivityBodyDurationTitle.getText();
            String secondsText = rawSecondsText.toString();
            String[] timeParts = secondsText.split(timePartsSeparator);
            String rawHours = timeParts[0];
            String rawMinutes = timeParts[1];
            String rawSeconds = timeParts[2];
            int hours = Integer.valueOf(rawHours);
            int minutes = Integer.valueOf(rawMinutes);
            int seconds = Integer.valueOf(rawSeconds);
            seconds++;
            boolean isToggleSecond = seconds == countSecondsInMinute;
            if (isToggleSecond) {
                seconds = initialSeconds;
                minutes++;
                boolean isToggleHour = minutes == countMinutesInHour;
                if (isToggleHour) {
                    minutes = initialMinutes;
                    hours++;
                }
            }
            String updatedHoursText = String.valueOf(hours);
            int countHoursChars = updatedHoursText.length();
            boolean isAddHoursPrefix = countHoursChars == 1;
            if (isAddHoursPrefix) {
                updatedHoursText = oneCharPrefix + updatedHoursText;
            }
            String updatedMinutesText = String.valueOf(minutes);
            int countMinutesChars = updatedMinutesText.length();
            boolean isAddMinutesPrefix = countMinutesChars == 1;
            if (isAddMinutesPrefix) {
                updatedMinutesText = oneCharPrefix + updatedMinutesText;
            }
            String updatedSecondsText = String.valueOf(seconds);
            int countSecondsChars = updatedSecondsText.length();
            boolean isAddSecondsPrefix = countSecondsChars == 1;
            if (isAddSecondsPrefix) {
                updatedSecondsText = oneCharPrefix + updatedSecondsText;
            }
            String currentTime = updatedHoursText + ":" + updatedMinutesText + ":" + updatedSecondsText;
            recordExerciseStartedActivityBodyDurationTitle.setText(currentTime);
            ContentValues contentValues = new ContentValues();
            contentValues.put("exercise_duration", currentTime);
            db.update("indicators", contentValues, "_id = 1", new String[] {  });
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        timer.purge();
        timer.cancel();
    }

}
