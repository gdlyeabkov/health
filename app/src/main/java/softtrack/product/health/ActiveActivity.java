package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActiveActivity extends AppCompatActivity {

    public ImageButton activeActivityHeaderAsideBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        initialize();
    }

    public void initialize() {
        activeActivityHeaderAsideBackBtn = findViewById(R.id.active_activity_header_aside_back_btn);
        activeActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActiveActivity.this, MainActivity.class);
                ActiveActivity.this.startActivity(intent);
            }
        });
    }

}
