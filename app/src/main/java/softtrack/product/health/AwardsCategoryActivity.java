package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AwardsCategoryActivity extends AppCompatActivity {

    public TextView awardsCategoryActivityHeaderLabel;
    public ImageButton awardsCategoryActivityHeaderBackBtn;
    public LinearLayout awardsCategoryActivityBody;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards_category);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        awardsCategoryActivityHeaderLabel = findViewById(R.id.awards_category_activity_header_label);
        awardsCategoryActivityHeaderBackBtn = findViewById(R.id.awards_category_activity_header_back_btn);
        awardsCategoryActivityBody = findViewById(R.id.awards_category_activity_body);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        String awardsCategory = "";
        if (isExtrasExists) {
            awardsCategory = extras.getString("category");
            awardsCategoryActivityHeaderLabel.setText(awardsCategory);
        }
        awardsCategoryActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AwardsCategoryActivity.this, AwardsActivity.class);
                AwardsCategoryActivity.this.startActivity(intent);
            }
        });
        boolean isExercisesAwards = awardsCategory == "Упражнение";
        // isExercisesAwards = false;
        if (isExercisesAwards) {
            Cursor foodsCursor = db.rawQuery("Select * from awards where type=\"Велоспорт\" OR type=\"Бег\" OR type=\"Ходьба\"", null);
            foodsCursor.moveToFirst();
            while (true) {
                String awardName = foodsCursor.getString(1);
                LinearLayout award = new LinearLayout(AwardsCategoryActivity.this);
                TextView awardNameItem = new TextView(AwardsCategoryActivity.this);
                awardNameItem.setText(awardName);
                award.addView(awardNameItem);
                awardsCategoryActivityBody.addView(award);
                boolean isBreak = foodsCursor.moveToNext();
                if (!isBreak) {
                    break;
                }
            }
        }
    }

}
