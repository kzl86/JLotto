package jlotto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Joker extends Lotto {
    
    public Joker (String line){
        super(5, 6);
        
        StringTokenizer st = new StringTokenizer (line, ";");
        st.nextToken();
        st.nextToken();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.");
        try {
            myDate = formatter.parse(st.nextToken());
        } catch (ParseException ex) {
            Logger.getLogger(Lotto5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i <= 4; i++) {
            numberOfHits.add(i, Integer.parseInt(st.nextToken()));
            amountOfWins.add(i, st.nextToken());
        }
        
        for (int i = 0; i <= 5; i++) {
            winners.add(i, Integer.parseInt(st.nextToken()));
        }
    }
    
    @Override
    public void writeResult(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        
                
        for ( int i = winners.size()-1; i >= 0; i-- ){
            if (winners.get(i) == numbers.get(i)) {
                sb.append(numbers.get(i));
                sb.append(" ");
            } 
        }
        
        sb.append(System.getProperty("line.separator"));
        
        results.append(sb);
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(Integer.toString(week()));
        sb.append(". heti JOKER nyerősorsolás;\n");
        
        sb.append("Telitalálatok száma: ");
        sb.append(numberOfHits.get(0));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(0));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Ötös találatok száma: ");
        sb.append(numberOfHits.get(1));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(1));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Négyes találatok száma: ");
        sb.append(numberOfHits.get(2));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(2));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Hármas találatok száma: ");
        sb.append(numberOfHits.get(3));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(3));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Kettes találatok száma: ");
        sb.append(numberOfHits.get(4));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(4));
        sb.append(System.getProperty("line.separator"));
        
        sb.append(results);
        
        return sb.toString();
    }
    
}
