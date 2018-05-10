package jlotto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lotto5 extends Lotto {
    

    
    public Lotto5 (String line){
        
        super(4, 5);
        
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
        
        for (int i = 0; i <= 4; i++) {
            winners.add(i, Integer.parseInt(st.nextToken()));
        }
        
    }
    

 
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(Integer.toString(week()));
        sb.append(". heti ÖTÖS nyerősorsolás;\n");
        
        sb.append("Telitalálatok száma: ");
        sb.append(numberOfHits.get(0));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(0));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Négyes találatok száma: ");
        sb.append(numberOfHits.get(1));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(1));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Hármas találatok száma: ");
        sb.append(numberOfHits.get(2));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(2));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("Kettes találatok száma: ");
        sb.append(numberOfHits.get(3));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(3));
        sb.append(System.getProperty("line.separator"));
        
        sb.append(results);
        
        return sb.toString();
    }
    
}
