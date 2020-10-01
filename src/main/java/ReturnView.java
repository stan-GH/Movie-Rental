import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class ReturnView extends BorderPane {
    /*
     * movieTitleContainer contains the names of all movies rented by the user
     * movieDueDateContainer contains the due dates for all the movies rented by the user
     */
    private VBox movieTitleContainer, movieDueDateContainer;
    private HBox movieTitleAndDueDateLayout;

    private Button returnToMainMenuBtn, returnMovieBtn;

    private TextField returnTf;

    public ReturnView(){
        StackPane sp = new StackPane();
        Label returnLbl = new Label("Return");
        Label movieTitleLbl = new Label("Movie Title");
        Label dueDateTitleLbl = new Label("Due Date");
        returnToMainMenuBtn = new Button("Return to Main Menu");
        HBox bottomBPLayout = new HBox(); // Contains the button to return to main menu and the return movie section
        /* Vertical layout that contains movie title label and the vertical layout that will contain movie names owned
        by the user */
        VBox verticalMovieTileLayout = new VBox();
        /* Vertical layout that contains movie date label and the vertical layout that will contain due dates to return
        the movies */
        VBox verticalMovieDateLayout = new VBox();
        VBox spContainer = new VBox(sp);
        /* section that will hold the controls to allow the user to return their movie(s) */
        VBox returnMovieSection = new VBox();
        returnMovieBtn = new Button("Return Movie");

        Label returnInstructionLbl = new Label("Enter the movie name down below to return your rented movie");
        returnTf = new TextField();

        returnMovieSection.getChildren().addAll(returnInstructionLbl, returnTf, returnMovieBtn);

        returnLbl.setFont(new Font(30));
        movieTitleLbl.setFont(new Font(20));
        dueDateTitleLbl.setFont(new Font(20));
        HBox titleLayout = new HBox(returnLbl);
        movieTitleContainer = new VBox();
        movieDueDateContainer = new VBox();
        movieTitleAndDueDateLayout = new HBox();

        verticalMovieTileLayout.getChildren().addAll(movieTitleLbl, movieTitleContainer);
        verticalMovieDateLayout.getChildren().addAll(dueDateTitleLbl, movieDueDateContainer);

        HBox.setMargin(verticalMovieTileLayout, new Insets(0, 100, 0, 0));
        HBox.setMargin(verticalMovieDateLayout, new Insets(0, 0, 0, 30));
        titleLayout.setAlignment(Pos.CENTER);

        Rectangle background = new Rectangle(400, 600);
        background.setFill(Color.AQUAMARINE);

        movieTitleAndDueDateLayout.getChildren().addAll(verticalMovieTileLayout, verticalMovieDateLayout);
        movieTitleAndDueDateLayout.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(background, movieTitleAndDueDateLayout);

        bottomBPLayout.getChildren().addAll(returnToMainMenuBtn, returnMovieSection);
        HBox.setMargin(returnToMainMenuBtn, new Insets(0, 650, 0, 0));

        setTop(titleLayout);
        setCenter(spContainer);
        setBottom(bottomBPLayout);
    }

    public TextField getReturnMovieTextField(){
        return returnTf;
    }

    public void setReturnMovieBtnAction(EventHandler<ActionEvent> e){
        returnMovieBtn.setOnAction(e);
    }

    /**
     * Returns the main menu button
     * @return main menu button
     */
    public Button getReturnToMainMenuBtn(){
        return returnToMainMenuBtn;
    }
    /**
     * Adds a movie and when it's due into the layout
     * @param movieName name of the movie user wants to add
     * @param dueDate the date the movie is due back
     */
    public void addMovie(String movieName, String dueDate){
        Label movieToAddLbl = new Label(movieName);
        Label dueDateLbl = new Label(dueDate);
        movieTitleContainer.getChildren().add(movieToAddLbl);
        movieDueDateContainer.getChildren().add(dueDateLbl);
    }

    /**
     * Removes a movie and its corresponding due date from the layouts in which they're in
     * @param movieName the name of the movie to be removed
     *
     * @return true if movie was successfully removed. Otherwise false is returned
     */
    public boolean removeMovie(String movieName){
        int i = 0;
        for (Node movie : movieTitleContainer.getChildren()){
            if (movie instanceof Label){
                if (((Label) movie).getText().compareTo(movieName) == 0){
                    movieTitleContainer.getChildren().remove(movie);
                    movieDueDateContainer.getChildren().remove(i);
                    return true;
                }
            }

            i++;
        }

        return false;
    }
}
