package droidlymobilegames.ca.tommysadventures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import droidlymobilegames.ca.tommysadventures.entities.Player;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    //public GameThread gameThread;
    public GameLoop gameThread;
    public Paint textpaint;
    public int screenWidth = 0;
    public int screenHeight = 0;
    public Player player;


    public GameView(Context context,int screenWidth,int screenHeight){
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        getHolder().addCallback(this);
        gameThread = new GameLoop(getHolder(),this);
        initializeGameview();
    }
    public void initializeGameview(){

        textpaint = new Paint();
        textpaint.setColor(Color.WHITE);
        textpaint.setTextSize(26);
        player = new Player(this);
    }

    //All update methods placed here
    public void update(){

    }
    //Draw all objects
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (textpaint != null) {
            canvas.drawText("FPS " + gameThread.getAverageFPS(), 100, 100, textpaint);
        }
        player.draw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameThread = new GameLoop(getHolder(),this);
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry){
            try {
                gameThread.join();
                retry = false;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
