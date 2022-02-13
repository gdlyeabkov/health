package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AwardsActivity extends AppCompatActivity {

    public LinearLayout awardsActivityBodyExercisesThirdItem;
    public ImageView awardsActivityBodyExercisesThirdItemImg;
    public int accessedColor;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        awardsActivityBodyExercisesThirdItem = findViewById(R.id.awards_activity_body_exercises_third_item);
        awardsActivityBodyExercisesThirdItemImg = findViewById(R.id.awards_activity_body_exercises_third_item_img);
        accessedColor = Color.rgb(0, 0, 0);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        long countExercisesRecords = DatabaseUtils.queryNumEntries(db, "exercise_records");
        boolean isExercisesRecordsExists = countExercisesRecords >= 1;
        if (isExercisesRecordsExists) {
            awardsActivityBodyExercisesThirdItemImg.setColorFilter(accessedColor);
            awardsActivityBodyExercisesThirdItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AwardsActivity.this, AwardActivity.class);
                    intent.putExtra("category", "Упражнение");
                    AwardsActivity.this.startActivity(intent);
                }
            });
        }

    }

}
