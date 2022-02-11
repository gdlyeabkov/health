package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExercisesListActivity extends AppCompatActivity {

    public ImageButton exercisesListActivityHeaderAsideBackBtn;
    public Button exercisesListActivityAddExerciseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);
        initialize();
    }

    public void initialize() {
        exercisesListActivityHeaderAsideBackBtn = findViewById(R.id.exercises_list_activity_header_aside_back_btn);
        exercisesListActivityAddExerciseBtn = findViewById(R.id.exercises_list_activity_add_exercise_btn);
        exercisesListActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExercisesListActivity.this, MainActivity.class);
                ExercisesListActivity.this.startActivity(intent);
            }
        });
        exercisesListActivityAddExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExercisesListActivity.this, AddExerciseActivity.class);
                ExercisesListActivity.this.startActivity(intent);
            }
        });
    }

}
