package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecordExerciseResultsActivity extends AppCompatActivity {

    public ImageButton recordExerciseAcitvityHeaderAsideBackBtn;
    public String exerciseType;
    public TextView recordExerciseAcitvityHeaderAsideLabel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise_results);
        initialize();
    }

    public void initialize() {
        recordExerciseAcitvityHeaderAsideBackBtn = findViewById(R.id.record_exercise_results_activity_header_aside_back_btn);
        recordExerciseAcitvityHeaderAsideLabel = findViewById(R.id.record_exercise_results_activity_header_aside_label);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        exerciseType = extras.getString("type");
        recordExerciseAcitvityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordExerciseResultsActivity.this, MainActivity.class);
                intent.putExtra("type", exerciseType);
                RecordExerciseResultsActivity.this.startActivity(intent);
            }
        });
        recordExerciseAcitvityHeaderAsideLabel.setText(exerciseType);
    }

}
