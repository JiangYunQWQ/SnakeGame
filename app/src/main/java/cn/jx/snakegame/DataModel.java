package cn.jx.snakegame;

public class DataModel implements Comparable<DataModel>{

    private int score;
    private int time;

    public DataModel(int score, int time) {
        this.score = score;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(DataModel o) {
        int num=o.score-this.score;
        if(num==0){
            return this.time-o.time;
        }
        return num;
    }
}
