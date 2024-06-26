package com.game.hoppenhelm;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class Playground {
    private double widthRec;
    public Circle redCircle1 = new Circle();
    public Circle redCircle2 = new Circle();
    public Circle redCircle3 = new Circle();
    public Circle redCircle4 = new Circle();
    //Making a circle to show the number of lives in the game
    Playground(int widthScreen , int heightScreen , Group root) {
        redCircle1.setFill(Color.GREEN);
        redCircle1.setRadius(20);
        redCircle1.setCenterX(200);
        redCircle1.setCenterY(50);
        root.getChildren().add(redCircle1);
        redCircle2.setFill(Color.GREEN);
        redCircle2.setRadius(20);
        redCircle2.setCenterX(150);
        redCircle2.setCenterY(50);
        root.getChildren().add(redCircle2);
        redCircle3.setFill(Color.GREEN);
        redCircle3.setRadius(20);
        redCircle3.setCenterX(100);
        redCircle3.setCenterY(50);
        root.getChildren().add(redCircle3);
        redCircle4.setFill(Color.GREEN);
        redCircle4.setRadius(20);
        redCircle4.setCenterX(50);
        redCircle4.setCenterY(50);
        //Adjusting the width of the rectangle relative to the height of the screen
        root.getChildren().add(redCircle4);
        this.widthRec = heightScreen / 20.0;
        //Construction of playground rectangles
        for (int i = 0 ; i < this.widthRec ; i++){
            if( i % 2 == 0) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.GRAY);
                rectangle.setWidth(150);
                rectangle.setHeight(15);
                rectangle.setY(widthScreen - 15);
                rectangle.setX(heightScreen - (i * 100));
                root.getChildren().add(rectangle);
            } else {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.BLACK);
                rectangle.setWidth(150);
                rectangle.setHeight(15);
                rectangle.setY(widthScreen - 15);
                rectangle.setX(heightScreen - (i * 100));
                root.getChildren().add(rectangle);
            }
        }
        //Change color for playground rectangles
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                changeBlocksColor(root);
            }
        });
        root.requestFocus();
    }
    private void changeBlocksColor(Group root) {
        for (Node node : root.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) node;
                Color currentColor = (Color) rectangle.getFill();
                if (currentColor == Color.BLACK) {
                    rectangle.setFill(Color.BLUE);
                } else if (currentColor == Color.BLUE) {
                    rectangle.setFill(Color.BLACK);
                }
            }
        }
    }
    //Print the first child in the group root
    public void movePlayground(Group root){
        System.out.println(root.getChildren().get(0).getClass().getName() );
    }
}