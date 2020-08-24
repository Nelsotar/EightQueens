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

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Taryn Nelson
 * @since 2020-03-30
 */
public class StartPane extends StackPane{
    /**
     * begin - Button that starts the application.
     */
    Button begin;
    
    /**
     * exit - Button to exit application. 
     */
    Button exit;
    
    /**
     * lblBoardSize - label for boardSize combo box.
     */
    Label lblBoardSize;
    
    /**
     * stage - Application's stage.
     */
    Stage stage;
    
    /**
     * intro - Text object containing introductory text.
     */
    Text intro;
    
    /**
     * boardSize - ComboBox displaying all different possible board sizes.
     */
    ComboBox boardSize;
    
    /**
     * rootPane - application's root pane.
     */
    StackPane rootPane;
    
    /**
     * main - GridPane containing the layout of the start pane.
     */
    GridPane main;
    
    /**
     * introText = String containing intro text.
     */
    String introText = "In chess, a queen can attack horizontally, vertically," 
            + " and diagonally \nfor the entire length of the chessboard. \n\nIn this"
            + " expansion of the classic eight queens problem we find out \nhow many "
            + "ways there are for n queens to peacefully co-exist \n on one "
            + "chessboard of size n x n. Pick a chessboard size and click \nbegin to find out "
            + "all the ways in which peace can be achieved \nbetween these aggressive monarchs.\n";
    
    /**
     * Constructor initializes many of classes' attributes and calls setStartPane().
     * @param rootPane application's root pane
     * @param stage application's stage
     */
    public StartPane(StackPane rootPane, Stage stage){
        main = new GridPane();

        exit = new Button("Exit");
        begin = new Button("Begin");
        intro = new Text(introText);
        lblBoardSize = new Label("Pick a board size: ");
        this.stage = stage;
        
        setBoardCombo();
        
        this.rootPane = rootPane;
        setStartPane();  
        
        main.setId("startPaneMain"); 
        setId("startPaneBg");
    }
    
    /**
     * Places elements of StartPane in grid to create layout.
     */
    final private void setStartPane(){
        main.setMaxSize(400,500);
        
        //setting column widths
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setPrefWidth(150);
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPrefWidth(150);
        ColumnConstraints col4 = new ColumnConstraints();
        col1.setPrefWidth(50);
    
        main.getColumnConstraints().addAll(col1, col2, col3, col4);
        
        main.setVgap(10);
        main.setHgap(10);
        
        Image titleImage = new Image("images/titleImage.png");
        ImageView queens = new ImageView(titleImage);
        
        main.add(queens, 0, 0);
        GridPane.setColumnSpan(queens, 4);
        GridPane.setHalignment(queens, HPos.CENTER);
        
        ImageView title = new ImageView("images/title.png");
        
        main.add(title, 0, 1);
        GridPane.setColumnSpan(title, 4);
        GridPane.setHalignment(title, HPos.CENTER);
        title.setId("title");
        
        main.add(intro, 0, 2);
        GridPane.setColumnSpan(intro, 4);
        intro.setId("intro");
        
        main.add(lblBoardSize,1, 3);
        lblBoardSize.setId("lblBoardSize");
        main.add(boardSize, 2, 3);
        
        main.add(begin, 0, 5);
        GridPane.setColumnSpan(begin, 4);
        GridPane.setHalignment(begin, HPos.CENTER);
        begin.setId("begin");
        
        main.add(exit,0, 6);
        GridPane.setColumnSpan(exit, 4);
        GridPane.setHalignment(exit, HPos.CENTER);
        exit.setId("exit");

        begin.setOnAction(e->{submit();});
        exit.setOnAction(e->{exit();});
        
        getChildren().add(main);
    }
    
    /**
     * Creates combobox for StartPane.
     */
    final private void setBoardCombo(){
        ArrayList<String> list = new ArrayList();
        list.add("4 x 4");
        list.add("5 x 5");
        list.add("6 x 6");
        list.add("7 x 7");
        list.add("8 x 8");
        list.add("9 x 9");
        list.add("10 x 10");
        ObservableList<String> options = FXCollections.observableArrayList(list);
        
        boardSize =  new ComboBox(options);
        boardSize.setValue("8 x 8");
    }
    
    /**
     * Returns board size based on combobox option selected on StartPane.
     * @return board size
     */
    private int getSize(){
        int size = 4;

        if(boardSize.getValue().equals("5 x 5")){
            size = 5;
        }
        else if(boardSize.getValue().equals("6 x 6")){
            size = 6;
        }
        else if(boardSize.getValue().equals("7 x 7")){
            size = 7;
        }
        else if(boardSize.getValue().equals("8 x 8")){
            size = 8;
        }
        else if(boardSize.getValue().equals("9 x 9")){
            size = 9;
        }
        else if(boardSize.getValue().equals("9 x 9")){
            size = 9;
        }
        else if(boardSize.getValue().equals("10 x 10")){
            size = 10;
        }
        
        return size;
    }
    
    /**
     * Resizes the application when board size is greater than 9 * 9.
     */
    private void changeAppSize(){
        int size = getSize();
        if(size == 9){
            stage.setWidth(550);
            stage.setHeight(680);
        }
        else if(size == 10){
            stage.setWidth(600);
            stage.setHeight(725);
        }
    }
    
    /**
     * Finds all solution boards for board size chosen and displays the first one.
     */
    private void submit(){
        //generates all possible solution boards
        PositionGenerator positionGenerator = new PositionGenerator(getSize(), rootPane, stage);
        //set the top and bottom banners of each solution board
        positionGenerator.setTopBanners();
        positionGenerator.setBottomBanners();
        
        //changes the size of the application if needed
        changeAppSize();
        
        //adds the first solution board behind the start pane
        BoardPane pane = positionGenerator.positions.getFirst();
        rootPane.getChildren().add(0, pane);
        
        //disables the start page and solution board until start page fades out 
        //and is removed 
        pane.disableProperty().set(true);
        this.disableProperty().set(true);
        
        FadeTransition fadeStart = new FadeTransition();
        fadeStart.setNode(this);
        fadeStart.setDuration(Duration.seconds(2));
        fadeStart.setFromValue(1);
        fadeStart.setToValue(0);
        fadeStart.play();
        
        fadeStart.onFinishedProperty().set(e->{
            rootPane.getChildren().remove(1);
            pane.disableProperty().set(false);
        });
    }
    
    /**
     * Exits the application.
     */
    private void exit(){
        Platform.exit();
    }
 }
