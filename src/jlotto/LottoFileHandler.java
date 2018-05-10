package jlotto;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class LottoFileHandler {
    
    public static int lotto5LastWeek(){
        return lastWeek(JLotto.LOTTO5_FILE);
    }
    
    public static int lotto6LastWeek(){
        return lastWeek(JLotto.LOTTO6_FILE);
    }
    
    public static int lottoSkandiLastWeek(){
        return lastWeek(JLotto.LOTTOSKANDI_FILE);
    }
    
    public static int jokerLastWeek(){
        return lastWeek(JLotto.JOKER_FILE);
    }
    
    public static int euroJackpotLastWeek(){
        return lastWeek(JLotto.EUROJACKPOT_FILE);
    }
    
    private static int lastWeek(String fileName) {
      String line = null;
      try (
              BufferedReader br = new BufferedReader(new FileReader(fileName));
         )
      { 
          line = br.readLine(); 
      }
      catch (Exception ex) {
          ex.printStackTrace();
      }
      StringTokenizer st = new StringTokenizer (line, ";");
      st.nextToken();
      return Integer.parseInt(st.nextToken());
    }
    
    //https://stackoverflow.com/questions/6659360/how-to-solve-javax-net-ssl-sslhandshakeexception-error
    public static void download(String link, String fileName) {
        URLConnection fileStream = null;
        try {
            URL remoteFile = new URL(link);
            fileStream = remoteFile.openConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try ( 
                FileOutputStream fos = new FileOutputStream(fileName);
                InputStream in = fileStream.getInputStream();
            )
            {   int data;
                while ((data = in.read()) != -1) {
                    fos.write(data);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
    public static String pullRow(String fileName) {
        String line = null;
        try (
                BufferedReader br = new BufferedReader(new FileReader(fileName));
            )
        { 
            line = br.readLine(); 
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return line;
    }
    
    public static String pullRow(String fileName, int week) {
        String line = null;
        try (
                BufferedReader br = new BufferedReader(new FileReader(fileName));
            )
        { 
            
        while ( (line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer (line, ";");
            st.nextToken();
            if (Integer.parseInt(st.nextToken()) == week ) { break; }
        }
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return line;
    }
    
}
