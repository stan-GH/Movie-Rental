import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ReturnView extends BorderPane {
    private VBox movieTitleLayout, movieDueDateLayout;
    private HBox movieTitleAndDueDateLayout;
    private Button returnBtn;

    public ReturnView(){
        StackPane sp = new StackPane();
        Label returnLbl = new Label("Return");
        Label movieTitleLbl = new Label("Movie Title");
        Label dueDateTitleLbl = new Label("Due Date");
        VBox outerMovieTitleLayout = new VBox();
        VBox outerMovieDateLayout = new VBox();
        VBox spContainer = new VBox(sp);
        returnBtn = new Button("Return to Main Menu");

        returnLbl.setFont(new Font(30));
        movieTitleLbl.setFont(new Font(20));
        dueDateTitleLbl.setFont(new Font(20));
        HBox titleLayout = new HBox(returnLbl);
        movieTitleLayout = new VBox();
        movieDueDateLayout = new VBox();
        movieTitleAndDueDateLayout = new HBox();

        outerMovieTitleLayout.getChildren().addAll(movieTitleLbl, movieTitleLayout);
        outerMovieDateLayout.getChildren().addAll(dueDateTitleLbl, movieDueDateLayout);


        HBox.setMargin(outerMovieTitleLayout, new Insets(0, 100, 0, 0));
        HBox.setMargin(outerMovieDateLayout, new Insets(0, 0, 0, 30));
        titleLayout.setAlignment(Pos.CENTER);

        Rectangle background = new Rectangle(400, 600);
        background.setFill(Color.AQUAMARINE);

        movieTitleAndDueDateLayout.getChildren().addAll(outerMovieTitleLayout, outerMovieDateLayout);
        movieTitleAndDueDateLayout.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(background, movieTitleAndDueDateLayout);

        setTop(titleLayout);
        setCenter(spContainer);
        setBottom(returnBtn);
    }

    public Button getReturnBtn(){
        return returnBtn;
    }
    /**
     * Adds a movie and when it's due into the layout
     * @param movieName name of the movie user wants to add
     * @param dueDate the date the movie is due back
     */
    public void addMovie(String movieName, String dueDate){
        Label movieToAddLbl = new Label(movieName);
        Label dueDateLbl = new Label(dueDate);
        movieTitleLayout.getChildren().add(movieToAddLbl);
        movieDueDateLayout.getChildren().add(dueDateLbl);
    }

    /**
     * Removes a movie and its corresponding due date from the layouts in which they're in
     * @param movieName the name of the movie to be removed
     *
     * @return true if movie was successfully removed. Otherwise false is returned
     */
    public boolean removeMovie(String movieName){
        int i = 0;
        for (Node movie : movieTitleLayout.getChildren()){
            if (movie instanceof Label){
                if (((Label) movie).getText().compareTo(movieName) == 0){
                    movieTitleLayout.getChildren().remove(movie);
                    movieDueDateLayout.getChildren().remove(i);
                    return true;
                }
            }

            i++;
        }

        return false;
    }
}
