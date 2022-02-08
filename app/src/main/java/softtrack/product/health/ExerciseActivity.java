package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {

    public ImageButton exerciseActivityHeaderAsideBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initialize();
    }

    public void initialize() {
        exerciseActivityHeaderAsideBackBtn = findViewById(R.id.exercise_activity_header_aside_back_btn);
        exerciseActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseActivity.this, MainActivity.class);
                ExerciseActivity.this.startActivity(intent);
            }
        });
    }

}
