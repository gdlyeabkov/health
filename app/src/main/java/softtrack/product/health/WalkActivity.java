package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WalkActivity extends AppCompatActivity {

    public ImageButton walkActivityHeaderAsideBackBtn;
    public TextView walkAcitivtyBodyInfoWalkCountLabel;
    public ProgressBar walkAcitivtyBodyInfoWalkProgress;
    public final int countStepsPerKm = 1312;
    public final double countKCalsPerStep = 0.04;
    public TextView walkAcitivtyBodyInfoWalkDestinationKms;
    public TextView walkAcitivtyBodyInfoWalkDestinationCallories;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        walkActivityHeaderAsideBackBtn = findViewById(R.id.walk_activity_header_aside_back_btn);
        walkAcitivtyBodyInfoWalkCountLabel = findViewById(R.id.walk_acitivty_body_info_walk_count_label);
        walkAcitivtyBodyInfoWalkProgress = findViewById(R.id.walk_acitivty_body_info_walk_progress);
        walkAcitivtyBodyInfoWalkDestinationCallories = findViewById(R.id.walk_acitivty_body_info_walk_destination_callories);
        walkAcitivtyBodyInfoWalkDestinationKms = findViewById(R.id.walk_acitivty_body_info_walk_destination_kms);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        walkActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalkActivity.this, MainActivity.class);
                WalkActivity.this.startActivity(intent);
            }
        });
        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        indicatorsCursor.moveToFirst();
        int walk = indicatorsCursor.getInt(2);
        String rawWalk = String.valueOf(walk);
        String rawWalkMsg = rawWalk + " шагов";
        walkAcitivtyBodyInfoWalkCountLabel.setText(rawWalkMsg);
        walkAcitivtyBodyInfoWalkProgress.setProgress(walk);
        double countKms = walk / countStepsPerKm;
        String rawCountKms = Double.toString(countKms);
        walkAcitivtyBodyInfoWalkDestinationKms.setText(rawCountKms);
        double countKCals = countKCalsPerStep * walk;
        String rawCountKCals = Double.toString(countKCals);
        walkAcitivtyBodyInfoWalkDestinationCallories.setText(rawCountKCals);
    }

}
