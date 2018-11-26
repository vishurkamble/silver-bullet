package ca.ciccc.silverBullet.gameBoard;

import ca.ciccc.silverBullet.playerElements.Player;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TimerDisplay extends HBox {
    double currentTime;
    Text timerText;
    List<Circle> playerImages;

    public TimerDisplay(List<Player> players) {

        playerImages = new ArrayList<>();
        for (Player p : players){
            playerImages.add((Circle) p.getPlayerNode());
        }

        this.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, new CornerRadii(4), Insets.EMPTY)));
        this.setPadding(new Insets(10));
        currentTime = 0;
        timerText = new Text();
        timerText.setText(doubleToTime(currentTime));
        timerText.setFill(Color.WHITE);
        timerText.setFont(
                Font.font("Times New Roman", FontWeight.BOLD, 20)
        );
        for(int i = 0; i<playerImages.size(); i++){
            if(i == playerImages.size() / 2){
                getChildren().add(timerText);
            }
            Circle imageToAdd = new Circle(20,((Circle)playerImages.get(i)).getFill());
            playerImages.set(i, imageToAdd);
            getChildren().add(imageToAdd);

        }
    }

    private String doubleToTime(double d){

        int minutes =(int) d / (60);
        int seconds = (int)d % 60;
        String str = String.format("%d:%02d", minutes, seconds);
        return str;

    }

    public void timerUpdate(double d){
        timerText.setText(doubleToTime(d));
    }

    public void setHighlight(int playerNumber){
        Circle imageToCheck = playerImages.get(playerNumber);
        ColorAdjust darken = new ColorAdjust();
        darken.setBrightness(-.5);
        ColorAdjust lighten = new ColorAdjust();
        lighten.setBrightness(0);
        imageToCheck.setEffect(lighten);
        playerImages.forEach(i->{
            if(!i.equals(imageToCheck)){
                i.setEffect(darken);
            }
        });
    }

    public void highlightAll(){
        ColorAdjust lighten = new ColorAdjust();
        lighten.setBrightness(0);
        playerImages.forEach(i->{

            i.setEffect(lighten);

        });

    }
}
