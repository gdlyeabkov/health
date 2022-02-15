package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AwardActivity extends AppCompatActivity {

    public ImageButton awardActivityHeaderBackBtn;
    public String category;
    public String type;
    public String name;
    public String record;
    public TextView awardActivityAwardName;
    public TextView awardActivityAwardRecord;
    public TextView awardActivityAwardTypeLabel;
    public ImageButton awardActivityHeaderShareBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award);
        initialize();
    }

    public void initialize() {
        awardActivityHeaderBackBtn = findViewById(R.id.award_activity_header_back_btn);
        awardActivityAwardName = findViewById(R.id.award_activity_award_name);
        awardActivityAwardRecord = findViewById(R.id.award_activity_award_record);
        awardActivityAwardTypeLabel = findViewById(R.id.award_activity_type_label);
        awardActivityHeaderShareBtn = findViewById(R.id.award_activity_header_share_btn);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            category = extras.getString("category");
            type = extras.getString("type");
            name = extras.getString("name");
            record = extras.getString("record");
            awardActivityAwardTypeLabel.setText(type);
            awardActivityAwardName.setText(name);
            awardActivityAwardRecord.setText(record);
        }
        awardActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AwardActivity.this, AwardsCategoryActivity.class);
                intent.putExtra("category", category);
                AwardActivity.this.startActivity(intent);
            }
        });
        awardActivityHeaderShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_MEDIA_SHARED);
                startActivity(Intent.createChooser(share,"Share image"));
            }
        });
    }

}
