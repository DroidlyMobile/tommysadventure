package droidlymobilegames.ca.tommysadventures;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    private int screenWidth, screenHeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenWidth = getDisplayWidth(this);
        screenHeight = getDisplayHeight(this);
        gameView = new GameView(this,screenWidth,screenHeight);
        setContentView(gameView);
        //setContentView(R.layout.activity_main);
    }

    public int getDisplayWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public int getDisplayHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}