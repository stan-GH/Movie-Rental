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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RentView extends BorderPane {
    private MovieTile[][] movieSquares; //Contains buttons that the user can press to rent a movie
    private Button returnBtn;

    private GridPane movieSquareLayout;

    public RentView(){
        HBox titleLayout = new HBox();
        movieSquareLayout = new GridPane();
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
        Random rand = new Random();
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        // Creates movie squares
        for (int i = 0; i < movieSquares.length; i++){
            for (int j = 0; j < movieSquares[i].length; j++){
                movieSquares[i][j] = new MovieTile("Movie " + movieCounter++, df.format(
                        rand.nextFloat() * 100F));
                movieSquareLayout.add(movieSquares[i][j], j, i);
            }
        }

        VBox filterLayout = new VBox();
        Label filterLbl = new Label("Filter");
        filterLbl.setFont(new Font(20));

        // Creates filter bar
        ToggleGroup filterToggles = new ToggleGroup();
        RadioButton alphabeticalToggle = new RadioButton("Alphabetical");
        RadioButton leastExpensiveToggle = new RadioButton("Least-Expensive");
        RadioButton popularityToggle = new RadioButton("Popularity");

        alphabeticalToggle.setOnAction(e -> setAlphabeticalFilterMode());
        leastExpensiveToggle.setOnAction(e -> setLeastExpensiveFilterMode());

        filterToggles.getToggles().addAll(alphabeticalToggle, leastExpensiveToggle, popularityToggle);

        filterLayout.getChildren().addAll(filterLbl, alphabeticalToggle, leastExpensiveToggle, popularityToggle);

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
     * Returns the requested movie tile
     * @param movieName Name of the movie that is used to find the movie tile
     * @return movieTile if it's contained in the array of movie tiles. Otherwise, null is returned.
     */
    public MovieTile getMovieTile(String movieName){
        for (MovieTile[] movieTileRow : movieSquares){
            for (MovieTile movieTile : movieTileRow){
                if (movieTile.getMovieName().compareTo(movieName) == 0){
                    return movieTile;
                }
            }
        }

        return null;
    }

    /**
     * Represents a MovieTile that contains the movie's name and price and the ability to buy the movie
     */
    public static class MovieTile extends StackPane {
        private Rectangle tile;
        private Label movieNameLbl, priceLbl;
        private Button buyBtn;
        private boolean isRented;

        public MovieTile(String movieName, String price) {
            isRented = false;
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

        public Float getPrice(){
            return Float.parseFloat(priceLbl.getText());
        }

        public String getMovieName(){
            return movieNameLbl.getText();
        }

        // Changes the fill color of the movie tile depending on whether or not the movie has been rented
        public void setRentedStatus(boolean status){
            isRented = status;

            if (isRented){
                tile.setFill(Color.RED);
            }else {
                tile.setFill(Color.DARKSEAGREEN);
            }
        }

        public boolean getRentedStatus(){
            return isRented;
        }
    }

    /**
     * Changes the movie tiles to be ordered alphabetically in the RentView
     */
    private void setAlphabeticalFilterMode(){
        ArrayList<String> movieNames = new ArrayList<>();

        for (MovieTile[] movieTileRow : movieSquares){
            for (MovieTile movieTile : movieTileRow){
                movieNames.add(movieTile.getMovieName());
            }
        }

        Collections.sort(movieNames); //sorts movie names alphabetically

        MovieTile[][] tempMovieSquares = new MovieTile[3][3]; //Contains new positioning of tiles

        movieSquareLayout.getChildren().clear();
        getChildren().remove(movieSquareLayout); // <-- don't know if I need this line

        int movieNamesPos = 0;
        for (int i = 0; i < movieSquares.length; i++){
            for (int j = 0; j < movieSquares[i].length; j++){
                tempMovieSquares[i][j] = getMovieTile(movieNames.get(movieNamesPos));
                movieSquareLayout.add(getMovieTile(movieNames.get(movieNamesPos++)), j, i);
            }
        }

        setCenter(movieSquareLayout); // <-- don't know if I need this line
        movieSquares = tempMovieSquares;
    }

    /**
     * Changes the movie tiles to be ordered by price in ascending order
     */
    private void setLeastExpensiveFilterMode(){
        MovieTile[] tempMovieSquares = new MovieTile[movieSquares.length * 3];
        int tempMovieSquaresPos = 0;

        for (int i = 0; i < movieSquares.length; i++){
            for (int j = 0; j < movieSquares[i].length; j++){
                tempMovieSquares[tempMovieSquaresPos++] = movieSquares[i][j];
            }
        }

        sort(tempMovieSquares);

        movieSquareLayout.getChildren().clear();
        getChildren().remove(movieSquareLayout);

        tempMovieSquaresPos = 0;

        for (int i = 0; i < movieSquares.length; i++){
            for (int j = 0; j < movieSquares[i].length; j++){
                movieSquares[i][j] = tempMovieSquares[tempMovieSquaresPos];
                movieSquareLayout.add(tempMovieSquares[tempMovieSquaresPos++], j, i);
            }
        }

        setCenter(movieSquareLayout);
    }

    /**
     * Given a list of MovieTiles, sorts the list so that it's in ascending order
     * @param lst a list of floats
     * @return lst in sorted ascending order
     */
    private static MovieTile[] sort(MovieTile[] lst){
        int j;
        MovieTile key;

        for (int i = 1; i < lst.length; i++){
            key = lst[i];
            j = i - 1;

            while (j >= 0 && key.getPrice() < lst[j].getPrice()){
                lst[j + 1] = lst[j];
                j = j - 1;
            }

            lst[j + 1] = key;

            System.out.println("Current i = " + i);
            for (int k = 0; k < lst.length; k++){
                System.out.println(lst[k].priceLbl.getText());
            }
        }

        return lst;
    }
}
