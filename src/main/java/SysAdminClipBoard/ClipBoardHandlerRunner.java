package SysAdminClipBoard;

import SysAdminClipBoard.views.PernamentClipboardScene;
import SysAdminClipBoard.views.NotesScene;
import SysAdminClipBoard.views.PrimaryScene;
import SysAdminClipBoard.views.SceneType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//https://coderanch.com/t/377833/java/listen-clipboard
//http://fxexperience.com/2011/12/styling-fx-buttons-with-css/  //button inspiration
// run this way: mvn clean javafx:run

public class ClipBoardHandlerRunner extends Application {
    private static ClipBoardHandler myHandler;
    private static Scene scenePrimary;
    private static Scene sceneConst;
    private static Scene sceneNotes;
    private static Stage stageMain;

    public static void main(String[] args) {
        myHandler = ClipBoardHandler.initilize();
        Application.launch();
    }


    @Override
    public void start(Stage stage) {
        // == stage and scene initializations ==
        stageMain=stage;
        scenePrimary = PrimaryScene.initializeScene();
        sceneConst = PernamentClipboardScene.initializeScene();
        sceneNotes = NotesScene.initializeScene();

         // == text fields initializations ==
        PrimaryScene.AddNewEntryInVbox(myHandler," ","Clip board was initialized");
        for (int i = 0;i<3;i++)
            PernamentClipboardScene.AddNewEntryInVbox(myHandler," ","Insert text");
        NotesScene.AddNewEntryInVbox(myHandler,"","Write your notes here!");

        // == Setting up scene and stage ==
        setMainScene(SceneType.scenePrimary);
        stage.setTitle("Your clipboard");
        stage.initStyle(StageStyle.UTILITY);
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    public static void setMainScene(SceneType st) {

        if (st.equals(SceneType.scenePrimary)) {
            if (scenePrimary != null) {
                stageMain.setScene(scenePrimary);
                System.out.println("Setting "+st.name());
            }
        }

        if (st.equals(SceneType.sceneConst)) {
            if (sceneConst != null) {
                stageMain.setScene(sceneConst);
                System.out.println("Setting "+st.name());
            }
        }

        if (st.equals(SceneType.sceneNotes)) {
            if (sceneNotes != null) {
                stageMain.setScene(sceneNotes);
                System.out.println("Setting "+st.name());
            }
        }
    }
}
