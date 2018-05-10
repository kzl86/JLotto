package jlotto;

import java.util.List;

public class LottoContainer {
    
    static List<Lotto5> lotto5List;
    static List<Lotto6> lotto6List;
    static List<LottoSkandi> lottoSkandiList;
    static List<Joker> jokerList;
    static List<EuroJackpot> euroJackpotList;
    
    public static Lotto5 lotto5onWeek (int w) {
        Lotto5 result = null;
        for (Lotto5 lotto5 : lotto5List) {
            if (lotto5.week() == w) {
                result = lotto5;
            }
        }
        return result;
    }
    
    public static Lotto6 lotto6onWeek (int w) {
        
        Lotto6 result = null;
        for (Lotto6 lotto6 : lotto6List) {
            if (lotto6.week() == w) {
                result = lotto6;
            }
        }
        return result;
        
    }
    
    public static LottoSkandi lottoSkandionWeek (int w) {
        
        LottoSkandi result = null;
        for (LottoSkandi lottoSkandi : lottoSkandiList) {
            if (lottoSkandi.week() == w) {
                result = lottoSkandi;
            }
        }
        return result;
        
    }
    
    public static Joker jokeronWeek (int w) {
        
        Joker result = null;
        for (Joker joker : jokerList) {
            if (joker.week() == w) {
                result = joker;
            }
        }
        return result;
        
    }
    
    public static EuroJackpot euroJackpotonWeek (int w) {
        EuroJackpot result = null;
        for (EuroJackpot euroJackpot : euroJackpotList) {
            if (euroJackpot.week() == w) {
                result = euroJackpot;
            }
        }
        return result;  
    }
}
