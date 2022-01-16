import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{
    private static final int numRow = 50;
    private static final int numCol = 75;
    private static final int width = 900;
    private static final int height = 600;
    private static final int factor = 12;
    Button block, beehive, blinker, clear, glider, toad, customize;
    private static final Canvas canvas = new Canvas(900, 600);
    Cell[][] grid = new Cell[75][50];
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Timeline timeline;
    private StatusBar statusBar;
    private Boolean isCustomize = false;
    private int countFrame = 0;

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setWidth(900);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);

        GridPane gridPane = new GridPane();

        statusBar = new StatusBar();
        statusBar.StatusBarView(gridPane);

        // create Buttons
        createButton(gridPane);

        // clear button
        clear.setOnAction(event -> {
                timeline.play();
                isCustomize = false;
                emptyGrid();
        });

        //block button
        block.setOnAction(event -> {
            if(isCustomize) timeline.play();
            isCustomize = false;
            canvas.setOnMouseClicked(this::blockButton);
        });

        // beehive button
        beehive.setOnAction(event -> {
            if(isCustomize) timeline.play();
            isCustomize = false;
            canvas.setOnMouseClicked(this::beehiveButton);
        });

        // blinker button
        blinker.setOnAction(event -> {
            if(isCustomize) timeline.play();
            isCustomize = false;
            canvas.setOnMouseClicked(this::blinkerButton);
        });

        // toad button
        toad.setOnAction(event -> {
            if(isCustomize) timeline.play();
            isCustomize = false;
            canvas.setOnMouseClicked(this::toadButton);
        });

       // glider button
        glider.setOnAction(event -> {
            if(isCustomize) timeline.play();
            isCustomize = false;
            canvas.setOnMouseClicked(this::gliderButton);
        });

        // customize button
        customize.setOnAction(event -> {
            timeline.pause();
            isCustomize = true;
            canvas.setOnMouseClicked(this::customizeButton);
        });

        // Draw grid
        emptyGrid();

        EventHandler<ActionEvent> eventHandler = event -> {
            countFrame += timeline.getRate();
            statusBar.statusFrame(timeline, countFrame);
            drawChange();
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), eventHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        gridPane.add(canvas, 0,1);

        Scene scene = new Scene(gridPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void blockButton(MouseEvent mouseEvent){

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int factor = (width/numCol);
        int i = (int)x / factor;
        int j = (int)y / factor;
        drawSquare(i, j, Color.BLACK);
        drawSquare(i+1, j, Color.BLACK);
        drawSquare(i, j+1, Color.BLACK);
        drawSquare(i+1, j+1, Color.BLACK);

        if(!isEdge(i,j)){
            grid[i][j].setLive(true);
        }

        if(!isEdge(i+1,j)){
            grid[i+1][j].setLive(true);
        }

        if(!isEdge(i,j+1)){
            grid[i][j+1].setLive(true);
        }

        if(!isEdge(i+1, j+1)){
            grid[i+1][j+1].setLive(true);
        }

        updateNeighbor();
        statusBar.statusCreated(i,j,"block");
    }

    private void beehiveButton(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int factor = (width/numCol);
        int i = (int)x / factor;
        int j = (int)y / factor;
        drawSquare(i+1, j, Color.BLACK);
        drawSquare(i+2, j, Color.BLACK);
        drawSquare(i, j+1, Color.BLACK);
        drawSquare(i+3, j+1, Color.BLACK);
        drawSquare(i+1, j+2, Color.BLACK);
        drawSquare(i+2, j+2, Color.BLACK);

        if(!isEdge(i+1,j)){
            grid[i+1][j].setLive(true);
        }

        if(!isEdge(i+2,j)){
            grid[i+2][j].setLive(true);
        }
        if(!isEdge(i,j+1)){
            grid[i][j+1].setLive(true);
        }

        if(!isEdge(i+3,j+1)){
            grid[i+3][j+1].setLive(true);
        }

        if(!isEdge(i+1, j+2)){
            grid[i+1][j+2].setLive(true);
        }

        if(!isEdge(i+2, j+2)){
            grid[i+2][j+2].setLive(true);
        }

        updateNeighbor();

        statusBar.statusCreated(i,j,"beehive");
    }

    private void blinkerButton(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int factor = (width/numCol);
        int i = (int)x / factor;
        int j = (int)y / factor;
        drawSquare(i, j+1, Color.BLACK);
        drawSquare(i+1, j+1, Color.BLACK);
        drawSquare(i+2, j+1, Color.BLACK);

        if(!isEdge(i,j+1)){
            grid[i][j+1].setLive(true);
        }

        if(!isEdge(i+1,j+1)){
            grid[i+1][j+1].setLive(true);
        }

        if(!isEdge(i+2,j+1)){
            grid[i+2][j+1].setLive(true);
        }

        updateNeighbor();
        statusBar.statusCreated(i,j,"blinker");
    }

    private void toadButton(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int factor = (width/numCol);
        int i = (int)x / factor;
        int j = (int)y / factor;
        drawSquare(i+1, j, Color.BLACK);
        drawSquare(i+2, j, Color.BLACK);
        drawSquare(i+3, j, Color.BLACK);
        drawSquare(i, j+1, Color.BLACK);
        drawSquare(i+1, j+1, Color.BLACK);
        drawSquare(i+2, j+1, Color.BLACK);

        if(!isEdge(i+1,j)){
            grid[i+1][j].setLive(true);
        }
        if(!isEdge(i+2,j)){
            grid[i+2][j].setLive(true);
        }
        if(!isEdge(i+3,j)){
            grid[i+3][j].setLive(true);
        }
        if(!isEdge(i,j+1)){
            grid[i][j+1].setLive(true);
        }
        if(!isEdge(i+1,j+1)){
            grid[i+1][j+1].setLive(true);
        }
        if(!isEdge(i+2,j+1)){
            grid[i+2][j+1].setLive(true);
        }

        updateNeighbor();
        statusBar.statusCreated(i,j,"toad");
    }

    private void gliderButton(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int factor = (width/numCol);
        int i = (int)x / factor;
        int j = (int)y / factor;
        drawSquare(i+2, j, Color.BLACK);
        drawSquare(i, j+1, Color.BLACK);
        drawSquare(i+2, j+1, Color.BLACK);
        drawSquare(i+1, j+2, Color.BLACK);
        drawSquare(i+2, j+2, Color.BLACK);
        if(!isEdge(i+2,j)){
            grid[i+2][j].setLive(true);
        }
        if(!isEdge(i,j+1)){
            grid[i][j+1].setLive(true);
        }
        if(!isEdge(i+2,j+1)){
            grid[i+2][j+1].setLive(true);
        }
        if(!isEdge(i+1,j+2)){
            grid[i+1][j+2].setLive(true);
        }
        if(!isEdge(i+2,j+2)){
            grid[i+2][j+2].setLive(true);
        }


        updateNeighbor();

        statusBar.statusCreated(i,j,"glider");
    }

    private void customizeButton(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int factor = (width/numCol);
        int i = (int)x / factor;
        int j = (int)y / factor;
        drawSquare(i, j, Color.BLACK);

        if(!isEdge(i,j)){
            grid[i][j].setLive(true);
        }
        updateNeighbor();
        statusBar.statusCreated(i,j,"customize");
    }

    private Boolean isEdge(int x, int y){
        return x < 0 || x > 74 || y < 0 || y > 49;
    }

    private void emptyGrid(){
        for(int i = 0; i < numCol; ++i){
            for(int j = 0; j < numRow; ++j){
                grid[i][j] = new Cell(i*(width/numCol), j*(height/numRow));
                drawSquare(i,j,Color.WHITE);
            }
        }
    }

    private void drawGrid(){
        for(int i = 0; i < numCol; ++i){
            for(int j = 0; j < numRow; ++j){
                if(grid[i][j].getLive()){
                    drawSquare(i,j,Color.BLACK);
                } else {
                    drawSquare(i,j,Color.WHITE);
                }
            }
        }
    }

    private void drawSquare(int x, int y, Paint fill){
        gc.setStroke(Color.SILVER);
        gc.setLineWidth(2);
        gc.strokeRect(x*factor,y*factor, factor ,factor);
        gc.setFill(fill);
        gc.fillRect(x*factor,y*factor, factor, factor);
    }

    private void createButton(GridPane gridPane){
        Image blockImage = new Image("file:ImageSources/block.png");
        Image beehiveImage = new Image("file:ImageSources/beehive.png");
        Image blinkerImage = new Image("file:ImageSources/blinker.png");
        Image toadImage = new Image("file:ImageSources/toad.png");
        Image gliderImage = new Image("file:ImageSources/glider.png");
        Image clearImage = new Image("file:ImageSources/clear.png");
        Image customizeImage = new Image("file:ImageSources/hand.png");

        ImageView blockView = new ImageView(blockImage);
        blockView.setFitWidth(30);
        blockView.setFitHeight(30);

        ImageView beehiveView = new ImageView(beehiveImage);
        beehiveView.setFitWidth(30);
        beehiveView.setFitHeight(30);

        ImageView blinkerView = new ImageView(blinkerImage);
        blinkerView.setFitWidth(30);
        blinkerView.setFitHeight(30);

        ImageView toadView = new ImageView(toadImage);
        toadView.setFitWidth(30);
        toadView.setFitHeight(30);

        ImageView gliderView = new ImageView(gliderImage);
        gliderView.setFitWidth(30);
        gliderView.setFitHeight(30);

        ImageView clearView = new ImageView(clearImage);
        clearView.setFitWidth(20);
        clearView.setFitHeight(20);

        ImageView customizeView = new ImageView(customizeImage);
        customizeView.setFitWidth(20);
        customizeView.setFitHeight(20);

        block = new Button("Block", blockView);
        block.setFont(Font.font("Helvetica"));
        beehive = new Button("Beehive", beehiveView);
        beehive.setFont(Font.font("Helvetica"));
        blinker = new Button("Blinker", blinkerView);
        blinker.setFont(Font.font("Helvetica"));
        toad = new Button("Toad", toadView);
        toad.setFont(Font.font("Helvetica"));
        glider = new Button("Glider", gliderView);
        glider.setFont(Font.font("Helvetica"));
        clear = new Button("Clear", clearView);
        clear.setFont(Font.font("Helvetica"));
        customize = new Button("Customize", customizeView);
        customize.setFont(Font.font("Helvetica"));

        ToolBar toolBar = new ToolBar(block, beehive, new Separator(),blinker, toad, glider, new Separator(), customize, new Separator() ,clear);
        toolBar.setPrefSize(1600, 30);
        gridPane.add(toolBar, 0,0);

    }

    private void drawChange(){
        Cell[][] gridNext = grid;
        for(int i = 0; i < numCol; ++i){
            for(int j = 0; j < numRow; ++j){
                int numNei = grid[i][j].numLiveNeighbor;
                if(grid[i][j].getLive() && numNei < 2){
                    gridNext[i][j].setLive(false);
                } else if(grid[i][j].getLive() && (numNei==2 || numNei ==3 )){
                    gridNext[i][j].setLive(true);
                }else if(grid[i][j].getLive() && numNei > 3){
                    gridNext[i][j].setLive(false);
                } else if(!grid[i][j].getLive() && numNei == 3){
                    gridNext[i][j].setLive(true);
                }
            }
        }
        updateNeighbor();
        grid = gridNext;
        drawGrid();
    }

    private void updateNeighbor(){
        for(int k = 0; k < numCol; ++k){
            for(int l = 0; l < numRow; ++l) {
                grid[k][l].setNumLiveNeighbor(countNeighbor(k,l));
            }
        }
    }

    private int countNeighbor(int x, int y){
        int count = 0;
        if(!isEdge(x-1, y-1) && grid[x - 1][y - 1].getLive()){
            ++count;
        }
        if(!isEdge(x, y-1) && grid[x][y - 1].getLive()){
            ++count;
        }
        if(!isEdge(x+1, y-1) &&grid[x+1][y-1].getLive()){
            ++count;
        }
        if(!isEdge(x-1, y) && grid[x-1][y].getLive()){
            ++count;
        }
        if(!isEdge(x+1, y) && grid[x+1][y].getLive()){
            ++count;
        }
        if(!isEdge(x-1, y+1) && grid[x-1][y+1].getLive()){
            ++count;
        }
        if(!isEdge(x, y+1) && grid[x][y+1].getLive()){
            ++count;
        }
        if(!isEdge(x+1, y+1) && grid[x+1][y+1].getLive()){
            ++count;
        }
        return count;
    }
}

