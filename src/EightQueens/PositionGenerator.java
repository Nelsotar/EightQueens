/*
 * Copyright (C) 2020 Taryn Nelson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package EightQueens;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *Class finds all possible positions queens may be placed on the board, prints
 * each new solution board, adds top banners, bottom banners, and navigation to
 * board panes.
 * @author Taryn Nelson
 * @since 2020-03-29
 */
public class PositionGenerator {
    /**
     * positions - Circular linked list containing all solutions found for provided 
     * board size.
     */
    CircularLinkedList positions;
    
    /**
     * chessBoard - 2D char array that contains current solution being worked on.
     */
    char[][] chessBoard;
    
    /**
     * boardSize - Size of chessboard to find solutions for.
     */
    int boardSize;
    
    /**
     * solutionCount - number of solutions found for current board size.
     */
    int solutionCount = 0;
    
    /**
     * rootPane - The application's root pane.
     */
    StackPane rootPane;
    
    /**
     * stage - The application's stage.
     */
    Stage stage;
    
    /**
     * numPosition - TextField where user can enter the position number they 
     * wish to jump to.
     */
    TextField numPosition;
    
    /**
     * This constructor initializes and sets a number of the classes' 
     * attributes, it also starts the solveEightQueens method.
     * @param size Board size.
     * @param rootPane Application's root pane.
     * @param stage Application's stage.
     */
    protected PositionGenerator(int size, StackPane rootPane, Stage stage){
        boardSize = size;
        positions = new CircularLinkedList();
        chessBoard = new char[size][size];
        this.rootPane = rootPane;
        this.stage = stage;
        solveEightQueens(0);
    }
    
    /**
     * Creates and sets the top banner for each BoardPane that was generated. 
     * Each banner contains the board size, the position number, and the total positions.
     */
    public void setTopBanners(){
        positions.getFirst();
        VBox bannerTxt;
        StackPane banner;
        ImageView bannerBG;
        for(int i = 0; i < positions.size(); i++){
            banner = new StackPane();
            
            bannerBG = new ImageView("images/tbanner.png"); 
            
            bannerTxt = new VBox();
            bannerTxt.setAlignment(Pos.CENTER);
            Text txtBoardSize = new Text("Board size: " + boardSize + " x " + boardSize);
            Text txtPosition = new Text("Position: " + positions.currentNode.pane.getPositionNum() + " / " + positions.size());
            txtPosition.setId("txtPosition");
            bannerTxt.getChildren().addAll(txtBoardSize, txtPosition);
            banner.getChildren().addAll(bannerBG, bannerTxt);      
            positions.currentNode.pane.setTop(banner);
            positions.currentNode.pane.setAlignment(positions.currentNode.pane.getTop(), Pos.CENTER);
            positions.getNextNode();
        }
    }
    
    /**
     * Creates and sets the bottom banner for each BoardPane that was generated. 
     * Each banner contains a return and exit button, as well as a field for 
     * jumping to specific positions.
     */
    protected void setBottomBanners(){
        positions.getFirst();
        for(int i = 0; i < positions.size(); i++){
            StackPane banner = new StackPane();
            GridPane bannerTxt = new GridPane();
            bannerTxt.setAlignment(Pos.CENTER);
        
            ImageView bannerBG = new ImageView("images/bbanner.png");
            Button btnReturn = new Button("Return");
            btnReturn.setId("return");
            btnReturn.setOnAction(e->{returnStart();});
        
            Button btnExit = new Button("Exit");
            btnExit.setId("exit2");
            btnExit.setOnAction(e->{exit();});
        
            Text txtJump =  new Text("Jump to Position:");
            txtJump.setId("txtJump");
            positions.getCurrentPane().numPosition = new TextField(); 
            positions.getCurrentPane().numPosition.setId("numPosition");
            Button btnJump = new Button("Jump");
            btnJump.setId("jump");
            btnJump.setOnAction(e->{jump();});
        
            bannerTxt.setVgap(10);
            bannerTxt.setHgap(10);
        
            bannerTxt.add(txtJump, 0,0);
            bannerTxt.add(positions.getCurrentPane().numPosition, 1,0);
            bannerTxt.add(btnJump, 2,0);
            bannerTxt.add(btnReturn, 0,1);
            bannerTxt.add(btnExit, 2,1);
        
            banner.getChildren().addAll(bannerBG, bannerTxt);
        
            positions.currentNode.pane.setBottom(banner);
            positions.getNextNode();
        }
        
    }
    
    /**
     * Method checks to see if a valid number was added, then displays the
     * corresponding position number board. Displays error messages to console if
     * incorrect input is entered, or you are already viewing that entered position.
     */
    private void jump(){
        String num = positions.getCurrentPane().numPosition.getText();
        
        if(num.matches("\\d+")){
            int positionNumber = Integer.parseInt(num);
            if(positionNumber != positions.getCurrentPane().getPositionNum() && positionNumber > 0 && positionNumber <= positions.size()){
                BoardPane newPane = positions.getPaneAtPosition(positionNumber);
                positions.getCurrentPane().numPosition.setText("");
                rootPane.getChildren().clear();
                rootPane.getChildren().add(newPane);    
            }
            else if(positionNumber == positions.getCurrentPane().getPositionNum()){
                System.out.println("You are currently viewing this position.");
            }
            else{
                System.out.println("Please enter an integer between 1 and the total number of positions.");
            }
        }else{
            System.out.println("Please enter an integer between 1 and the total number of positions.");
        }
    }
    
    /**
     * Method exits the application.
     */
    private void exit(){
        Platform.exit();
    }
    
    /**
     * Returns to the StartPane.
     */
    private void returnStart(){
        //some board sizes require a larger screen, this retruns app to regular 
        //size
        stage.setHeight(600);
        stage.setWidth(500);
        rootPane.getChildren().clear();
        rootPane.getChildren().add(new StartPane(rootPane, stage));
    }
    
    /**
     * Displays the next BoardPane in positions.
     */
    private void nextPane(){
        rootPane.getChildren().clear();
        rootPane.getChildren().add(positions.getNextPane());
    }
    
    /**
     * Displays the previous BoardPane in positions.
     */
    private void previousPane(){
        rootPane.getChildren().clear();
        rootPane.getChildren().add(positions.getPreviousPane());
    }
    
    /**
     * Clears the chessBoard array from given column on.
     * @param column column to start from
     */
    private void makeBoard(int column){
        for(int i = column; i < chessBoard.length; i++){
            for(int j = 0; j < chessBoard.length; j++){
                chessBoard[i][j] = ' ';
            }
        }
    }
    
    /**
     * Finds positions that the queens can be placed in through recursion. When 
     * a valid solution is found, printBoard() is called.
     * @param column column to place queen on
     */
    private void solveEightQueens(int column){
        for(int i = column; i < chessBoard.length; i++){   
            for(int j = 0; j <= chessBoard[i].length; j++){
                makeBoard(column);
                if(j == chessBoard.length){
                    return;
                }
                else if(!isForbidden(i,j)){
                    
                    chessBoard[i][j] = 'o';
                    
                    if(column == chessBoard[i].length - 1){
                        printBoard();
                    }
                    
                    solveEightQueens(column + 1);
                }
            }
        }
        
    }
    
    /**
     * Method determines if a certain position is in danger of attack from 
     * previously placed queens.
     * @param column column the queen can potentially be placed in.
     * @param row row the queen can potentially be placed in.
     * @return 
     */
    private boolean isForbidden(int column, int row){
        boolean isForbidden = false;
        int tempRow = row;
        int tempColumn = column;
        int tempRow2 = row;
        int tempColumn2 = column;
        
        for(int i = 0; i < chessBoard.length;i++){
            if(chessBoard[column][i] == 'o' || chessBoard[i][row] == 'o'){
                isForbidden = true;
                break;
            }
            
            tempRow++;
            tempColumn++;
            tempRow2--;
            tempColumn2--;
            
            if(tempRow < chessBoard.length && tempColumn < chessBoard.length && chessBoard[tempColumn][tempRow] == 'o'){
                isForbidden = true;
                break;
            }
            if(tempRow2 >= 0 && tempColumn2 >= 0 && chessBoard[tempColumn2][tempRow2] == 'o'){
                isForbidden = true;
                break;
            }
            if(tempRow2 >= 0 && tempColumn < chessBoard.length && chessBoard[tempColumn][tempRow2] == 'o'){
                isForbidden = true;
                break;
            }
            if(tempRow < chessBoard.length && tempColumn2 >= 0 && chessBoard[tempColumn2][tempRow] == 'o'){
                isForbidden = true;
                break;
            }
        }
        return isForbidden;
    }
    
    /**
     * Method creates a BoardPane and sets a valid solution on it, and adds 
     * navigation buttons.
     */
    public void printBoard(){
        solutionCount++;
        BoardPane pane = new BoardPane(solutionCount, boardSize);
        pane.setSolution(chessBoard);
        
        Image leftImage = new Image("images/left2.png");
        Image leftImageClicked = new Image("images/leftclicked.png");
        Image rightImage = new Image("images/right2.png");
        Image rightImageClicked = new Image("images/rightclicked.png");
        
        ImageView left = new ImageView(leftImage);
        ImageView right = new ImageView(rightImage);
        
        Button nextPane = new Button("", right);
        Button prevPane = new Button("", left);
        nextPane.setId("nextPane");
        prevPane.setId("prevPane");
        
        nextPane.setOnMousePressed(e->{
            right.setImage(rightImageClicked);
        });
        nextPane.setOnMouseReleased(e->{
            right.setImage(rightImage);
            nextPane();
        });
        
        prevPane.setOnMousePressed(e->{
            left.setImage(leftImageClicked);
        });
        prevPane.setOnMouseReleased(e->{
            left.setImage(leftImage);
            previousPane();
        });

        pane.setLeft(prevPane);
        pane.setRight(nextPane);
        BoardPane.setAlignment(pane.getLeft(), Pos.CENTER);
        BoardPane.setAlignment(pane.getRight(), Pos.CENTER);
        positions.add(pane);
    }
}
