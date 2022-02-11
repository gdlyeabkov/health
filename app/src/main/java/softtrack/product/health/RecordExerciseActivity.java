package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RecordExerciseActivity extends AppCompatActivity {

    public ImageButton recordExerciseAcitvityHeaderAsideBackBtn;
    public TextView recordExerciseAcitvityHeaderAsideLabel;
    public String exerciseType;
    public Button recordExerciseAcitvityStartBtn;
    public final String oneCharPrefix = "0";
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        recordExerciseAcitvityHeaderAsideBackBtn = findViewById(R.id.record_exercise_acitvity_header_aside_back_btn);
        recordExerciseAcitvityHeaderAsideLabel = findViewById(R.id.record_exercise_acitvity_header_aside_label);
        recordExerciseAcitvityStartBtn = findViewById(R.id.record_exercise_acitvity_start_btn);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        recordExerciseAcitvityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordExerciseActivity.this, MainActivity.class);
                intent.putExtra("type", "Бег");
                RecordExerciseActivity.this.startActivity(intent);
            }
        });
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        exerciseType = extras.getString("type");
        recordExerciseAcitvityHeaderAsideLabel.setText(exerciseType);
        recordExerciseAcitvityStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("is_exercise_enabled", 1);
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                String rawHours = String.valueOf(hours);
                boolean isNotLocalizedHours = rawHours.length() == 1;
                if (isNotLocalizedHours) {
                    rawHours = oneCharPrefix + rawHours;
                }
                String rawMinutes = String.valueOf(minutes);
                boolean isNotLocalizedMinutes = rawMinutes.length() == 1;
                if (isNotLocalizedMinutes) {
                    rawMinutes = oneCharPrefix + rawMinutes;
                }
                String exerciseStartTime = rawHours + ":" + rawMinutes;
                contentValues.put("exercise_start_time", exerciseStartTime);
                contentValues.put("exercise_type", exerciseType);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });

                Intent intent = new Intent(RecordExerciseActivity.this, RecordStartedExerciseActivity.class);
                intent.putExtra("type", exerciseType);
                RecordExerciseActivity.this.startActivity(intent);
            }
        });
    }

}
