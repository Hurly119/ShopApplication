package Octagon;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class Alert {
    public void popUpMessage(String Text, String Title){
        Stage primaryStage = new Stage();
        primaryStage.setTitle(Title);
        VBox Alert = new VBox();

        Label messageText = new Label(Text);
        Button closeBtn = new Button("Close");
        Alert.getChildren().addAll(messageText,closeBtn);
        Alert.setAlignment(Pos.CENTER);
        Alert.setSpacing(10);

        closeBtn.setOnAction(e -> {
            primaryStage.close();
        });

        Scene scene = new Scene(Alert,250,100);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
