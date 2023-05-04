package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActiveActivity extends AppCompatActivity {

    public ImageButton activeActivityHeaderAsideBackBtn;
    public ImageButton activeActivityHeaderBtnsAuxBtn;
    public ImageButton activeActivityHeaderBtnsShareBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        initialize();
    }

    public void initialize() {
        activeActivityHeaderAsideBackBtn = findViewById(R.id.active_activity_header_aside_back_btn);
        activeActivityHeaderBtnsAuxBtn = findViewById(R.id.active_activity_header_btns_aux_btn);
        activeActivityHeaderBtnsShareBtn = findViewById(R.id.active_activity_header_btns_share_btn);
        activeActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActiveActivity.this, MainActivity.class);
                ActiveActivity.this.startActivity(intent);
            }
        });
        activeActivityHeaderBtnsShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_MEDIA_SHARED);
                startActivity(Intent.createChooser(share,"Share image"));
            }
        });
    }

}
