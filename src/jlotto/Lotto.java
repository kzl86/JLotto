package jlotto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Lotto {

    Date myDate;
    List<Integer> numberOfHits;
    List<String> amountOfWins;
    List<Integer> winners;
    StringBuilder results;
    
    public Lotto(int hitsNwins, int winnernum){
        
        numberOfHits = new ArrayList(hitsNwins);
        amountOfWins = new ArrayList(hitsNwins);
        winners = new ArrayList(winnernum);
        
        results = new StringBuilder();
    }
    
    public int week() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.myDate);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    public void writeResult(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for (int winnerNumber : winners) {
            for (int n : numbers) {
                if (winnerNumber == n) {
                    sb.append(Integer.toString(n) + " ");
                }
            }
        }
        sb.append(System.getProperty("line.separator"));
        results.append(sb);
    }
}
