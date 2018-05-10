package jlotto;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class JLotto {

    /**
     * @param args the command line arguments
     */
    
    private static final String LOTTO5_DOWNLOAD_LINK = "https://bet.szerencsejatek.hu/cmsfiles/otos.csv";
    private static final String LOTTO6_DOWNLOAD_LINK = "https://bet.szerencsejatek.hu/cmsfiles/hatos.csv";
    private static final String LOTTOSKANDI_DOWNLOAD_LINK = "https://bet.szerencsejatek.hu/cmsfiles/skandi.csv";
    private static final String JOKER_DOWNLOAD_LINK = "https://bet.szerencsejatek.hu/cmsfiles/joker.csv";
    private static final String EUROJACKPOT_DOWNLOAD_LINK = "https://bet.szerencsejatek.hu/cmsfiles/eurojackpot.csv";
    
    static final String LOTTO5_FILE = "otos.csv";
    static final String LOTTO6_FILE = "hatos.csv";
    static final String LOTTOSKANDI_FILE = "skandi.csv";
    static final String JOKER_FILE = "joker.csv";
    static final String EUROJACKPOT_FILE = "eurojackpot.csv";
     
    public static void main(String[] args) {
        
        LottoContainer.lotto5List = new ArrayList<>();
        LottoContainer.lotto6List = new ArrayList<>();
        LottoContainer.lottoSkandiList = new ArrayList<>();
        LottoContainer.jokerList = new ArrayList<>();
        LottoContainer.euroJackpotList = new ArrayList<>();
        
        // Links are hard coded here
        Runnable lotto5Download = () -> { 
            LottoFileHandler.download(
                LOTTO5_DOWNLOAD_LINK,
                LOTTO5_FILE
            );
        };
        
        Runnable lotto6Download = () -> {
            LottoFileHandler.download(
                LOTTO6_DOWNLOAD_LINK,
                LOTTO6_FILE
            );
        };
        
        Runnable lottoSkandiDownload = () -> {
            LottoFileHandler.download(
                LOTTOSKANDI_DOWNLOAD_LINK,
                LOTTOSKANDI_FILE
            );
        };
        
        Runnable jokerDownload = () -> {
            LottoFileHandler.download(
                JOKER_DOWNLOAD_LINK,
                JOKER_FILE
            );
        };
       
        Runnable euroJackpotDownload = () -> {
            LottoFileHandler.download(
                EUROJACKPOT_DOWNLOAD_LINK,
                EUROJACKPOT_FILE
            );
        };
        
        // Running threads
        lotto5Download.run();
        lotto6Download.run();
        lottoSkandiDownload.run();
        jokerDownload.run();
        euroJackpotDownload.run();
        
        List<String> input = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("5/6/7> ");
            String inputLine = s.nextLine();
            if (inputLine.equals("")) { break; }
            input.add(inputLine);
        }
        
        for (String inputLine : input) {
            
            List<Integer> numbers = new ArrayList<>();
        
            if (inputLine.startsWith("#")) {
                
                StringTokenizer preNumbers = new StringTokenizer (inputLine, " ");
                String ws = preNumbers.nextToken();
                int week =  Integer.parseInt(ws.substring(1, ws.length()));
                while (preNumbers.hasMoreElements()) {
                    numbers.add(Integer.parseInt(preNumbers.nextToken()));
                }
                
                if (numbers.size() == 5) { 
                    lotto5Process(
                        LottoFileHandler.pullRow(LOTTO5_FILE, week),
                        numbers,
                        week
                    );
                }
                
                if (numbers.size() == 6) {
                    lotto6Process(
                        LottoFileHandler.pullRow(LOTTO6_FILE, week),
                        numbers,
                        week
                    );
                }
                
                if (numbers.size() == 7) {
                    lottoSkandiProcess(
                        LottoFileHandler.pullRow(LOTTOSKANDI_FILE, week),
                        numbers,
                        week
                    );
                }
                
            } else {
                numbers = Arrays.stream(inputLine.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
                if (numbers.size() == 5) { 
                    lotto5Process(
                        LottoFileHandler.pullRow(LOTTO5_FILE),
                        numbers,
                        0
                    ); 
                }
                
                if (numbers.size() == 6) {
                    lotto6Process(
                        LottoFileHandler.pullRow(LOTTO6_FILE),
                        numbers,
                        0
                    );
                }
                
                if (numbers.size() == 7) {
                    lottoSkandiProcess(
                        LottoFileHandler.pullRow(LOTTOSKANDI_FILE),
                        numbers,
                        0
                    );
                }

            }    
        }
        
        LottoContainer.lotto5List.forEach((lotto) -> {
            System.out.println(lotto);
        });
        
        LottoContainer.lotto6List.forEach((lotto) -> {
            System.out.println(lotto);
        });
        
        LottoContainer.lottoSkandiList.forEach((lotto) -> {
            System.out.println(lotto);
        });
        
        boolean continueWithJoker;
        while (true) {
            System.out.println("Joker? (i)gen/enter");
            String in = s.nextLine();
            if (in.startsWith("i")) {
                continueWithJoker = true;
                break;
            } else {
                continueWithJoker = false;
                break;
            }
        }
        
        if (continueWithJoker) {
        
            List<String> inputJoker = new ArrayList<>();
            while (true) {
                System.out.print("JOKER> ");
                String inputLine = s.nextLine();
                if (inputLine.equals("")) { break; }
                inputJoker.add(inputLine);
            }
            
            for (String inputLine : inputJoker) {
                
                List<Integer> numbers = new ArrayList<>();
                if (inputLine.startsWith("#")) {
                
                StringTokenizer preNumbers = new StringTokenizer (inputLine, " ");
                String ws = preNumbers.nextToken();
                int week =  Integer.parseInt(ws.substring(1, ws.length()));
                while (preNumbers.hasMoreElements()) {
                    numbers.add(Integer.parseInt(preNumbers.nextToken()));
                }
                            
                if (numbers.size() == 6) {
                    jokerProcess(
                        LottoFileHandler.pullRow(JOKER_FILE, week),
                        numbers,
                        week
                    );
                }
                
                } else {
                    numbers = Arrays.stream(inputLine.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                
                    if (numbers.size() == 6) {
                        jokerProcess(
                            LottoFileHandler.pullRow(JOKER_FILE),
                            numbers,
                            0
                        );
                    }
                }
            }
            
        }
        
        boolean continueWithEuroJackpot;
        while (true) {
            System.out.println("EuroJackpot? (i)gen/enter");
            String in = s.nextLine();
            if (in.startsWith("i")) {
                continueWithEuroJackpot = true;
                break;
            } else {
                continueWithEuroJackpot = false;
                break;
            }
        }
        
        if (continueWithEuroJackpot) {
            List<String> inputEuroJackpot = new ArrayList<>();
            while (true) {
                System.out.print("EUROJACKPOT> ");
                String inputLine = s.nextLine();
                if (inputLine.equals("")) { break; }
                inputEuroJackpot.add(inputLine);
            }
            for (String inputLine : inputEuroJackpot) {
                List<Integer> numbers = new ArrayList<>();
                if (inputLine.startsWith("#")) {
                    
                    StringTokenizer preNumbers = new StringTokenizer (inputLine, " ");
                    String ws = preNumbers.nextToken();
                    int week =  Integer.parseInt(ws.substring(1, ws.length()));
                    while (preNumbers.hasMoreElements()) {
                        numbers.add(Integer.parseInt(preNumbers.nextToken()));
                    }
                    
                    if (numbers.size() == 7) {
                    euroJackpotProcess(
                        LottoFileHandler.pullRow(EUROJACKPOT_FILE, week),
                        numbers,
                        week
                        );
                    }
                } else {
                    numbers = Arrays.stream(inputLine.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                
                    if (numbers.size() == 7) {
                        euroJackpotProcess(
                            LottoFileHandler.pullRow(EUROJACKPOT_FILE),
                            numbers,
                            0
                        );
                    }
                }
                
                
            }
        }
        
        
        
        LottoContainer.euroJackpotList.forEach((lotto) -> {
            System.out.println(lotto);
        });
        
        LottoContainer.jokerList.forEach((lotto) -> {
            System.out.println(lotto);
        });
        
        
        
        File lottoFile = new File(LOTTO5_FILE); if (lottoFile.exists()) { lottoFile.delete(); }
        lottoFile = new File(LOTTO6_FILE); if (lottoFile.exists()) { lottoFile.delete(); }
        lottoFile = new File(LOTTOSKANDI_FILE); if (lottoFile.exists()) { lottoFile.delete(); }
        lottoFile = new File(JOKER_FILE); if (lottoFile.exists()) { lottoFile.delete(); }
        lottoFile = new File(EUROJACKPOT_FILE); if (lottoFile.exists()) { lottoFile.delete(); }
    }
    
    private static void lotto5Process(String inputLine, List<Integer> numbers, int w) {
        
        if (w == 0) {
        
            int week = LottoFileHandler.lotto5LastWeek();
            Lotto5 myLotto = LottoContainer.lotto5onWeek(week);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                Lotto5 tmp = new Lotto5(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.lotto5List.add(tmp);
            }
            
        } else {
        
            Lotto5 myLotto = LottoContainer.lotto5onWeek(w);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                
                Lotto5 tmp = new Lotto5(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.lotto5List.add(tmp);
            }
            
        }
    
    }
    
    private static void lotto6Process(String inputLine, List<Integer> numbers, int w) {
        if (w == 0) {
        
            int week = LottoFileHandler.lotto6LastWeek();
            Lotto6 myLotto = LottoContainer.lotto6onWeek(week);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                Lotto6 tmp = new Lotto6(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.lotto6List.add(tmp);
            }
            
        } else {
        
            Lotto6 myLotto = LottoContainer.lotto6onWeek(w);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                
                Lotto6 tmp = new Lotto6(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.lotto6List.add(tmp);
            }
            
        }
    }
    
    private static void lottoSkandiProcess(String inputLine, List<Integer> numbers, int w) {
        if (w == 0) {
        
            int week = LottoFileHandler.lottoSkandiLastWeek();
            LottoSkandi myLotto = LottoContainer.lottoSkandionWeek(week);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                LottoSkandi tmp = new LottoSkandi(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.lottoSkandiList.add(tmp);
            }
            
        } else {
        
            LottoSkandi myLotto = LottoContainer.lottoSkandionWeek(w);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                
                LottoSkandi tmp = new LottoSkandi(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.lottoSkandiList.add(tmp);
            }
            
        }
    }

    private static void jokerProcess(String inputLine, List<Integer> numbers, int w) {
        if (w == 0) {
        
            int week = LottoFileHandler.jokerLastWeek();
            Joker myLotto = LottoContainer.jokeronWeek(week);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                Joker tmp = new Joker(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.jokerList.add(tmp);
            }
            
        } else {
        
            Joker myLotto = LottoContainer.jokeronWeek(w);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                
                Joker tmp = new Joker(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.jokerList.add(tmp);
            }
            
        }
    }
    
    private static void euroJackpotProcess(String inputLine, List<Integer> numbers, int w) {
            if (w == 0) {
        
            int week = LottoFileHandler.euroJackpotLastWeek();
            EuroJackpot myLotto = LottoContainer.euroJackpotonWeek(week);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                EuroJackpot tmp = new EuroJackpot(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.euroJackpotList.add(tmp);
            }
            
        } else {
        
            EuroJackpot myLotto = LottoContainer.euroJackpotonWeek(w);
            if (myLotto != null) {
                myLotto.writeResult(numbers);
            } else {
                
                EuroJackpot tmp = new EuroJackpot(inputLine);
                tmp.writeResult(numbers);
                LottoContainer.euroJackpotList.add(tmp);
            }
            
        }
    }
    
}
