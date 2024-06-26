package com.game.hoppenhelm;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle; // for use in earth of game
import javafx.util.Duration;
import java.lang.Thread;
public class Player extends Parent {
    private int CenterX , CenterY , Width , Height;
    Rectangle rec;
    //Build a player
    public Player(int CenterX , int CenterY , int Width , int Height) {
        this.CenterX = CenterX ;
        this.CenterY = CenterY ;
        this.Width = Width ;
        this.Height = Height;
        this.rec = new Rectangle();
        this.rec.setX(CenterX);
        this.rec.setY(CenterY);
        this.rec.setWidth(Width);
        this.rec.setHeight(Height);
        this.rec.setFill(Color.BLACK);
    }
    public Rectangle getRectangle() {
        return rec;
    }
}