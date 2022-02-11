package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
        boolean isRecordsFound = countRecords >= 1;
        if (isRecordsFound) {
            exerciseActivityNotFound.setVisibility(View.GONE);
            for (int i = 0; i < countRecords; i++) {
                LinearLayout exerciseActivityRecord = new LinearLayout(ExerciseActivity.this);
                exerciseActivityRecord.setOrientation(LinearLayout.VERTICAL);
                LinearLayout exerciseActivityRecordHeader = new LinearLayout(ExerciseActivity.this);
                exerciseActivityRecord.setOrientation(LinearLayout.HORIZONTAL);
                exerciseActivityRecord.addView(exerciseActivityRecordHeader);
                ImageView exerciseActivityRecordHeaderIcon = new ImageView(ExerciseActivity.this);
                exerciseActivityRecordHeader.addView(exerciseActivityRecordHeaderIcon);
                TextView exerciseActivityRecordHeaderLabel = new TextView(ExerciseActivity.this);
                exerciseActivityRecordHeaderLabel.setText("Ходьба");
                exerciseActivityRecordHeader.addView(exerciseActivityRecordHeaderLabel);
                LinearLayout exerciseActivityRecordBody = new LinearLayout(ExerciseActivity.this);
                exerciseActivityRecordBody.setOrientation(LinearLayout.HORIZONTAL);
                exerciseActivityRecord.addView(exerciseActivityRecordBody);
                TextView exerciseActivityRecordDuration = new TextView(ExerciseActivity.this);
                exerciseActivityRecordDuration.setText("00:00:00");
                exerciseActivityRecordBody.addView(exerciseActivityRecordDuration);
                TextView exerciseActivityRecordTime = new TextView(ExerciseActivity.this);
                exerciseActivityRecordTime.setText("00:00");
                exerciseActivityRecord.addView(exerciseActivityRecordDuration);
                exerciseActivityRecords.addView(exerciseActivityRecord);
            }
        }
    }

}
