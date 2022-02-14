package softtrack.product.health;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditMyPageActivity extends AppCompatActivity {

    public Button editMyPageActivitityFooterCancelBtn;
    public Button editMyPageActivitityBodyGalleryBtn;
    public Button editMyPageActivitityBodyCameraBtn;
    private static final int PICK_IMAGE = 100;
    public ImageView editMyPageActivitityBodyActiveSatIcon;
    public ImageView editMyPageActivitityBodyActiveStandIcon;
    public ImageView editMyPageActivitityBodyActiveNecessaryIcon;
    public ImageView editMyPageActivitityBodyActiveBigIcon;
    public TextView editMyPageActivitityBodyActiveLabel;
    public TextView editMyPageActivitityBodyActiveDesc;
    public LinearLayout editMyPageActivitityBodyCharacteristicsWeight;
    public LinearLayout editMyPageActivitityBodyCharacteristicsGender;
    public LinearLayout editMyPageActivitityBodyCharacteristicsGrowth;
    public Button editMyPageActivitityFooterSaveBtn;
    public EditText editMyPageActivitityBodyNicknameField;
    public String currentGender = "";
    public String settedGrowth = "0.0";
    public String settedWeight = "0.0";
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_page);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        editMyPageActivitityFooterCancelBtn = findViewById(R.id.edit_my_page_activitity_footer_cancel_btn);
        editMyPageActivitityBodyGalleryBtn = findViewById(R.id.edit_my_page_activitity_body_gallery_btn);
        editMyPageActivitityBodyCameraBtn = findViewById(R.id.edit_my_page_activitity_body_camera_btn);
        editMyPageActivitityBodyActiveLabel = findViewById(R.id.edit_my_page_activitity_body_active_label);
        editMyPageActivitityBodyActiveDesc = findViewById(R.id.edit_my_page_activitity_body_active_desc);
        editMyPageActivitityBodyCharacteristicsWeight = findViewById(R.id.edit_my_page_activitity_body_characteristics_weight);
        editMyPageActivitityBodyCharacteristicsGender = findViewById(R.id.edit_my_page_activitity_body_characteristics_gender);
        editMyPageActivitityBodyCharacteristicsGrowth = findViewById(R.id.edit_my_page_activitity_body_characteristics_growth);
        editMyPageActivitityFooterSaveBtn = findViewById(R.id.edit_my_page_activitity_footer_save_btn);
        editMyPageActivitityBodyNicknameField = findViewById(R.id.edit_my_page_activitity_body_nickname_field);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        editMyPageActivitityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
                EditMyPageActivity.this.startActivity(intent);
            }
        });
        editMyPageActivitityBodyGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        editMyPageActivitityBodyCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
                EditMyPageActivity.this.startActivity(intent);*/
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
        editMyPageActivitityBodyActiveSatIcon = findViewById(R.id.edit_my_page_activitity_body_active_sat_icon);
        editMyPageActivitityBodyActiveStandIcon = findViewById(R.id.edit_my_page_activitity_body_active_stand_icon);
        editMyPageActivitityBodyActiveNecessaryIcon = findViewById(R.id.edit_my_page_activitity_body_active_necessary_icon);
        editMyPageActivitityBodyActiveBigIcon = findViewById(R.id.edit_my_page_activitity_body_active_big_icon);
        editMyPageActivitityBodyActiveSatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String levelActivityLabel = "Сидячий образ жизни";
                String levelActivityDesc = "Обычные ежедневные нагрузки";
                selectLevelActivity(view, levelActivityLabel, levelActivityDesc);
            }
        });
        editMyPageActivitityBodyActiveStandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String levelActivityLabel = "Несущественная активность";
                String levelActivityDesc = "Обычные ежедневные нагрузки и 30-60 мин.\nумеренных ежедневных нагрузок(например, ходьба\nсо скоростью 5-7км/ч)";
                selectLevelActivity(view, levelActivityLabel, levelActivityDesc);
            }
        });
        editMyPageActivitityBodyActiveNecessaryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String levelActivityLabel = "Активный";
                String levelActivityDesc = "Обычные ежедневные нагрузки и не менее 60 мин.\nумеренных ежедневных нагрузок";
                selectLevelActivity(view, levelActivityLabel, levelActivityDesc);
            }
        });
        editMyPageActivitityBodyActiveBigIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String levelActivityLabel = "Большая активность";
                String levelActivityDesc = "Обычные ежедневные нагрузки, а также не менее\n60 мин. умеренных ежедневных нагрузок и 60 мин.\n интенсивных нагрузок. Вместо этого вы можете\nдобавить к ежедневным нагрузкам 120 мин.\nумеренных нагрузок";
                selectLevelActivity(view, levelActivityLabel, levelActivityDesc);
            }
        });
        String levelActivityLabel = "Сидячий образ жизни";
        String levelActivityDesc = "Обычные ежедневные нагрузки";
        selectLevelActivity(editMyPageActivitityBodyActiveSatIcon, levelActivityLabel, levelActivityDesc);
        editMyPageActivitityBodyCharacteristicsGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMyPageActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_gender_selector, null);
                builder.setView(dialogView);
                builder.setCancelable(true);
                RadioGroup genderGroup = dialogView.findViewById(R.id.activity_gender_selector_gender_group);

                builder.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                        RadioButton rawCurrentGender = dialogView.findViewById(selectedGenderId);
                        CharSequence rawCurrentGenderConent = rawCurrentGender.getText();
                        String currentGenderConent = rawCurrentGenderConent.toString();
                        currentGender = currentGenderConent;
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Пол");
                alert.show();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Log.d("debug", "Пол " + currentGender);
                        if (currentGender == "Мужской") {
                            RadioButton maleGender = dialogView.findViewById(R.id.activity_gender_selector_male_gender);
                            maleGender.setChecked(true);
                        } else if (currentGender == "Женский") {
                            RadioButton femaleGender = dialogView.findViewById(R.id.activity_gender_selector_female_gender);
                            femaleGender.setChecked(true);
                        } else if (currentGender == "Другое") {
                            RadioButton otherGender = dialogView.findViewById(R.id.activity_gender_selector_other_gender);
                            otherGender.setChecked(true);
                        } else if (currentGender == "Не хочу указывать") {
                            RadioButton notGender = dialogView.findViewById(R.id.activity_gender_selector_not_gender);
                            notGender.setChecked(true);
                        }
                    }
                });
            }
        });
        editMyPageActivitityBodyCharacteristicsGrowth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMyPageActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_growth_selector, null);
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LinearLayout growthSelectorActivityRealPart = dialogView.findViewById(R.id.growth_selector_activity_real_part);
                        ScrollView growthSelectorActivityRealPartScroll = dialogView.findViewById(R.id.growth_selector_activity_real_part_scroll);
                        LinearLayout growthSelectorActivityImaginaryPart = dialogView.findViewById(R.id.growth_selector_activity_imaginary_part);
                        ScrollView growthSelectorActivityImaginaryPartScroll = dialogView.findViewById(R.id.growth_selector_activity_imaginary_part_scroll);
                        int realScrollY = growthSelectorActivityRealPartScroll.getScrollY();
                        int imaginaryScrollY = growthSelectorActivityImaginaryPartScroll.getScrollY();
                        Log.d("debug", "Vertical offset: " + Integer.toString(realScrollY) + ", " + Integer.toString(imaginaryScrollY));
                        int destinationBetweenScrollItems = 105;
                        String settedImaginaryGrowth = "0";
                        String settedRealGrowth = "0";
                        int selectedLabelIndex = ((int) (Math.floor(realScrollY / destinationBetweenScrollItems)));
                        int hoursColumnLabelsCount = growthSelectorActivityImaginaryPart.getChildCount();
                        boolean isChildrenAccess = selectedLabelIndex < hoursColumnLabelsCount;
                        if (isChildrenAccess) {
                            for (int hoursLabelIndex = 1; hoursLabelIndex < hoursColumnLabelsCount; hoursLabelIndex++) {
                                TextView currentLabel = ((TextView)(growthSelectorActivityImaginaryPart.getChildAt(hoursLabelIndex)));
                                Typeface labelTypeface = Typeface.DEFAULT;
                                currentLabel.setTypeface(labelTypeface, Typeface.NORMAL);
                            }
                            TextView selectedLabel = ((TextView)(growthSelectorActivityImaginaryPart.getChildAt(selectedLabelIndex)));
                            Typeface labelTypeface = selectedLabel.getTypeface();
                            selectedLabel.setTypeface(labelTypeface, Typeface.BOLD);
                            CharSequence rawSelectLabelContent = selectedLabel.getText();
                            String selectLabelContent = rawSelectLabelContent.toString();
                            settedRealGrowth = selectLabelContent;
                        }
                        selectedLabelIndex = ((int) (Math.floor(imaginaryScrollY / destinationBetweenScrollItems)));
                        hoursColumnLabelsCount = growthSelectorActivityImaginaryPart.getChildCount();
                        isChildrenAccess = selectedLabelIndex < hoursColumnLabelsCount;
                        if (isChildrenAccess) {
                            for (int hoursLabelIndex = 1; hoursLabelIndex < hoursColumnLabelsCount; hoursLabelIndex++) {
                                TextView currentLabel = ((TextView)(growthSelectorActivityImaginaryPart.getChildAt(hoursLabelIndex)));
                                Typeface labelTypeface = Typeface.DEFAULT;
                                currentLabel.setTypeface(labelTypeface, Typeface.NORMAL);
                            }
                            TextView selectedLabel = ((TextView)(growthSelectorActivityImaginaryPart.getChildAt(selectedLabelIndex)));
                            Typeface labelTypeface = selectedLabel.getTypeface();
                            selectedLabel.setTypeface(labelTypeface, Typeface.BOLD);
                            CharSequence rawSelectLabelContent = selectedLabel.getText();
                            String selectLabelContent = rawSelectLabelContent.toString();
                            settedImaginaryGrowth = selectLabelContent;
                        }
                        settedGrowth = settedRealGrowth + "." + settedImaginaryGrowth;
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Рост");
                alert.show();
            }
        });
        editMyPageActivitityBodyCharacteristicsWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMyPageActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_weight_selector, null);
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LinearLayout weightSelectorActivityRealPart = dialogView.findViewById(R.id.weight_selector_activity_real_part);
                        ScrollView weightSelectorActivityRealPartScroll = dialogView.findViewById(R.id.weight_selector_activity_real_part_scroll);
                        LinearLayout weightSelectorActivityImaginaryPart = dialogView.findViewById(R.id.weight_selector_activity_imaginary_part);
                        ScrollView weightSelectorActivityImaginaryPartScroll = dialogView.findViewById(R.id.weight_selector_activity_imaginary_part_scroll);
                        int realScrollY = weightSelectorActivityRealPartScroll.getScrollY();
                        int imaginaryScrollY = weightSelectorActivityImaginaryPartScroll.getScrollY();
                        Log.d("debug", "Vertical offset: " + Integer.toString(realScrollY) + ", " + Integer.toString(imaginaryScrollY));
                        int destinationBetweenScrollItems = 105;
                        String settedImaginaryWeight = "0";
                        String settedRealWeight = "0";
                        int selectedLabelIndex = ((int) (Math.floor(realScrollY / destinationBetweenScrollItems)));
                        int hoursColumnLabelsCount = weightSelectorActivityImaginaryPart.getChildCount();
                        boolean isChildrenAccess = selectedLabelIndex < hoursColumnLabelsCount;
                        if (isChildrenAccess) {
                            for (int hoursLabelIndex = 1; hoursLabelIndex < hoursColumnLabelsCount; hoursLabelIndex++) {
                                TextView currentLabel = ((TextView)(weightSelectorActivityImaginaryPart.getChildAt(hoursLabelIndex)));
                                Typeface labelTypeface = Typeface.DEFAULT;
                                currentLabel.setTypeface(labelTypeface, Typeface.NORMAL);
                            }
                            TextView selectedLabel = ((TextView)(weightSelectorActivityImaginaryPart.getChildAt(selectedLabelIndex)));
                            Typeface labelTypeface = selectedLabel.getTypeface();
                            selectedLabel.setTypeface(labelTypeface, Typeface.BOLD);
                            CharSequence rawSelectLabelContent = selectedLabel.getText();
                            String selectLabelContent = rawSelectLabelContent.toString();
                            settedRealWeight = selectLabelContent;
                        }
                        selectedLabelIndex = ((int) (Math.floor(imaginaryScrollY / destinationBetweenScrollItems)));
                        hoursColumnLabelsCount = weightSelectorActivityImaginaryPart.getChildCount();
                        isChildrenAccess = selectedLabelIndex < hoursColumnLabelsCount;
                        if (isChildrenAccess) {
                            for (int hoursLabelIndex = 1; hoursLabelIndex < hoursColumnLabelsCount; hoursLabelIndex++) {
                                TextView currentLabel = ((TextView)(weightSelectorActivityImaginaryPart.getChildAt(hoursLabelIndex)));
                                Typeface labelTypeface = Typeface.DEFAULT;
                                currentLabel.setTypeface(labelTypeface, Typeface.NORMAL);
                            }
                            TextView selectedLabel = ((TextView)(weightSelectorActivityImaginaryPart.getChildAt(selectedLabelIndex)));
                            Typeface labelTypeface = selectedLabel.getTypeface();
                            selectedLabel.setTypeface(labelTypeface, Typeface.BOLD);
                            CharSequence rawSelectLabelContent = selectedLabel.getText();
                            String selectLabelContent = rawSelectLabelContent.toString();
                            settedImaginaryWeight = selectLabelContent;
                        }
                        settedWeight = settedRealWeight + "." + settedImaginaryWeight;
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ScrollView weightSelectorActivityRealPartScroll = dialogView.findViewById(R.id.weight_selector_activity_real_part_scroll);
                        ScrollView weightSelectorActivityImaginaryPartScroll = dialogView.findViewById(R.id.weight_selector_activity_imaginary_part_scroll);
                        int destinationBetweenScrollItems = 105;
                        int realScrollY = Integer.valueOf(settedGrowth) * destinationBetweenScrollItems;
                        int imaginaryScrollY = Integer.valueOf(settedWeight) * destinationBetweenScrollItems;
                        weightSelectorActivityRealPartScroll.scrollTo(0, realScrollY);
                        weightSelectorActivityImaginaryPartScroll.scrollTo(0, imaginaryScrollY);
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Вес");
                alert.show();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        // здесь

                    }
                });
            }
        });
        editMyPageActivitityFooterSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "";
                CharSequence rawName = editMyPageActivitityBodyNicknameField.getText();
                name = rawName.toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("gender", currentGender);
                contentValues.put("growth", settedGrowth);
                contentValues.put("weight", settedWeight);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
                Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
                EditMyPageActivity.this.startActivity(intent);
            }
        });
        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        indicatorsCursor.moveToFirst();
        String name = "";
        name = indicatorsCursor.getString(9);
        editMyPageActivitityBodyNicknameField.setText(name);
        currentGender = indicatorsCursor.getString(10);
        settedGrowth = indicatorsCursor.getString(11);
        settedWeight = indicatorsCursor.getString(12);
    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public void selectLevelActivity(View levelActivity, String levelActivityLabel, String levelActivityDesc) {
        ImageView currentLevelActivity = (ImageView) levelActivity;
        editMyPageActivitityBodyActiveSatIcon.setColorFilter(null);
        editMyPageActivitityBodyActiveStandIcon.setColorFilter(null);
        editMyPageActivitityBodyActiveNecessaryIcon.setColorFilter(null);
        editMyPageActivitityBodyActiveBigIcon.setColorFilter(null);
        currentLevelActivity.setColorFilter(Color.rgb(0, 150, 0));
        editMyPageActivitityBodyActiveLabel.setText(levelActivityLabel);
        editMyPageActivitityBodyActiveDesc.setText(levelActivityDesc);
    }

}
