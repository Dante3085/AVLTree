package com.Main;

import com.AVLTree.AVLTree;
import com.AVLTree.ui.AVLNodeView;
import com.AVLTree.ui.AVLTreeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXMain extends Application
{
    public static Stage mainWindow;
    Pane root;
    Scene scene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainWindow = primaryStage;
        root = new Pane();
        scene = new Scene(root);
        mainWindow.setScene(scene);
        mainWindow.setTitle("AVLTreeView - Visualization");
        mainWindow.setFullScreen(true);
        mainWindow.show();

        AVLTreeView<Integer> a = new AVLTreeView<>("name");
        a.add(1);
        a.add(2);
        a.add(3);
        root.getChildren().add(a);
    }
}
