package jlotto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EuroJackpot extends Lotto {
    
    private List<Integer> winners2;
    
    public EuroJackpot (String line){
        super(12, 5);
        
        winners2 = new ArrayList(2);
        StringTokenizer st = new StringTokenizer (line, ";");
        st.nextToken();
        st.nextToken();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.");
        try {
            myDate = formatter.parse(st.nextToken());
        } catch (ParseException ex) {
            Logger.getLogger(Lotto5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i <= 11; i++) {
            numberOfHits.add(i, Integer.parseInt(st.nextToken()));
            amountOfWins.add(i, st.nextToken());
        }
        
        for (int i = 0; i <= 4; i++) {
            winners.add(i, Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i <= 1; i++) {
            winners2.add(i, Integer.parseInt(st.nextToken()));
        }
    }
    
    @Override
    public void writeResult(List<Integer> numbers) {
        
        List<Integer> fiveNumbers = numbers.subList(0, 4);
        List<Integer> twoNumbers = numbers.subList(5, 6);
        
        StringBuilder sb = new StringBuilder();
        
        List<Integer> fiveNumbersHit = new ArrayList<>();
        List<Integer> twoNumbersHit = new ArrayList<>();
        
        for (int winnerNumber : winners) {
            for (int n : fiveNumbers) {
                if (winnerNumber == n) {
                    fiveNumbersHit.add(n);
                }
            }
        }
        
        for (int winnerNumber : winners2) {
            for (int n : twoNumbers) {
                if (winnerNumber == n) {
                    twoNumbersHit.add(n);
                }
            }
        }
        
        if ( (fiveNumbersHit.size() >= 3) ||
                (twoNumbersHit.size() == 2) ||
                ((fiveNumbersHit.size() == 2 ) && (twoNumbersHit.size() == 1))
                
           ) {
            sb.append(parseNumberLists(fiveNumbersHit, twoNumbersHit));
        }
        
        sb.append(System.getProperty("line.separator"));
        
        results.append(sb);
    }
    
    public String parseNumberLists(List<Integer> a , List<Integer> b ) {
        StringBuilder sb = new StringBuilder();
        a.forEach((number) -> {
            sb.append(number);
            sb.append(" ");
        });
        sb.append(" / ");
        b.forEach((number) -> {
            sb.append(number);
            sb.append(" ");
        });
        
        return sb.toString();
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(Integer.toString(week()));
        sb.append(". heti EUROJACKPOT nyerősorsolás;\n");
        
        sb.append("5+2 találat: ");
        sb.append(numberOfHits.get(0));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(0));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("5+1 találat: ");
        sb.append(numberOfHits.get(1));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(1));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("5 találat: ");
        sb.append(numberOfHits.get(2));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(2));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("4+2 találat: ");
        sb.append(numberOfHits.get(3));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get(3));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("4+1 találat: ");
        sb.append(numberOfHits.get( 4 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 4 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("4 találat: ");
        sb.append(numberOfHits.get( 5 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 5 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("3+2 találat: ");
        sb.append(numberOfHits.get( 6 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 6 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("3+1 találat: ");
        sb.append(numberOfHits.get( 7 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 7 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("2+2 találat: ");
        sb.append(numberOfHits.get( 8 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 8 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("3 találat: ");
        sb.append(numberOfHits.get( 9 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 9 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("1+2 találat: ");
        sb.append(numberOfHits.get( 10 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 10 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("2+1 találat: ");
        sb.append(numberOfHits.get( 11 ));
        sb.append(" Nyeremény: ");
        sb.append(amountOfWins.get( 11 ));
        sb.append(System.getProperty("line.separator"));
        
        sb.append(results);
        
        return sb.toString();
    }
    
}
