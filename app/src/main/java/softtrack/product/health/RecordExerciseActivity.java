package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecordExerciseActivity extends AppCompatActivity {

    public ImageButton recordExerciseAcitvityHeaderAsideBackBtn;
    public TextView recordExerciseAcitvityHeaderAsideLabel;
    public String exerciseType;
    public Button recordExerciseAcitvityStartBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise);
        initialize();
    }

    public void initialize() {
        recordExerciseAcitvityHeaderAsideBackBtn = findViewById(R.id.record_exercise_acitvity_header_aside_back_btn);
        recordExerciseAcitvityHeaderAsideLabel = findViewById(R.id.record_exercise_acitvity_header_aside_label);
        recordExerciseAcitvityStartBtn = findViewById(R.id.record_exercise_acitvity_start_btn);
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
                Intent intent = new Intent(RecordExerciseActivity.this, RecordStartedExerciseActivity.class);
                intent.putExtra("type", exerciseType);
                RecordExerciseActivity.this.startActivity(intent);
            }
        });
    }

}
