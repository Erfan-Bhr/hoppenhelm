package com.game.hoppenhelm;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
public class Game extends Application {
    int timeSeconds = 10;
    private Label timelabel;
    protected int Health = 4;
    private int widthScreen, heightScreen;
    static Timer timer= new Timer();
    static TimerTask Task = new MyTimerTask();
    Enemy enemy;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("HoppenHelm MEB");

        this.widthScreen = 720;
        this.heightScreen = 1280;
        Player player = new Player(130, 605, 100, 100);
        enemy = new Enemy(100, 100, 1130, 605);
        Group root = new Group(player.getRectangle(), enemy.getRectangle());
        //Making the main window
        Scene scene = new Scene(root, this.heightScreen, this.widthScreen);
        scene.setFill(Color.WHITE);
        stage.setScene(scene);
        Playground playground = new Playground(this.widthScreen, this.heightScreen, root);

        //Build a timer
        timelabel = new Label();
        timelabel.setLayoutX(widthScreen - 130);
        timelabel.setLayoutY(27);
        timelabel.setStyle("-fx-font-size: 30px;");
        root.getChildren().add(timelabel);
        Task = new MyTimerTask();
        timer.schedule(Task, 10000);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                if(timeSeconds > 0) {
                    timeSeconds = 10;
                    enemy.rectangle.setFill(Color.RED);
                    if (Task != null) {
                        try {
                            playground.movePlayground(root);
                            enemy.moverectangle();
                            if (enemy.getCenterX() == 130) {
                                Health--;
                                enemy.rectangle.setFill(Color.rgb(96, 6, 6));
                                if (Health == 3) {
                                    root.getChildren().remove(playground.redCircle1);
                                    playground.redCircle2.setFill(Color.YELLOW);
                                    playground.redCircle3.setFill(Color.YELLOW);
                                    playground.redCircle4.setFill(Color.YELLOW);
                                } else if (Health == 2) {
                                    root.getChildren().remove(playground.redCircle2);
                                    playground.redCircle3.setFill(Color.ORANGE);
                                    playground.redCircle4.setFill(Color.ORANGE);
                                } else if (Health == 1) {
                                    root.getChildren().remove(playground.redCircle3);
                                    playground.redCircle4.setFill(Color.RED);
                                } else {
                                    root.getChildren().remove(0);
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Game over!");
                                    alert.getButtonTypes().setAll(ButtonType.OK);
                                    alert.showAndWait().ifPresent(response -> {
                                        if (response == ButtonType.OK) {
                                            stage.close();
                                        }
                                    });
                                }
                            }
                        } catch (Exception InterruptedException) {
                            System.out.println((InterruptedException.getMessage()));
                        }
                        Task.cancel();
                    }
                    Task = new MyTimerTask();
                    timer.schedule(Task, 10000);
                }
            } else if (e.getCode() == KeyCode.UP) {
                if (timeSeconds > 0) {
                    timeSeconds = 10;
                    try {
                        enemy.moverectangle();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        enemy.moverectangle();
                        playground.movePlayground(root);
                        if (enemy.getCenterX() == 130) {
                            Health--;
                            enemy.rectangle.setFill(Color.rgb(96, 6, 6));
                            if (Health == 3) {
                                root.getChildren().remove(playground.redCircle1);
                                playground.redCircle2.setFill(Color.YELLOW);
                                playground.redCircle3.setFill(Color.YELLOW);
                                playground.redCircle4.setFill(Color.YELLOW);
                            } else if (Health == 2) {
                                root.getChildren().remove(playground.redCircle2);
                                playground.redCircle3.setFill(Color.ORANGE);
                                playground.redCircle4.setFill(Color.ORANGE);
                            } else if (Health == 1) { // die
                                root.getChildren().remove(playground.redCircle3);
                                playground.redCircle4.setFill(Color.RED);
                            } else {
                                root.getChildren().remove(0);
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setContentText("Game over!");
                                alert.getButtonTypes().setAll(ButtonType.OK);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.OK) {
                                        stage.close();
                                    }
                                });
                            }
                        }
                    } catch (Exception InterruptedException) {
                        System.out.println((InterruptedException.getMessage()));
                    }
                }
            }

            if (enemy.getCenterX() < 0) {
                enemy.set(100, 100, 1130, 605);
                root.getChildren().set(1, enemy.getRectangle());
            }
            //Creating new enemy after passing the Player
        });
        stage.show();
        startTimer();
    }

    private void startTimer() {
        Timer countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    timelabel.setText("Time : " + timeSeconds);
                    if(timeSeconds <= 0) {
                        countdownTimer.cancel();
                    }
                    timeSeconds--;
                });
            }
        }, 0, 1000);
    }

    static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Game over! Your time is up.");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        System.exit(0);
                    }
                });
            });
            timer.cancel();
        }
    }
}