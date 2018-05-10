package jlotto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LottoSkandi extends Lotto {
    
    private List<Integer> winners2;
    
    public LottoSkandi (String line){
        super(4, 7);
        
        winners2 = new ArrayList(7);
        StringTokenizer st = new StringTokenizer (line, ";");
        st.nextToken();
        st.nextToken();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.");
        try {
            myDate = formatter.parse(st.nextToken());
        } catch (ParseException ex) {
            Logger.getLogger(Lotto5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i <= 3; i++) {
            numberOfHits.add(i, Integer.parseInt(st.nextToken()));
            amountOfWins.add(i, st.nextToken());
        }
        
        for (int i = 0; i <= 6; i++) {
            winners.add(i, Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i <= 6; i++) {
            winners2.add(i, Integer.parseInt(st.nextToken()));
        }
    }
    
    @Override
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
        
        for (int winnerNumber : winners2) {
            for (int n : numbers) {
                if (winnerNumber == n) {
                    sb.append(Integer.toString(n) + " ");
                }
            }
        }
        sb.append(System.getProperty("line.separator"));
        
        results.append(sb);
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(Integer.toString(week()));
        sb.append(". heti SKANDINÁV nyerősorsolás;\n");
        
        sb.append("Telitalálatok száma: ");
        sb.append(numberOfHits.get(0));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(0));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Hatos találatok száma: ");
        sb.append(numberOfHits.get(1));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(1));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Ötös találatok száma: ");
        sb.append(numberOfHits.get(2));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(2));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Négyes találatok száma: ");
        sb.append(numberOfHits.get(3));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(3));
        sb.append(System.getProperty("line.separator"));
        
        sb.append(results);
        
        return sb.toString();
    }
    
}
