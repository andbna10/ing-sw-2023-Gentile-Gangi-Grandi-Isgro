package Model;

public class Player {
    private int id;
    private static int numPlayers = 0;
    private String username;
    private int points;
    private PersonalGoalCard goal;
    private Bookshelf bookshelf;

    /**
     * Player constructor
     */
    public Player(String username){
        this.username = username;
        id = numPlayers;
        numPlayers++;
        points = 0;
        bookshelf = new Bookshelf();
    }

    /**
     * Overview: username getter
     */
    public String getUsername(){ return username; }

    /**
     * Overview: id getter
     */
    public int getId(){ return id; }

    /**
     * Overview: points getter
     */
    public int getPoints(){ return points; }

    /**
     * Overview: get goal
     */
    public PersonalGoalCard getGoal(){ return goal; }

    /**
     * Overview: get bookshelf
     */
    public Bookshelf getBookshelf(){ return bookshelf; }


    /**
     * Overview: set the goal
     */
    public void setGoal(PersonalGoalCard goal){ this.goal = goal; }
}
