package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WalkActivity extends AppCompatActivity {

    public ImageButton walkActivityHeaderAsideBackBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);
        initialize();
    }

    public void initialize() {
        walkActivityHeaderAsideBackBtn = findViewById(R.id.walk_activity_header_aside_back_btn);
        walkActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalkActivity.this, MainActivity.class);
                WalkActivity.this.startActivity(intent);
            }
        });
    }

}
