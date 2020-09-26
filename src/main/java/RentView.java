import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class RentView extends BorderPane {
    private MovieTile[][] movieSquares; //Contains buttons that the user can press to rent a movie
    private Button returnBtn;

    public RentView(){
        HBox titleLayout = new HBox();
        GridPane movieSquareLayout = new GridPane();
        movieSquares = new MovieTile[3][3];
        returnBtn = new Button("Return to Main Menu");
        Label rentLbl = new Label("Rent");
        rentLbl.setFont(new Font(30));

        movieSquareLayout.setHgap(100);
        movieSquareLayout.setVgap(100);
        movieSquareLayout.setAlignment(Pos.CENTER);

        titleLayout.getChildren().add(rentLbl);
        titleLayout.setAlignment(Pos.CENTER);

        int movieCounter = 0;
        // Creates movie squares
        for (int i = 0; i < movieSquares.length; i++){
            for (int j = 0; j < movieSquares[i].length; j++){
                movieSquares[i][j] = new MovieTile("Movie " + movieCounter++, "0.0");
                movieSquareLayout.add(movieSquares[i][j], i, j);
            }
        }

        VBox filterLayout = new VBox();
        Label filterLbl = new Label("Filter");
        filterLbl.setFont(new Font(20));

        // Creates filter bar
        ToggleGroup filterToggles = new ToggleGroup();
        RadioButton alphabeticalToggle = new RadioButton("Alphabetical");
        RadioButton notOwnedToggle = new RadioButton("Not Owned");
        RadioButton popularityToggle = new RadioButton("Popularity");

        filterToggles.getToggles().addAll(alphabeticalToggle, notOwnedToggle, popularityToggle);

        filterLayout.getChildren().addAll(filterLbl, alphabeticalToggle, notOwnedToggle, popularityToggle);

        setTop(titleLayout);
        setCenter(movieSquareLayout);
        setRight(filterLayout);
        setBottom(returnBtn);

    }

    public MovieTile[][] getMovieSquares(){
        return movieSquares;
    }

    public Button getReturnBtn(){
        return returnBtn;
    }

    /**
     * Represents a MovieTile that contains the movie's name and price and the ability to buy the movie
     */
    public static class MovieTile extends StackPane {
        private Rectangle tile;
        private Label movieNameLbl, priceLbl;
        private Button buyBtn;

        public MovieTile(String movieName, String price){
            movieNameLbl = new Label(movieName);
            movieNameLbl.setFont(new Font(20));
            priceLbl = new Label(price);
            buyBtn = new Button("Buy");
            buyBtn.setPrefWidth(100);
            buyBtn.setFont(new Font(15));

            tile = new Rectangle(200, 200);
            tile.setFill(Color.DARKSEAGREEN);

            VBox.setMargin(movieNameLbl, new Insets(0, 0, 20, 0));
            VBox.setMargin(priceLbl, new Insets(0, 0, 20, 0));
            VBox.setMargin(buyBtn, new Insets(0, 0, 20, 0));
            VBox tileLayout = new VBox(movieNameLbl, priceLbl, buyBtn);

            tileLayout.setAlignment(Pos.CENTER);
            getChildren().addAll(tile, tileLayout);
        }

        public Button getBuyBtn(){
            return buyBtn;
        }

        public void setPriceLbl(String price){
            priceLbl.setText(price);
        }

    }
}
