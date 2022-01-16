import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import javax.tools.Tool;


public class StatusBar{
    Label label1, frame;
    public void StatusBarView(GridPane parent){
        label1 = new Label("Created");
        label1.setFont(Font.font("Helvetica"));
        frame = new Label("Frame " + "0");
        frame.setFont(Font.font("Helvetica"));

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(label1, Pos.BOTTOM_LEFT);
        stackPane.setAlignment(frame, Pos.BOTTOM_RIGHT);
        stackPane.getChildren().addAll(label1, frame);
        parent.add(stackPane, 0,2);
    }

    public void statusCreated(int x, int y, String type){
        label1.setText("Create " + type + " at " + x + "," + y);
    }

    public void statusFrame(Timeline timeline, int count){
        frame.setText("Frame " + count);
    }
}
