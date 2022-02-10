package softtrack.product.health;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class RecordSleepDataActivity extends AppCompatActivity {

    public Button recordSleepDataActivityFooterSaveBtn;
    public Button recordSleepDataActivityFooterCancelBtn;
    public LinearLayout recordSleepDataActivityBodyDate;
    public TextView recordWeightDataActivityBodyDateLabel;
    public String rawRecordTime;
    public ArrayList<String> monthsLabels;
    public ArrayList<String> weeksLabels;
    public String oneCharPrefix = "0";
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_sleep_data);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        recordSleepDataActivityFooterCancelBtn = findViewById(R.id.record_sleep_data_activity_footer_cancel_btn);
        recordSleepDataActivityFooterSaveBtn = findViewById(R.id.record_sleep_data_activity_footer_save_btn);
        recordSleepDataActivityBodyDate = findViewById(R.id.record_sleep_data_activity_body_date);
        recordWeightDataActivityBodyDateLabel = findViewById(R.id.record_sleep_data_activity_body_date_label);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

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
        String rawRecordTimeStamp = rawRecordDate + " " + rawRecordTime;
        recordWeightDataActivityBodyDateLabel.setText(rawRecordTimeStamp);
        recordSleepDataActivityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordSleepDataActivity.this, SleepActivity.class);
                RecordSleepDataActivity.this.startActivity(intent);
            }
        });
        recordSleepDataActivityFooterSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawRecordWeightDataActivityBodyDateLabelContent = recordWeightDataActivityBodyDateLabel.getText();
                String recordWeightDataActivityBodyDateLabelContent = rawRecordWeightDataActivityBodyDateLabelContent.toString();
                db.execSQL("INSERT INTO \"sleep_records\"(hours, minutes, date) VALUES (\"" + 0 + "\", \"" + 0 + "\", \"" + recordWeightDataActivityBodyDateLabelContent + "\");");
                Intent intent = new Intent(RecordSleepDataActivity.this, SleepActivity.class);
                RecordSleepDataActivity.this.startActivity(intent);
            }
        });
        recordSleepDataActivityBodyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(RecordSleepDataActivity.this, new DatePickerDialog.OnDateSetListener() {
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
