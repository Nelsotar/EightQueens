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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *Application class for EightQueens package.
 * @author Taryn Nelson
 * @since 2020-03-29
 */
public class EightQueensApplication extends Application{
    
    /**
     * Overidden start method, starts the application running.
     * @param stage the applications stage.
     */
    @Override
    public void start(Stage stage){    
        StackPane rootPane = new StackPane();
        
        StartPane startPane = new StartPane(rootPane, stage);
        
        rootPane.getChildren().add(startPane);
        rootPane.getStylesheets().add("css/styles.css");
        
        Scene scene = new Scene(rootPane, 500, 600);
        stage.setScene(scene);
        //no one is allowed to mess with my pretty layout, sorry
        stage.setResizable(false);
        Image queenIcon = new Image("images/Queen.png");
        stage.getIcons().add(queenIcon);
        stage.setTitle("The Eight Queens Problem");
        stage.show();
    
    }
    
    /**
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);    
    }
}
