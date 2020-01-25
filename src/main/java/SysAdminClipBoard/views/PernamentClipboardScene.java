package SysAdminClipBoard.views;

import SysAdminClipBoard.ClipBoardHandler;
import SysAdminClipBoard.ClipBoardHandlerRunner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PernamentClipboardScene extends Scene implements SceneInterface {
    private static PernamentClipboardScene pernamentClipboardScene;
    private static VBox vBox;
    private static BorderPane borderPane = new BorderPane();

    public static PernamentClipboardScene initializeScene(){

        if (pernamentClipboardScene == null) {
            pernamentClipboardScene = new PernamentClipboardScene(borderPane, 225, 250);
        }
        return pernamentClipboardScene;
    }

    private PernamentClipboardScene(Parent borderPane, double width, double height) {
        super(borderPane, width, height);

        // == setting up stackPane and VBox ==
        vBox = new VBox(2);
        vBox.setStyle("-fx-background-color: #"+value2.toString().substring(2) +";-fx-text-inner-color: #"+ value2.toString().substring(2));
        this.borderPane.setCenter(vBox);
        this.borderPane.setStyle("-fx-background-color: #"+value2.toString().substring(2) );

        // == setting up HBox ==
        HBox hBox = new HBox(5);
        hBox.setStyle("-fx-background-color: #"+value2.toString().substring(2));

        javafx.geometry.Insets insets = new javafx.geometry.Insets(10,0,0,0);
        this.borderPane.setBottom(hBox);
        BorderPane.setMargin(hBox, insets);

        // == setting up buttons ==
        Button button1 = new Button("CLIP");
        button1.setFont(Font.font ("Verdana", 8));
        button1.setTextFill(value);

        Button button2 = new Button("NOTES");
        button2.setFont(Font.font ("Verdana", 8));
        button2.setTextFill(value);

        button1.prefWidthProperty().bind(hBox.widthProperty().divide(2));
        button1.setStyle(yellowButtonCollor);
        button1.setOnAction(e -> ClipBoardHandlerRunner.setMainScene(SceneType.scenePrimary));

        button2.prefWidthProperty().bind(hBox.widthProperty().divide(2));
        button2.setStyle(yellowButtonCollor);
        button2.setOnAction(e -> ClipBoardHandlerRunner.setMainScene(SceneType.sceneNotes));

        hBox.getChildren().addAll(button1,button2);
    }

    public static void AddNewEntryInVbox(ClipBoardHandler mch, String textBefore, String text){
        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-control-inner-background: #"+value3.toString().substring(2)+";-fx-text-inner-color: #"+ value.toString().substring(2));
        textArea.setText(text);
        textArea.setFont(Font.font ("Verdana", 12));

        var lambdaContext = new Object() {
            boolean firstClickForTextArea = true;
        };

        textArea.setOnMouseClicked(e -> {
            if (lambdaContext.firstClickForTextArea) {
                textArea.clear();
                lambdaContext.firstClickForTextArea = false;
            }
        });

        BorderPane inVBox = new BorderPane();

        inVBox.setCenter(textArea);

        Button button1   = new Button( textBefore );

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(textArea.getText());
                mch.setPaste(textArea.getText());
            }
        });

        button1.setMaxSize(1,1);
        button1.setStyle(yellowButtonCollor);

        inVBox.setLeft(button1);

        if(vBox.getChildren().size() >= 5){
            for(int iConter=5;iConter<vBox.getChildren().size();iConter++){

                int finalIConter = iConter;

                Platform.runLater(() ->
                        vBox.getChildren().remove(finalIConter ) );
            }
        }

        Platform.runLater(() ->  vBox.getChildren().add(0,inVBox) );
    }
}
