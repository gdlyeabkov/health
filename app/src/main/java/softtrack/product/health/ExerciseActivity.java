package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {

    public ImageButton exerciseActivityHeaderAsideBackBtn;
    public LinearLayout exerciseActivityNotFound;
    public LinearLayout exerciseActivityRecords;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        exerciseActivityHeaderAsideBackBtn = findViewById(R.id.exercise_activity_header_aside_back_btn);
        exerciseActivityNotFound = findViewById(R.id.exercise_activity_not_found);
        exerciseActivityRecords = findViewById(R.id.exercise_activity_records);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        exerciseActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseActivity.this, MainActivity.class);
                ExerciseActivity.this.startActivity(intent);
            }
        });
        long countRecords = DatabaseUtils.queryNumEntries(db, "exercise_records");
        Cursor exercisesCursor = db.rawQuery("Select * from exercise_records", null);
        boolean isRecordsFound = countRecords >= 1;
        if (isRecordsFound) {
            exerciseActivityNotFound.setVisibility(View.GONE);
            exercisesCursor.moveToFirst();
            for (int i = 0; i < countRecords; i++) {
                String type = "Ходьба";
                String dateTime = "22.11.2000 00:00";
                type = exercisesCursor.getString(1);
                dateTime = exercisesCursor.getString(2);
                String[] dateAndTime = dateTime.split(" ");
                String time = dateAndTime[1];
                String duration = "00:00:00";
                duration = exercisesCursor.getString(3);
                LinearLayout exerciseActivityRecord = new LinearLayout(ExerciseActivity.this);
                LinearLayout exerciseActivityRecordHeader = new LinearLayout(ExerciseActivity.this);
                exerciseActivityRecord.setOrientation(LinearLayout.HORIZONTAL);
                exerciseActivityRecord.addView(exerciseActivityRecordHeader);
                ImageView exerciseActivityRecordHeaderIcon = new ImageView(ExerciseActivity.this);
                int logo = 0;
                if (type == "Ходьба") {
                    exerciseActivityRecordHeaderIcon.setImageResource(R.drawable.walk_logo);
                } else if (type == "Бег") {
                    exerciseActivityRecordHeaderIcon.setImageResource(R.drawable.run_logo);
                } else if (type == "Велоспорт") {
                    exerciseActivityRecordHeaderIcon.setImageResource(R.drawable.bicycle_logo);
                }
                LinearLayout.LayoutParams exerciseActivityRecordHeaderIconLayoutParams = new LinearLayout.LayoutParams(50, 50);
                exerciseActivityRecordHeaderIconLayoutParams.setMargins(25, 0, 0, 0);
                exerciseActivityRecordHeaderIcon.setLayoutParams(exerciseActivityRecordHeaderIconLayoutParams);
                exerciseActivityRecordHeader.addView(exerciseActivityRecordHeaderIcon);
                TextView exerciseActivityRecordHeaderLabel = new TextView(ExerciseActivity.this);
                exerciseActivityRecordHeaderLabel.setText(type);
                LinearLayout.LayoutParams exerciseActivityRecordHeaderLabelLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                exerciseActivityRecordHeaderLabelLayoutParams.setMargins(75, 0, 0, 0);
                exerciseActivityRecordHeaderLabel.setLayoutParams(exerciseActivityRecordHeaderLabelLayoutParams);
                exerciseActivityRecordHeader.addView(exerciseActivityRecordHeaderLabel);
                LinearLayout exerciseActivityRecordBody = new LinearLayout(ExerciseActivity.this);
                exerciseActivityRecordBody.setOrientation(LinearLayout.HORIZONTAL);
                TextView exerciseActivityRecordDuration = new TextView(ExerciseActivity.this);
                exerciseActivityRecordDuration.setText(duration);
                LinearLayout.LayoutParams exerciseActivityRecordDurationLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                exerciseActivityRecordDurationLayoutParams.setMargins(75, 0, 0, 0);
                exerciseActivityRecordDuration.setLayoutParams(exerciseActivityRecordDurationLayoutParams);
                exerciseActivityRecordBody.addView(exerciseActivityRecordDuration);
                exerciseActivityRecord.addView(exerciseActivityRecordBody);
                TextView exerciseActivityRecordTime = new TextView(ExerciseActivity.this);
                exerciseActivityRecordTime.setText(time);
                LinearLayout.LayoutParams exerciseActivityRecordTimeLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                exerciseActivityRecordTimeLayoutParams.setMargins(75, 0, 0, 0);
                exerciseActivityRecordTime.setLayoutParams(exerciseActivityRecordTimeLayoutParams);
                exerciseActivityRecord.addView(exerciseActivityRecordTime);
                exerciseActivityRecords.addView(exerciseActivityRecord);
                exerciseActivityRecord.setOrientation(LinearLayout.VERTICAL);
                exercisesCursor.moveToNext();
            }
        }
    }

}
