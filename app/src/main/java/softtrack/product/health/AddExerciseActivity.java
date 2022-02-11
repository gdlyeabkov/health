package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddExerciseActivity extends AppCompatActivity {

    public ImageButton addExerciseActivityHeaderAsideBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        initialize();
    }

    public void initialize() {
        addExerciseActivityHeaderAsideBackBtn = findViewById(R.id.add_exercise_activity_header_aside_back_btn);
        addExerciseActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddExerciseActivity.this, ExercisesListActivity.class);
                AddExerciseActivity.this.startActivity(intent);
            }
        });
    }

}
