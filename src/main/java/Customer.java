import java.util.LinkedList;
import java.util.Queue;

public class Customer {

    private String name;
    private float cashOnHand;
    private Queue<String> rentedMovies;

    public Customer(){
        this.name = "N/A";
        this.cashOnHand = 0.0F;
        rentedMovies = new LinkedList<>();
    }

    public String getName(){
        return name;
    }

    public float getCashOnHand(){
        return cashOnHand;
    }

    public Queue<String> getRentedMovies() {
        return rentedMovies;
    }

    public void setCashOnHand(float cashChange){
        cashOnHand += cashChange;
    }

    public void addMovieRental(String movie){
        rentedMovies.add(movie);
    }

    public void setName(String name){
        this.name = name;
    }
}
