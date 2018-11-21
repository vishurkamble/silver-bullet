package ca.ciccc.silverBullet;

import ca.ciccc.silverBullet.gameplayEnums.Directions;
import ca.ciccc.silverBullet.gameplayEnums.Orientation;
import ca.ciccc.silverBullet.gameplayEnums.PlayerAction;
import ca.ciccc.silverBullet.gridElements.GridBoard;
import ca.ciccc.silverBullet.gridElements.Move;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Player {
    boolean hasShot;
    int playerNumber;
    Directions facingDirection;
    int gridPositionX;
    int gridPositionY;
    Move targetMove;
    Node playerNode;
    PlayerAction[] playerActions;
    int currentAction = 0;


    public Player(boolean hasShot, int playerNumber, int gridX, int gridY, Directions facingDirection) {
        this.hasShot = hasShot;
        this.playerNumber = playerNumber;
        this.facingDirection = facingDirection;
        playerActions = new PlayerAction[5];
        gridPositionX = gridX;
        gridPositionY = gridY;
        playerNode = new Circle(30, Color.GREEN);
        Image image = new Image("File:src/main/resources/left.png");
        ((Circle) playerNode).setFill(new ImagePattern(image));
        playerNode.setRotate(90);

    }

    public void rotatePlayer(Orientation move){
        switch (facingDirection){
            case NORTH:
                if(move == Orientation.LEFT){
                    facingDirection = Directions.WEST;
                    playerNode.setRotate(0);
                } else {
                    facingDirection = Directions.EAST;
                    playerNode.setRotate(180);
                }
                break;
            case SOUTH:
                if(move == Orientation.LEFT){
                    facingDirection = Directions.EAST;
                    playerNode.setRotate(180);
                } else {
                    facingDirection = Directions.WEST;
                    playerNode.setRotate(0);
                }
                break;
            case EAST:
                if(move == Orientation.LEFT){
                    facingDirection = Directions.NORTH;
                    playerNode.setRotate(90);
                } else {
                    facingDirection = Directions.SOUTH;
                    playerNode.setRotate(-90);
                }
                break;
            case WEST:
                if(move == Orientation.LEFT){
                    facingDirection = Directions.SOUTH;
                    playerNode.setRotate(-90);
                } else {
                    facingDirection = Directions.NORTH;
                    playerNode.setRotate(90);
                }
                break;
        }
    }

    public void Die(){
        playerNode.setDisable(true);
        System.out.println("Player " + playerNumber + " was shot");
    }

    public void addAction(PlayerAction actionToAdd){
        playerActions[currentAction] = actionToAdd;
        currentAction++;
        if(currentAction > 4){
            currentAction = 0;
        }
    }

    public void takeAction(int actionNumber){
        switch (playerActions[actionNumber]){
            case MOVE:
                this.targetMove = GridBoard.instance.tryMovePlayer(this);

                GridBoard.instance.movePlayer(this);
                break;
            case TURN_LEFT:
                this.rotatePlayer(Orientation.LEFT);
                break;
            case TURN_RIGHT:
                this.rotatePlayer(Orientation.RIGHT);
                break;
            case SHOOT:
                GridBoard.instance.tryShoot(this);
                break;
            case WAIT:
                break;
        }
        playerActions[actionNumber] = PlayerAction.NONE;
    }

    public boolean isHasShot() {
        return hasShot;
    }

    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Directions getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Directions facingDirection) {
        this.facingDirection = facingDirection;
    }

    public int getGridPositionX() {
        return gridPositionX;
    }

    public void setGridPositionX(int gridPositionX) {
        this.gridPositionX = gridPositionX;
    }

    public int getGridPositionY() {
        return gridPositionY;
    }

    public void setGridPositionY(int gridPositionY) {
        this.gridPositionY = gridPositionY;
    }

    public Move getTargetMove() {
        return targetMove;
    }

    public void setTargetMove(Move targetMove) {
        this.targetMove = targetMove;
    }

    public Node getPlayerNode() {
        return playerNode;
    }

    public void setPlayerNode(Node playerNode) {
        this.playerNode = playerNode;
    }

    public PlayerAction[] getPlayerActions() {
        return playerActions;
    }

    public void setPlayerActions(PlayerAction[] playerActions) {
        this.playerActions = playerActions;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }
}