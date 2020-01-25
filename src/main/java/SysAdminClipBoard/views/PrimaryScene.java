package SysAdminClipBoard.views;

import SysAdminClipBoard.ClipBoardHandler;
import SysAdminClipBoard.ClipBoardHandlerRunner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PrimaryScene extends Scene implements SceneInterface {
    private static PrimaryScene primaryScene;
    private static TextField textField;
    private static VBox vBox;
    private static BorderPane borderPane = new BorderPane();
    private static boolean isSavePasteEnabled = false;
    private static boolean isAppendPasteEnabled = false;

    public static PrimaryScene initializeScene(){

        if (primaryScene == null) {
            primaryScene = new PrimaryScene(borderPane, 225, 250);
        }
        return primaryScene;
    }

    private PrimaryScene(Parent borderPane, double width, double height) {
        super(borderPane, width, height);

        // == setting up VBox ==
        vBox = new VBox(2);
        vBox.setStyle("-fx-background-color: #"+value2.toString().substring(2) +";-fx-text-inner-color: #"+ value.toString().substring(2));
        this.borderPane.setCenter(vBox);

        // == setting up HBox ==
        HBox hBox = new HBox(5);
        hBox.setStyle("-fx-background-color: #ecebe9, rgba(0,0,0,0.05), linear-gradient(#dcca8a, #c7a740), linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%), linear-gradient(#f6ebbe, #e6c34d);");
        this.borderPane.setBottom(hBox);

        // == setting up buttons
        Button button1 = new Button("#-OFF");
        button1.setFont(Font.font ("Verdana", 8));
        button1.setTextFill(value);

        Button button2 = new Button("+=-OFF");
        button2.setFont(Font.font ("Verdana", 8));
        button2.setTextFill(value);

        Button button3 = new Button("NOTES");
        button3.setFont(Font.font ("Verdana", 8));
        button3.setTextFill(value);

        Button button4 = new Button("CONST");
        button4.setFont(Font.font ("Verdana", 8));
        button4.setTextFill(value);

        button1.prefWidthProperty().bind(hBox.widthProperty().divide(4));
        button1.setStyle(yellowButtonCollor);

        button2.prefWidthProperty().bind(hBox.widthProperty().divide(4));
        button2.setStyle(yellowButtonCollor);

        button3.prefWidthProperty().bind(hBox.widthProperty().divide(4));
        button3.setStyle(yellowButtonCollor);

        button4.prefWidthProperty().bind(hBox.widthProperty().divide(4));
        button4.setStyle(yellowButtonCollor);

        button1.setOnAction(e -> {
            if (button1.getText().contains("#-OFF") ) {
                button1.setText("#-ON");
                PrimaryScene.isSavePasteEnabled = true;
                PrimaryScene.isAppendPasteEnabled = false;
                button2.setText("+=-OFF");
                button1.setStyle(greenButtonCollor);
            }
            else {
                button1.setText("#-OFF");
                PrimaryScene.isSavePasteEnabled = false;
                button1.setStyle(yellowButtonCollor);
                button2.setStyle(yellowButtonCollor);
            }
        });

        button2.setOnAction(e -> {
            if (button2.getText().contains("+=-OFF") ) {
                button2.setText("+=-ON");
                PrimaryScene.isAppendPasteEnabled = true;
                PrimaryScene.isSavePasteEnabled = false;
                button1.setText("#-OFF");
                button2.setStyle(greenButtonCollor);
            }
            else {
                PrimaryScene.isAppendPasteEnabled = false;
                button2.setText("+=-OFF");
                button1.setStyle(yellowButtonCollor);
                button2.setStyle(yellowButtonCollor);
            }
        });

        button3.setOnAction(e -> {
            PrimaryScene.isSavePasteEnabled = false;
            PrimaryScene.isAppendPasteEnabled = false;
            button1.setText("#-OFF");
            button2.setText("+=-OFF");
            button1.setStyle(yellowButtonCollor);
            button2.setStyle(yellowButtonCollor);
            ClipBoardHandlerRunner.setMainScene(SceneType.sceneNotes);
        });

        button4.setOnAction(e -> {
            PrimaryScene.isSavePasteEnabled = false;
            PrimaryScene.isAppendPasteEnabled = false;
            button1.setText("#-OFF");
            button2.setText("+=-OFF");
            button1.setStyle(yellowButtonCollor);
            button2.setStyle(yellowButtonCollor);
            ClipBoardHandlerRunner.setMainScene(SceneType.sceneConst);
        });

        hBox.getChildren().addAll(button1,button2,button3,button4);
    }

    public static void AddNewEntryInVbox(ClipBoardHandler mch, String i, String s){
        textField = new TextField();
        textField.setStyle("-fx-control-inner-background: #"+value1.toString().substring(2)+";-fx-text-inner-color: #"+ value.toString().substring(2));
        textField.setText(s);
        textField.setFont(Font.font ("Verdana", 12));
        textField.setEditable(false);

        BorderPane inVBox = new BorderPane();
        inVBox.setCenter(textField);
        Button button1   = new Button( i );

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(s);
                mch.setPaste(s);
            }
        });

        button1.setMaxSize(1,1);
        button1.setStyle(yellowButtonCollor);

        inVBox.setLeft(button1);

        if(vBox.getChildren().size() >= 7){
            for(int iConter=7;iConter<vBox.getChildren().size();iConter++){

                int finalIConter = iConter;

                Platform.runLater(() ->
                        vBox.getChildren().remove(finalIConter ) );
            }
        }

        Platform.runLater(() ->  vBox.getChildren().add(0,inVBox) );

    }

    public static boolean getIsSavePasteEnabled() {
        return isSavePasteEnabled;
    }

    public static boolean getIsIsAppendPasteEnabled() {
        return isAppendPasteEnabled;
    }

}
