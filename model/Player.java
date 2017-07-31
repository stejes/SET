package model;

/**
 * Created by fed on 1/31/15.
 */
public class Player implements Comparable<Player> {
    private String name;
    private int score = 0;
    private float time = 0;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.time = 0;
    }

    public void addScore(float time) {

        this.score += 3;
        this.time += time;
    }

    public int getScore(){
        return score;
    }

    public float getTime(){
        return time;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String result  = "";
        result += System.out.printf("Player %s: %d / %.2f", name, score, time);
        return result;
    }

    public int compareTo(Player o) {
        if(o.getScore() == score){
            return (int)time*100 - (int)(o.getTime() * 100);
        }
        else{
            return o.getScore() - score;
        }
    }
}
