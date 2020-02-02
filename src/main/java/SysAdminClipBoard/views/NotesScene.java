package SysAdminClipBoard.views;

import SysAdminClipBoard.ClipBoardHandler;
import SysAdminClipBoard.ClipBoardHandlerRunner;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class NotesScene extends SceneAbstract {
    private static NotesScene notesScene;
    private static StackPane stackPane;
    private static BorderPane borderPane = new BorderPane();

    public static NotesScene initializeScene(){
        if (notesScene == null) {
            notesScene = new NotesScene(borderPane, 225, 250);
        }
        return notesScene;
    }

    private NotesScene(Parent borderPane, double width, double height) {
        super(borderPane, width, height);

        // == setting up stackPane and borderPane ==
        stackPane = new StackPane();
        this.borderPane.setCenter(stackPane);
        this.borderPane.setStyle("-fx-background-color: #"+value2.toString().substring(2) );

        // == setting up hBox ==
        HBox hBox = new HBox(5);
        hBox.setStyle("-fx-background-color: #"+value2.toString().substring(2));
        javafx.geometry.Insets insets = new javafx.geometry.Insets(10,0,0,0);
        this.borderPane.setBottom(hBox);
        BorderPane.setMargin(hBox, insets);

        // == setting up buttons
        Button button1 = new Button("CLIP");
        button1.setFont(Font.font ("Verdana", 8));
        button1.setTextFill(value);

        Button button2 = new Button("CONST");
        button2.setFont(Font.font ("Verdana", 8));
        button2.setTextFill(value);

        button1.prefWidthProperty().bind(hBox.widthProperty().divide(2));
        button1.setStyle(yellowButtonCollor);
        button1.setOnAction(e -> ClipBoardHandlerRunner.setMainScene(SceneType.scenePrimary));

        button2.prefWidthProperty().bind(hBox.widthProperty().divide(2));
        button2.setStyle(yellowButtonCollor);
        button2.setOnAction(e -> ClipBoardHandlerRunner.setMainScene(SceneType.sceneConst));

        hBox.getChildren().addAll(button1,button2);
    }

    // == main logic ==
    public static void AddNewEntryInVbox(ClipBoardHandler clipBoardHandler, String textBefore, String text){
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

        Platform.runLater(() ->  stackPane.getChildren().add(textArea) );
    }
}
