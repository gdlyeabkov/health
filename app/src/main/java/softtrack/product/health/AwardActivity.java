package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AwardActivity extends AppCompatActivity {

    public TextView awardActivityHeaderLabel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award);
        initialize();
    }

    public void initialize() {
        awardActivityHeaderLabel = findViewById(R.id.award_activity_header_label);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            String awardsCategory = "";
            awardsCategory = extras.getString("category");
            awardActivityHeaderLabel.setText(awardsCategory);
        }
    }

}
