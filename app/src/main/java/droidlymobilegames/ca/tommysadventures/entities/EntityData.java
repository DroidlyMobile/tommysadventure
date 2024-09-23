package droidlymobilegames.ca.tommysadventures.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import droidlymobilegames.ca.tommysadventures.GameView;

public class EntityData {

    public GameView gameView = null;
    public int entityWidth = 0;
    public int entityHeight = 0;
    public int screenPosX = 0;//Screen positions are based on the actual position on screen
    public int screenPosY = 0;
    public int posX = 0;//Actual XY positions of world
    public int posY = 0;
    public int walkSpeed = 0;

    public Bitmap defaultWalking = null;
    public Bitmap[] entityWalking = new Bitmap[25]; //Can change this later to whatever number per entity

    public void draw(Canvas canvas){
        if (defaultWalking != null) {
            canvas.drawBitmap(defaultWalking, screenPosX, screenPosY, null);
        }
    }

    public void setupSpritesheet(int drawable, Bitmap[] bitmaps, int tileSize){

        Bitmap dummySpritesheet;
        int currentColumn = 0;
        int currentRow = 0;
        int numberOfSprites = 0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;//Keeps the dimensions of the image we're using
        dummySpritesheet = BitmapFactory.decodeResource(gameView.getResources(),
                drawable,bitmapOptions);
        //Some sprites might be 16x16 some might be 16x32 so we set a tileSize
        int maxColumns = dummySpritesheet.getWidth()/tileSize;
        int maxRows = dummySpritesheet.getHeight()/tileSize;
        while (currentRow < maxRows){
            bitmaps[numberOfSprites] = Bitmap.createScaledBitmap(Bitmap.createBitmap(dummySpritesheet,
                    currentColumn * tileSize,
                    currentRow * tileSize,
                    tileSize,tileSize),
                    entityWidth,entityHeight,false);//No filters added here but you could add one with a Paint object
            currentColumn ++;
            if (currentColumn == maxColumns){
                currentColumn = 0;
                currentRow++;
            }
            numberOfSprites ++;
        }
    }
}
