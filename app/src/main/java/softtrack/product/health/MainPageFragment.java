package softtrack.product.health;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainPageFragment extends Fragment {

    public MainActivity parentActivity;
    int waterControlBtnEnabledColor;
    int waterControlBtnDisabledColor;

    public MainPageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_page, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }

    public void initialize() {
        parentActivity = (MainActivity) this.getActivity();
        waterControlBtnDisabledColor = Color.rgb(150, 150, 150);
        waterControlBtnEnabledColor = Color.rgb(0, 0, 0);
        TextView mainPageWaterBlockDrinkGlasses = parentActivity.findViewById(R.id.main_page_water_block_drink_glasses);
        Button mainPageWaterBlockDrinkGlassesDecreaseBtn = parentActivity.findViewById(R.id.main_page_water_block_drink_glasses_decrease_btn);
        mainPageWaterBlockDrinkGlassesDecreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawGlassesCount = mainPageWaterBlockDrinkGlasses.getText();
                String parsedGlassesCount = rawGlassesCount.toString();
                int glassesCount = Integer.valueOf(parsedGlassesCount);
                boolean isCanDecrease = glassesCount >= 1;
                if (isCanDecrease) {
                    glassesCount -= 1;
                    String updatedRawGlassesCount = String.valueOf(glassesCount);
                    mainPageWaterBlockDrinkGlasses.setText(updatedRawGlassesCount);
                    boolean isCanNotDecrease = glassesCount == 0;
                    if (isCanNotDecrease) {
                        mainPageWaterBlockDrinkGlassesDecreaseBtn.setEnabled(false);
                        mainPageWaterBlockDrinkGlassesDecreaseBtn.setTextColor(waterControlBtnDisabledColor);
                    }
                }
            }
        });
        Button mainPageWaterBlockDrinkGlassesIncreaseBtn = parentActivity.findViewById(R.id.main_page_water_block_drink_glasses_increase_btn);
        mainPageWaterBlockDrinkGlassesIncreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDecreaseBtnEnabled = mainPageWaterBlockDrinkGlassesDecreaseBtn.isEnabled();
                boolean isDecreaseBtnDisabled = !isDecreaseBtnEnabled;
                if (isDecreaseBtnDisabled) {
                    mainPageWaterBlockDrinkGlassesDecreaseBtn.setEnabled(true);
                    mainPageWaterBlockDrinkGlassesDecreaseBtn.setTextColor(waterControlBtnEnabledColor);
                }
                CharSequence rawGlassesCount = mainPageWaterBlockDrinkGlasses.getText();
                String parsedGlassesCount = rawGlassesCount.toString();
                int glassesCount = Integer.valueOf(parsedGlassesCount);
                glassesCount += 1;
                String updatedRawGlassesCount = String.valueOf(glassesCount);
                mainPageWaterBlockDrinkGlasses.setText(updatedRawGlassesCount);
            }
        });
        LinearLayout mainPageActiveBlock = parentActivity.findViewById(R.id.main_page_active_block);
        mainPageActiveBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, ActiveActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageWalkBlock = parentActivity.findViewById(R.id.main_page_walk_block);
        mainPageWalkBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, WalkActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageExerciseBlock = parentActivity.findViewById(R.id.main_page_exercise_block);
        mainPageExerciseBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, ExerciseActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageFoodBlock = parentActivity.findViewById(R.id.main_page_food_block);
        mainPageFoodBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, FoodActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageSleepBlock = parentActivity.findViewById(R.id.main_page_sleep_block);
        mainPageSleepBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, SleepActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageBodyCompositionBlock = parentActivity.findViewById(R.id.main_page_body_composition_block);
        mainPageBodyCompositionBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, BodyActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageWaterBlock = parentActivity.findViewById(R.id.main_page_water_block);
        mainPageWaterBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, WaterActivity.class);
                parentActivity.startActivity(intent);
            }
        });
    }

}
