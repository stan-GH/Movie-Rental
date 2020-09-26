import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MovieRental extends Application {

    private Label mainMenuLbl;
    private Button rentBtn, returnBtn;
    private Stage primaryStage;
    private Scene mainMenu, rentScene, returnScene;
    private RentView rentView;
    private ReturnView returnView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        rentView = new RentView();
        returnView = new ReturnView();
        this.primaryStage = primaryStage;
        BorderPane mainMenuLayout = new BorderPane();
        HBox titleLayout = new HBox();

        mainMenuLbl = new Label("Movie Rental");
        mainMenuLbl.setFont(new Font(30));
        titleLayout.getChildren().add(mainMenuLbl);
        titleLayout.setAlignment(Pos.CENTER);

        rentBtn = new Button("Rent");
        rentBtn.setFont(new Font(20));
        rentBtn.setPrefWidth(200);
        rentBtn.setOnAction(event -> primaryStage.setScene(rentScene));
        rentBtn.setPadding(new Insets(0, 20, 0, 20));

        returnBtn = new Button("Return");
        returnBtn.setFont(new Font(20));
        returnBtn.setPrefWidth(200);
        returnBtn.setOnAction(event -> primaryStage.setScene(returnScene));
        returnBtn.setPadding(new Insets(0, 20, 0, 20));

        rentView.getReturnBtn().setOnAction(e -> primaryStage.setScene(mainMenu));
        returnView.getReturnBtn().setOnAction(e -> primaryStage.setScene(mainMenu));

        HBox buttonLayout = new HBox(rentBtn, returnBtn);
        buttonLayout.setAlignment(Pos.CENTER);
        HBox.setMargin(rentBtn, new Insets(0, 80, 0, 0));
        HBox.setMargin(returnBtn, new Insets(0, 0, 0, 80));
        mainMenuLayout.setTop(titleLayout);
        mainMenuLayout.setCenter(buttonLayout);

        mainMenu = new Scene(mainMenuLayout, screenSize.getWidth(), screenSize.getHeight() - 100);
        rentScene = new Scene(rentView, screenSize.getWidth(), screenSize.getHeight() - 100);
        returnScene = new Scene(returnView, screenSize.getWidth(), screenSize.getHeight() - 100);

        primaryStage.setScene(mainMenu);
        primaryStage.setTitle("Movie Rental");
        primaryStage.show();
    }

}
