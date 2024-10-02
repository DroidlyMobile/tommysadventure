package droidlymobilegames.ca.tommysadventures;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    private RelativeLayout mainlayout;
    private int screenWidth, screenHeight = 0;
    public ArrayList<String> buttonlist = new ArrayList<>();
    public ArrayList<String> buttonpointerlist = new ArrayList<>();
    private RelativeLayout buttonup,buttondown,buttonleft,buttonright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_ui_layout);
        mainlayout = findViewById(R.id.main_layout);
        buttondown = findViewById(R.id.button_down);
        buttonup = findViewById(R.id.button_up);
        buttonright = findViewById(R.id.button_right);
        buttonleft = findViewById(R.id.button_left);
        screenWidth = getDisplayWidth(this);
        screenHeight = getDisplayHeight(this);
        gameView = new GameView(this,screenWidth,screenHeight);
        mainlayout.addView(gameView);
    }

    public int getDisplayWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public int getDisplayHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}