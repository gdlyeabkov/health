package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AwardsCategoryActivity extends AppCompatActivity {

    public TextView awardsCategoryActivityHeaderLabel;
    public ImageButton awardsCategoryActivityHeaderBackBtn;
    public LinearLayout awardsCategoryActivityBody;
    public String awardsCategory = "";
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
        Log.d("debug", "категория " + awardsCategory);
        boolean isExercisesAwards = awardsCategory.contains("Упражнение");
        if (isExercisesAwards) {
            Cursor foodsCursor = db.rawQuery("Select * from awards where type=\"Велоспорт\" OR type=\"Бег\" OR type=\"Ходьба\" OR type=\"Поход\" OR type=\"Плавание\" OR type=\"Йога\"", null);
            boolean isBigDurationAwardsExists = foodsCursor.getCount() >= 1;
            if (isBigDurationAwardsExists) {
                foodsCursor.moveToFirst();
                while (true) {
                    String awardName = foodsCursor.getString(1);
                    String awardRecord = foodsCursor.getString(2);
                    String[] durationParts = awardRecord.split(" ")[0].split(":");
                    String hours = durationParts[0];
                    String minutes = durationParts[1];
                    String seconds = durationParts[2];
                    int parsedHours = Integer.valueOf(hours);
                    int parsedMinutes = Integer.valueOf(minutes);
                    int parsedSeconds = Integer.valueOf(seconds);
                    int duration = parsedHours * 60 + parsedMinutes;
                    String rawDuration = String.valueOf(duration) + " мин";
                    String awardType = foodsCursor.getString(3);
                    Log.d("debug", awardName);
                    LinearLayout award = new LinearLayout(AwardsCategoryActivity.this);
                    award.setOrientation(LinearLayout.VERTICAL);
                    award.setLayoutParams(new LinearLayout.LayoutParams(500,450));
                    ImageView awardImgItem = new ImageView(AwardsCategoryActivity.this);
                    awardImgItem.setImageResource(R.drawable.award_logo);
                    awardImgItem.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    award.addView(awardImgItem);
                    TextView awardNameItem = new TextView(AwardsCategoryActivity.this);
                    awardNameItem.setText(awardName);
                    awardNameItem.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    award.addView(awardNameItem);
                    awardsCategoryActivityBody.addView(award);
                    award.setContentDescription(awardType + "@" + awardName + "@" + rawDuration);
                    award.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CharSequence rawAwardData = view.getContentDescription();
                            Intent intent = new Intent(AwardsCategoryActivity.this, AwardActivity.class);
                            String awardData = rawAwardData.toString();
                            String[] awardDataItems = awardData.split("@");
                            String awardType = awardDataItems[0];
                            String awardName = awardDataItems[1];
                            String awardRecord = awardDataItems[2];
                            intent.putExtra("category", awardsCategory);
                            intent.putExtra("type", awardType);
                            intent.putExtra("name", awardName);
                            intent.putExtra("record", awardRecord);
                            AwardsCategoryActivity.this.startActivity(intent);
                        }
                    });
                    boolean isBreak = foodsCursor.moveToNext();
                    if (!isBreak) {
                        break;
                    }
                }
            }
        }
    }

}
