package softtrack.product.health;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
    int LOCATION_REFRESH_TIME = 2; // 15 seconds to update
    int LOCATION_REFRESH_DISTANCE = 1; // 500 meters to update
    public LocationListener mLocationListener;
    public TextView recordExerciseStartedActivityBodyCalloriesTitle;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise_started);
        initialize();
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                //your code here
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String rawLatitude = Double.toString(latitude);
                String rawLongitude = Double.toString(longitude);
                Log.d("debug", "Latitude: " + rawLatitude + ", Longitude: " + rawLongitude);
                CharSequence rawRecordExerciseStartedActivityBodyCalloriesTitleContent = recordExerciseStartedActivityBodyCalloriesTitle.getText();
                String recordExerciseStartedActivityBodyCalloriesTitleContent = rawRecordExerciseStartedActivityBodyCalloriesTitleContent.toString();
                int parsedRecordExerciseStartedActivityBodyCalloriesTitleContent = Integer.valueOf(recordExerciseStartedActivityBodyCalloriesTitleContent);
                parsedRecordExerciseStartedActivityBodyCalloriesTitleContent += 1;
                String updatedRecordExerciseStartedActivityBodyCalloriesTitleContent = String.valueOf(parsedRecordExerciseStartedActivityBodyCalloriesTitleContent);
                recordExerciseStartedActivityBodyCalloriesTitle.setText(updatedRecordExerciseStartedActivityBodyCalloriesTitleContent);
            }
        };
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.LOCATION_HARDWARE, Manifest.permission.INSTALL_LOCATION_PROVIDER, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CONTROL_LOCATION_UPDATES, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_MEDIA_LOCATION, }, 1);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, mLocationListener);
        }
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
        recordExerciseStartedActivityBodyCalloriesTitle = findViewById(R.id.record_exercise_started_activity_body_callories_title);
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
                addPossibleBigDurationAward(duration, dateTime);
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

    public void addPossibleBigDurationAward(String duration, String dateTime) {
        Cursor exerciseRecordsCursor = db.rawQuery("Select * from exercise_records", null);
        exerciseRecordsCursor.moveToFirst();
        long countExerciseRecords = 0;
        countExerciseRecords = DatabaseUtils.queryNumEntries(db, "exercise_records");
        String[] rawDurationParts = duration.split(":");
        String rawDurationHours = rawDurationParts[0];
        String rawDurationMinutes = rawDurationParts[1];
        String rawDurationSeconds = rawDurationParts[2];
        int durationHours = Integer.valueOf(rawDurationHours);
        int durationMinutes = Integer.valueOf(rawDurationMinutes);
        int durationSeconds = Integer.valueOf(rawDurationSeconds);
        int durationMinutesAsSeconds = durationMinutes * 60;
        int durationHoursAsSeconds = durationHours * 60 * 60;
        int totalDurationInSeconds = durationSeconds + durationMinutesAsSeconds + durationHoursAsSeconds;
        int bigDurationCounter = 0;
        for (long exerciseRecordsCursorIndex = 0; exerciseRecordsCursorIndex < countExerciseRecords; exerciseRecordsCursorIndex++) {
            String rawExerciseDuration = exerciseRecordsCursor.getString(3);
            String[] rawExerciseDurationParts = rawExerciseDuration.split(":");
            String rawExerciseDurationHours = rawExerciseDurationParts[0];
            String rawExerciseDurationMinutes = rawExerciseDurationParts[1];
            String rawExerciseDurationSeconds = rawExerciseDurationParts[2];
            int exerciseDurationHours = Integer.valueOf(rawExerciseDurationHours);
            int exerciseDurationMinutes = Integer.valueOf(rawExerciseDurationMinutes);
            int exerciseDurationSeconds = Integer.valueOf(rawExerciseDurationSeconds);
            int exerciseDurationMinutesAsSeconds = exerciseDurationMinutes * 60;
            int exerciseDurationHoursAsSeconds = exerciseDurationHours * 60 * 60;
            int totalExerciseDurationInSeconds = exerciseDurationSeconds + exerciseDurationMinutesAsSeconds + exerciseDurationHoursAsSeconds;
            boolean isGtDuration = totalDurationInSeconds > totalExerciseDurationInSeconds;
            if (isGtDuration) {
                bigDurationCounter++;
            }
            exerciseRecordsCursor.moveToNext();
        }
        boolean isGetBigDurationAward = bigDurationCounter == countExerciseRecords;
        if (isGetBigDurationAward) {
            String awardName = "Самая большая длительность";
            String awardDesc = duration + " " + dateTime;
            db.execSQL("INSERT INTO \"awards\"(name, description, type) VALUES (\"" + awardName + "\", \"" + awardDesc + "\", \"" + exerciseType + "\");");
        }
    }

}
