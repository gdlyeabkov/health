package softtrack.product.health;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecordWeightDataActivity extends AppCompatActivity {

    public Button recordWeightDataActivityFooterCancelBtn;
    public Button recordWeightDataActivityFooterSaveBtn;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;
    public EditText recordWeightDataActivityBodyNoteField;
    public EditText recordWeightDataActivityBodyFatField;
    public EditText recordWeightDataActivityBodyMusculatureField;
    public ScrollView recordWeightDataActivityBodyWeightWrapRealPartScroll;
    public LinearLayout recordWeightDataActivityBodyWeightWrapRealPart;
    public ScrollView recordWeightDataActivityBodyWeightWrapImaginaryPartScroll;
    public LinearLayout recordWeightDataActivityBodyWeightWrapImaginaryPart;
    public float settedWeight = 2.0f;
    public TextView recordWeightDataActivityBodyDateLabel;
    public ArrayList<String> monthsLabels;
    public ArrayList<String> weeksLabels;
    public String oneCharPrefix = "0";
    public LinearLayout recordWeightDataActivityBodyDate;
    public String rawRecordTime;
    public String settedRealWeight = "2";
    public String settedImaginaryWeight = "0";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight_data);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("WrongConstant")
    public void initialize() {
        recordWeightDataActivityFooterCancelBtn = findViewById(R.id.record_weight_data_activity_footer_cancel_btn);
        recordWeightDataActivityFooterSaveBtn = findViewById(R.id.record_weight_data_activity_footer_save_btn);
        recordWeightDataActivityBodyNoteField = findViewById(R.id.record_weight_data_activity_body_note_field);
        recordWeightDataActivityBodyFatField = findViewById(R.id.record_weight_data_activity_body_fat_field);
        recordWeightDataActivityBodyMusculatureField = findViewById(R.id.record_weight_data_activity_body_musculature_field);
        recordWeightDataActivityBodyWeightWrapRealPartScroll = findViewById(R.id.record_weight_data_activity_body_weight_wrap_real_part_scroll);
        recordWeightDataActivityBodyWeightWrapRealPart = findViewById(R.id.record_weight_data_activity_body_weight_wrap_real_part);
        recordWeightDataActivityBodyWeightWrapImaginaryPartScroll = findViewById(R.id.record_weight_data_activity_body_weight_wrap_imaginary_part_scroll);
        recordWeightDataActivityBodyWeightWrapImaginaryPart = findViewById(R.id.record_weight_data_activity_body_weight_wrap_imaginary_part);
        recordWeightDataActivityBodyDateLabel = findViewById(R.id.record_weight_data_activity_body_date_label);
        recordWeightDataActivityBodyDate = findViewById(R.id.record_weight_data_activity_body_date);
        monthsLabels = new ArrayList<String>();
        monthsLabels.add("января");
        monthsLabels.add("февраля");
        monthsLabels.add("марта");
        monthsLabels.add("апреля");
        monthsLabels.add("мая");
        monthsLabels.add("июня");
        monthsLabels.add("июля");
        monthsLabels.add("августа");
        monthsLabels.add("сентября");
        monthsLabels.add("октября");
        monthsLabels.add("ноября");
        monthsLabels.add("декабря");
        weeksLabels = new ArrayList<String>();
        weeksLabels.add("вс");
        weeksLabels.add("пн");
        weeksLabels.add("вт");
        weeksLabels.add("ср");
        weeksLabels.add("чт");
        weeksLabels.add("пт");
        weeksLabels.add("сб");
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String rawDayOfMonth = String.valueOf(dayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String rawDayOfWeek = weeksLabels.get(dayOfWeek);
        int month = calendar.get(Calendar.MONTH);
        String rawMonth = monthsLabels.get(month);
        int year = calendar.get(Calendar.YEAR);
        String rawYear = String.valueOf(year);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String rawHour = String.valueOf(hour);
        int rawHoursLength = rawHour.length();
        boolean isHoursWithOneChar = rawHoursLength == 1;
        if (isHoursWithOneChar) {
            rawHour = oneCharPrefix + rawHour;
        }
        int minute = calendar.get(Calendar.MINUTE);
        String rawMinute = String.valueOf(minute);
        int rawMinutesLength = rawMinute.length();
        boolean isMinutesWithOneChar = rawMinutesLength == 1;
        if (isMinutesWithOneChar) {
            rawMinute = oneCharPrefix + rawMinute;
        }
        String rawRecordDate = rawDayOfWeek + ", " + rawDayOfMonth + " " + rawMonth;
        rawRecordTime = rawHour + ":" + rawMinute;
        String recordTimeStamp = rawRecordDate + " " + rawRecordTime;
        recordWeightDataActivityBodyDateLabel.setText(recordTimeStamp);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        recordWeightDataActivityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordWeightDataActivity.this, BodyActivity.class);
                RecordWeightDataActivity.this.startActivity(intent);
            }
        });
        recordWeightDataActivityFooterSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawRecordWeightDataActivityBodyNoteFieldContent = recordWeightDataActivityBodyNoteField.getText();
                String recordWeightDataActivityBodyNoteFieldContent = rawRecordWeightDataActivityBodyNoteFieldContent.toString();
                CharSequence rawRecordWeightDataActivityBodyFatFieldContent = recordWeightDataActivityBodyFatField.getText();
                String recordWeightDataActivityBodyFatFieldContent = rawRecordWeightDataActivityBodyFatFieldContent.toString();
                int parsedRecordWeightDataActivityBodyFatFieldContent = 0;
                boolean isHaveFat = recordWeightDataActivityBodyFatFieldContent.length() >= 1;
                if (isHaveFat) {
                    parsedRecordWeightDataActivityBodyFatFieldContent = Integer.valueOf(recordWeightDataActivityBodyFatFieldContent);
                }
                CharSequence rawRecordWeightDataActivityBodyMusculatureFieldContent = recordWeightDataActivityBodyMusculatureField.getText();
                String recordWeightDataActivityBodyMusculatureFieldContent = rawRecordWeightDataActivityBodyMusculatureFieldContent.toString();
                int parsedRecordWeightDataActivityBodyMusculatureFieldContent = 0;
                boolean isHaveMusculature = recordWeightDataActivityBodyMusculatureFieldContent.length() >= 1;
                if (isHaveMusculature) {
                    parsedRecordWeightDataActivityBodyMusculatureFieldContent = Integer.valueOf(recordWeightDataActivityBodyMusculatureFieldContent);
                }
                CharSequence rawRecordWeightDataActivityBodyDateLabelContent = recordWeightDataActivityBodyDateLabel.getText();
                String recordWeightDataActivityBodyDateLabelContent = rawRecordWeightDataActivityBodyDateLabelContent.toString();
                String rawSettedWeight = settedRealWeight + "." + settedImaginaryWeight;
                settedWeight = Float.parseFloat(rawSettedWeight);
                db.execSQL("INSERT INTO \"body_records\"(marks, musculature, fat, weight, date) VALUES (\"" + recordWeightDataActivityBodyNoteFieldContent + "\", " + parsedRecordWeightDataActivityBodyMusculatureFieldContent + ", " + parsedRecordWeightDataActivityBodyFatFieldContent + ", " + settedWeight + ", \"" + recordWeightDataActivityBodyDateLabelContent + "\");");
                Intent intent = new Intent(RecordWeightDataActivity.this, BodyActivity.class);
                RecordWeightDataActivity.this.startActivity(intent);
            }
        });
        recordWeightDataActivityBodyWeightWrapRealPartScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int destinationBetweenScrollItems = 105;
                int selectedLabelIndex = ((int) (Math.floor(i1 / destinationBetweenScrollItems)));
                int hoursColumnLabelsCount = recordWeightDataActivityBodyWeightWrapRealPart.getChildCount();
                boolean isChildrenAccess = selectedLabelIndex < hoursColumnLabelsCount;
                if (isChildrenAccess) {
                    for (int hoursLabelIndex = 1; hoursLabelIndex < hoursColumnLabelsCount; hoursLabelIndex++) {
                        TextView currentLabel = ((TextView)(recordWeightDataActivityBodyWeightWrapRealPart.getChildAt(hoursLabelIndex)));
                        Typeface labelTypeface = Typeface.DEFAULT;
                        currentLabel.setTypeface(labelTypeface, Typeface.NORMAL);
                    }
                    TextView selectedLabel = ((TextView)(recordWeightDataActivityBodyWeightWrapRealPart.getChildAt(selectedLabelIndex)));
                    Typeface labelTypeface = selectedLabel.getTypeface();
                    selectedLabel.setTypeface(labelTypeface, Typeface.BOLD);
                    CharSequence rawSelectLabelContent = selectedLabel.getText();
                    String selectLabelContent = rawSelectLabelContent.toString();
                    settedRealWeight = selectLabelContent;
                }
            }
        });
        recordWeightDataActivityBodyWeightWrapRealPartScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int destinationBetweenScrollItems = 105;
                int selectedLabelIndex = ((int) (Math.floor(i1 / destinationBetweenScrollItems)));
                int hoursColumnLabelsCount = recordWeightDataActivityBodyWeightWrapImaginaryPart.getChildCount();
                boolean isChildrenAccess = selectedLabelIndex < hoursColumnLabelsCount;
                if (isChildrenAccess) {
                    for (int hoursLabelIndex = 1; hoursLabelIndex < hoursColumnLabelsCount; hoursLabelIndex++) {
                        TextView currentLabel = ((TextView)(recordWeightDataActivityBodyWeightWrapImaginaryPart.getChildAt(hoursLabelIndex)));
                        Typeface labelTypeface = Typeface.DEFAULT;
                        currentLabel.setTypeface(labelTypeface, Typeface.NORMAL);
                    }
                    TextView selectedLabel = ((TextView)(recordWeightDataActivityBodyWeightWrapImaginaryPart.getChildAt(selectedLabelIndex)));
                    Typeface labelTypeface = selectedLabel.getTypeface();
                    selectedLabel.setTypeface(labelTypeface, Typeface.BOLD);
                    CharSequence rawSelectLabelContent = selectedLabel.getText();
                    String selectLabelContent = rawSelectLabelContent.toString();
                    settedImaginaryWeight = selectLabelContent;
                }
            }
        });
        recordWeightDataActivityBodyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(RecordWeightDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String rawDayOfMonth = String.valueOf(dayOfMonth);
                        String rawMonth = monthsLabels.get(monthOfYear);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(
                            Calendar.YEAR,
                            year
                        );
                        calendar.set(
                            Calendar.MONTH,
                            monthOfYear
                        );
                        calendar.set(
                            Calendar.DAY_OF_MONTH,
                            dayOfMonth
                        );
                        String rawDayOfWeek = weeksLabels.get(calendar.get(Calendar.DAY_OF_WEEK) - 1);
                        String recordDate = rawDayOfWeek + ", " + rawDayOfMonth + " " + rawMonth + " " + rawRecordTime;
                        recordWeightDataActivityBodyDateLabel.setText(recordDate);
                    }
                }, year, month, day);
                picker.show();
            }
        });
    }

}
