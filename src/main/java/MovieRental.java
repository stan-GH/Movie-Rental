import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MovieRental extends Application {
    private Stage primaryStage;
    private Scene mainMenu, rentScene, returnScene;
    private RentView rentView;
    private ReturnView returnView;

    private Customer customer = new Customer();

    private Label mainMenuLbl, userCashLbl;

    private Button rentBtn, returnBtn;

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
        //Contains buttons layout and text input box for user's name and user's cash
        VBox centerDisplayLayout = new VBox();

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

        HBox userInputLayout = new HBox();

        VBox userNameLayout = new VBox();
        VBox userCashLayout = new VBox();

        userNameLayout.setPadding(new Insets(0, 20, 0, 0));

        Label userNameLbl = new Label("Name");
        userCashLbl = new Label("Money");

        TextField userNameTF = new TextField();
        TextField userCashTF = new TextField();

        userNameLayout.getChildren().addAll(userNameLbl, userNameTF);
        userCashLayout.getChildren().addAll(userCashLbl, userCashTF);

        Button submitBtn = new Button("Submit");

        HBox userInfoLayout = new HBox();
        HBox.setMargin(userNameLbl, new Insets(0, 10, 0, 0));
        HBox.setMargin(userCashLbl, new Insets(0, 0, 0, 10));
        userInfoLayout.setAlignment(Pos.CENTER);
        userInfoLayout.setPadding(new Insets(200,0,0,0));


        userInputLayout.getChildren().addAll(userNameLayout, userCashLayout, submitBtn);
        userInputLayout.setAlignment(Pos.CENTER);
        userInputLayout.setPadding(new Insets(200,0,0,0));

        rentView.getReturnBtn().setOnAction(e -> primaryStage.setScene(mainMenu));
        returnView.getReturnBtn().setOnAction(e -> primaryStage.setScene(mainMenu));

        HBox buttonLayout = new HBox(rentBtn, returnBtn);

        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(100, 0, 0, 0));
        HBox.setMargin(rentBtn, new Insets(0, 80, 0, 0));
        HBox.setMargin(returnBtn, new Insets(0, 0, 0, 80));

        centerDisplayLayout.getChildren().addAll(buttonLayout, userInputLayout);

        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (userNameTF.getText().compareTo("") == 0 || userCashTF.getText().compareTo("") == 0){
                    Alert errorMsg = new Alert(Alert.AlertType.ERROR, "Missing information for either user's " +
                            "name or user's cash");
                    errorMsg.show();
                    userNameTF.clear();
                    userCashTF.clear();
                }else {
                    customer.setName(userNameTF.getText());
                    customer.setCashOnHand(Float.parseFloat(userCashTF.getText()));

                    centerDisplayLayout.getChildren().remove(1);

                    userNameLbl.setText("Name: " + userNameTF.getText());
                    userCashLbl.setText("Cash: $" + userCashTF.getText());

                    userNameLbl.setFont(new Font("Times New Roman", 30));
                    userCashLbl.setFont(new Font("Times New Roman", 30));

                    userInfoLayout.getChildren().addAll(userNameLbl, userCashLbl);

                    centerDisplayLayout.getChildren().add(userInfoLayout);
                }
            }
        });

        mainMenuLayout.setTop(titleLayout);
        mainMenuLayout.setCenter(centerDisplayLayout);

        mainMenu = new Scene(mainMenuLayout, screenSize.getWidth(), screenSize.getHeight() - 100);
        rentScene = new Scene(rentView, screenSize.getWidth(), screenSize.getHeight() - 100);
        returnScene = new Scene(returnView, screenSize.getWidth(), screenSize.getHeight() - 100);

        primaryStage.setScene(mainMenu);
        primaryStage.setTitle("Movie Rental");
        primaryStage.show();
    }

    /**
     * Updates the label that shows the amount of money the user has
     */
    public void setCustomerCash(){
        userCashLbl.setText(Float.toString(customer.getCashOnHand()));
    }

}
