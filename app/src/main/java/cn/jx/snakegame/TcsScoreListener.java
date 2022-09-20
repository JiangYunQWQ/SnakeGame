package cn.jx.snakegame;

/**
 * 得分记录
 */
public interface TcsScoreListener {
    void onTCSScore(int score);
    void onTime(int time);
    void onGameOver();
}
