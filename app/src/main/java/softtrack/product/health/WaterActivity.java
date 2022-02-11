package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterActivity extends AppCompatActivity {

    public Button waterActivityDayDetailsControlIncreaseBtn;
    public Button waterActivityDayDetailsControlDecreaseBtn;
    public TextView waterActivityDayDetailsControlGlassesCountLabel;
    public int waterControlBtnEnabledColor;
    public int waterControlBtnDisabledColor;
    public TextView detectWaterCapacityLabel;
    public int isVisible;
    public int isUnVisible;
    public TextView countDrinkedMLLabel;
    final int countMLPerGlass = 250;
    public ImageButton waterActivityHeaderAsideBackBtn;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        initialize();
    }
    @SuppressLint("WrongConstant")
    public void initialize() {
        isVisible = View.VISIBLE;
        isUnVisible = View.INVISIBLE;
        waterActivityHeaderAsideBackBtn = findViewById(R.id.water_activity_header_aside_back_btn);
        countDrinkedMLLabel = findViewById(R.id.count_drinked_ml_label);
        waterControlBtnDisabledColor = Color.rgb(150, 150, 150);
        waterControlBtnEnabledColor = Color.rgb(0, 0, 0);
        detectWaterCapacityLabel = findViewById(R.id.detect_water_capacity_label);
        waterActivityDayDetailsControlIncreaseBtn = findViewById(R.id.water_activity_day_details_control_increase_btn);
        waterActivityDayDetailsControlDecreaseBtn = findViewById(R.id.water_activity_day_details_control_decrease_btn);;
        waterActivityDayDetailsControlGlassesCountLabel =  findViewById(R.id.water_activity_day_details_control_glasses_count_label);;
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        waterActivityDayDetailsControlDecreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawGlassesCount = waterActivityDayDetailsControlGlassesCountLabel.getText();
                String parsedGlassesCount = rawGlassesCount.toString();
                int glassesCount = Integer.valueOf(parsedGlassesCount);
                boolean isCanDecrease = glassesCount >= 1;
                if (isCanDecrease) {
                    glassesCount -= 1;
                    String updatedRawGlassesCount = String.valueOf(glassesCount);
                    waterActivityDayDetailsControlGlassesCountLabel.setText(updatedRawGlassesCount);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("water", glassesCount);
                    db.update("indicators", contentValues, "_id = 1", new String[] {  });
                    boolean isCanNotDecrease = glassesCount == 0;
                    if (isCanNotDecrease) {
                        waterActivityDayDetailsControlDecreaseBtn.setEnabled(false);
                        waterActivityDayDetailsControlDecreaseBtn.setTextColor(waterControlBtnDisabledColor);
                        int detectWaterCapacityLabelVisibility = detectWaterCapacityLabel.getVisibility();
                        boolean isDetectWaterCapacityLabelUnVisibile = detectWaterCapacityLabelVisibility == isUnVisible;
                        if (isDetectWaterCapacityLabelUnVisibile) {
                            detectWaterCapacityLabel.setVisibility(isVisible);
                        }
                    }
                    int mls = glassesCount * countMLPerGlass;
                    String rawMLs = String.valueOf(mls);
                    String countDrinkedMLLabelValue = "(" + rawMLs + " мл)";
                    countDrinkedMLLabel.setText(countDrinkedMLLabelValue);
                }
            }
        });
        waterActivityDayDetailsControlIncreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDecreaseBtnEnabled = waterActivityDayDetailsControlDecreaseBtn.isEnabled();
                boolean isDecreaseBtnDisabled = !isDecreaseBtnEnabled;
                if (isDecreaseBtnDisabled) {
                    waterActivityDayDetailsControlDecreaseBtn.setEnabled(true);
                    waterActivityDayDetailsControlDecreaseBtn.setTextColor(waterControlBtnEnabledColor);
                }
                int detectWaterCapacityLabelVisibility = detectWaterCapacityLabel.getVisibility();
                boolean isDetectWaterCapacityLabelVisibile = detectWaterCapacityLabelVisibility == isVisible;
                if (isDetectWaterCapacityLabelVisibile) {
                    detectWaterCapacityLabel.setVisibility(isUnVisible);
                }
                CharSequence rawGlassesCount = waterActivityDayDetailsControlGlassesCountLabel.getText();
                String parsedGlassesCount = rawGlassesCount.toString();
                int glassesCount = Integer.valueOf(parsedGlassesCount);
                glassesCount += 1;
                String updatedRawGlassesCount = String.valueOf(glassesCount);
                waterActivityDayDetailsControlGlassesCountLabel.setText(updatedRawGlassesCount);
                ContentValues contentValues = new ContentValues();
                contentValues.put("water", glassesCount);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
                int mls = glassesCount * countMLPerGlass;
                String rawMLs = String.valueOf(mls);
                String countDrinkedMLLabelValue = "(" + rawMLs + " мл)";
                countDrinkedMLLabel.setText(countDrinkedMLLabelValue);
            }
        });
        waterActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        indicatorsCursor.moveToFirst();
        int water = indicatorsCursor.getInt(1);
        String rawWater = String.valueOf(water);
        waterActivityDayDetailsControlGlassesCountLabel.setText(rawWater);
        boolean isHaveWater = water >= 1;
        if (isHaveWater) {
            waterActivityDayDetailsControlDecreaseBtn.setEnabled(true);
            waterActivityDayDetailsControlDecreaseBtn.setTextColor(waterControlBtnEnabledColor);
        }
        int mls = water * countMLPerGlass;
        String rawMLs = String.valueOf(mls);
        String countDrinkedMLLabelValue = "(" + rawMLs + " мл)";
        countDrinkedMLLabel.setText(countDrinkedMLLabelValue);
    }
}
