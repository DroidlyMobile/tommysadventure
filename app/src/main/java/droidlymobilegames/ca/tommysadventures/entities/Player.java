package droidlymobilegames.ca.tommysadventures.entities;

import android.graphics.Bitmap;

import droidlymobilegames.ca.tommysadventures.GameView;
import droidlymobilegames.ca.tommysadventures.R;

public class Player extends EntityData{

    public Player(GameView gameView){
        this.gameView = gameView;
        initialize();
    }

    public void initialize(){
        entityWidth = 160;
        entityHeight = 160;
        entityWalking = new Bitmap[12];
        setupSpritesheet(R.drawable.playwalkingspritesheet,entityWalking,16);
        defaultWalking = entityWalking[0];
        walkSpeed = 8;
        screenPosX = gameView.screenWidth/2 - entityWidth/2;
        screenPosY = gameView.screenHeight/2 - entityHeight/2;
    }
}
