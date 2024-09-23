package droidlymobilegames.ca.tommysadventures;

import android.graphics.Canvas;
import android.os.Build;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    private boolean running = false;
    private long targetFPS = 60;
    private long startTime;
    private long timeMS;
    private long waitTime;
    private long totalTime;
    private long frameCount = 0;
    private long targetTime;
    private long averageFPS = 0;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        targetTime = 1000/targetFPS;
    }
    public void setRunning(boolean running){
        this.running = running;
    }
    @Override
    public void run(){
        while (running){
            startTime = System.nanoTime();
            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockHardwareCanvas();
                synchronized (surfaceHolder){
                    gameView.update();
                    gameView.draw(canvas);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            //Calculate FPS
            timeMS = (System.nanoTime() - startTime / 1000000);
            waitTime = targetTime - timeMS;
            try {
                if (waitTime > 0){
                    sleep(waitTime);
                }
            }catch (InterruptedException ie){

            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS){
                averageFPS = 1000/ ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public long getAverageFPS(){
        return averageFPS;
    }
}
