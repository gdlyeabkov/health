package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
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
        isUnVisible = View.INVISIBLE;
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        exerciseType = extras.getString("type");
        recordExerciseStartedActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
        recordExerciseStartedActivityFooterStoppedBtnsResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordExerciseStartedActivityFooterStartedBtns.setVisibility(isVisible);
                recordExerciseStartedActivityFooterStoppedBtns.setVisibility(isUnVisible);
            }
        });
        recordExerciseStartedActivityFooterStoppedBtnsFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("INSERT INTO \"exercise_records\"(type, datetime, duration) VALUES (\"" + exerciseType + "\", \"" + "22.11.2000 10:00" + "\", \""  + "00:00:00" + "\");");
                Intent intent = new Intent(RecordStartedExerciseActivity.this, RecordExerciseResultsActivity.class);
                intent.putExtra("type", exerciseType);
                RecordStartedExerciseActivity.this.startActivity(intent);
                ContentValues contentValues = new ContentValues();
                contentValues.put("is_exercise_enabled", 0);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
            }
        });
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
            }
        }, 0, 1000);
    }

}
