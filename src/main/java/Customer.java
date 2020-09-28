import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Customer {

    private String name;
    private float cashOnHand;
    private Set<String> rentedMovies;

    public Customer(){
        this.name = "N/A";
        this.cashOnHand = 0.0F;
        rentedMovies = new HashSet<>();
    }

    public String getName(){
        return name;
    }

    public float getCashOnHand(){
        return cashOnHand;
    }

    public Set<String> getRentedMovies() {
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
