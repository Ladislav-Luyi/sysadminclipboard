package SysAdminClipBoard.views;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public interface SceneInterface {
    String yellowButtonCollor = "-fx-background-color: linear-gradient(#ffd65b, #e68400), linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22), linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); -fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); -fx-text-fill: #395306;";
    String greenButtonCollor = "-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%); -fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); -fx-text-fill: #395306;";
    Paint value = Paint.valueOf("561210"); // brown
    Paint value1 = Paint.valueOf("FFA500"); // orange
    Paint value2 = Paint.valueOf("F3E96B"); // yellow
    Paint value3 = Paint.valueOf("FFFACD"); // yellow
}
