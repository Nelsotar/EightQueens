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


import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is for creation of each chess board and of setting the solutions 
 * on the board. 
 * @author Taryn Nelson
 * @since 2020-03-29
 */
public class BoardPane extends BorderPane{
    /**
     * chessBoard - GridPane that displays the actual chessBoard. Buttons and 
     * banners are added by the PositionGenerator class.
     */
    GridPane chessBoard;
    
    /**
     * boardSize - the width and height of the chess board in 50px x 50px squares.
     */
    private int boardSize;
    
    /**
     * positionNum - The solution number of this board.
     */
    private int positionNum;
       
    TextField numPosition;
    
    /**
     * This two argument constructor creates and sets a chessBoard of size size
     * to the center of the BoardPane.
     * @param positionNum The solution number of this board.
     * @param size Size of the board.
     */
    public BoardPane(int positionNum,int size){ 
        boardSize = size;
        chessBoard = new GridPane();
        setChessBoard();
        setCenter(chessBoard);
        chessBoard.setAlignment(Pos.CENTER);
        
        this.positionNum = positionNum;
    }
    
    /**
     * Method returns positionNum attribute;
     * @return Solution number of this board. 
     */
    public int getPositionNum(){
        return positionNum;
    } 
    
    /**
     * Methods creates a chess board out of 50px * 50px black and white squares.
     * Squares are placed on a GridPane which is placed on the BoardPane center.
     */
    final private void setChessBoard(){
        Rectangle lastSquare = new Rectangle(50, 50);
        lastSquare.setFill(Color.BLACK);

        for(int i = 0; i < boardSize; i++){
            if(boardSize % 2 == 0){
                if(Color.BLACK.equals(lastSquare.getFill())){
                    lastSquare = new Rectangle(50,50); 
                    lastSquare.setFill(Color.WHITE);
                }else{
                    lastSquare = new Rectangle(50,50);
                    lastSquare.setFill(Color.BLACK);
                }
            }
            for(int j = 0; j < boardSize; j++){
                if(Color.BLACK.equals(lastSquare.getFill())){
                   lastSquare = new Rectangle(50,50); 
                   lastSquare.setFill(Color.WHITE);
                }else{
                   lastSquare = new Rectangle(50,50);
                   lastSquare.setFill(Color.BLACK);
                }
                chessBoard.add(lastSquare, i, j);
            }  
        }
    }
   
   /**
    * Method places queens on the chessBoard.
    * @param solution 2-d char array containing a solution found for the board.
    */
   public void setSolution(char[][] solution){
       ImageView queen;
       Image queenImage = new Image("images/Queen.png");

        for(int i = 0; i < boardSize ; i++){
            queen = new ImageView(queenImage);
            for(int j = 0; j < boardSize; j++){
                if(solution[i][j] == 'o'){
                   chessBoard.add(queen, i, j);
                   break;
                }
            }     
        }    
    }
}

