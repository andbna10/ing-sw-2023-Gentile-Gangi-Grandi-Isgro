package ServerSide.Model;

public class ScoringToken {
    private int points;
    private Roman roman;

    /**
     * Overview: ScoringToken constructor
     */
    public ScoringToken(Roman roman, int points){
        this.points = points;
        this.roman = roman;
    }

    /**
     * Overview: points getter
     */
    public int getPoints(){ return points; }

    /**
     * Overview: roman getter
     */
    public Roman getRoman(){ return roman; }

}
