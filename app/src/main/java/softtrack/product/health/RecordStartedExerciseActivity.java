package softtrack.product.health;

import android.annotation.SuppressLint;
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
            }
        });
    }

}
