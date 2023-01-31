package com.example.demo;


import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author: YJY
 * @date: 2022/8/8 10:59
 * @description:
 */
public class Word {

  public static Random r = new Random();
  public static String[] day1 = {"a", "abandon", "abdomen", "abide", "ability", "able",
      "abnormal", "aboard", "abolish", "abound", "about", "above", "abroad",
      "abrupt", "absence", "absent", "absolute", "absorb", "abstract", "absurd",
  };

  public static String[] day2 = {"abundance", "abundant", "abuse", "academic", "academy",
      "accelerate",
      "accent", "accept", "acceptance", "access", "accessory", "accident", "accidental",
      "acclaim", "accommodate", "accommodation", "accompany", "accomplish", "accord", "accordance"
  };

  public static String[] day3 = {"according to", "accordingly", "account", "accountant",
      "accumulate", "accuracy", "accurate",
      "accuse", "accustomed", "ache", "achieve", "acid", "acknowledge", "acquaint", "acquaintance",
      "acquire", "acquisition",
      "acre", "acrobat", "across"
  };

  public static String[] day4 = {"act", "action", "activate", "active", "activity", "actor",
      "actress", "actual", "acute",
      "adapt", "add", "addict", "addition", "additional", "address", "adequate", "adhere",
      "adjacent", "adjective", "adjoin"};

  public static String[] day5 = {"adjust", "administer", "administration", "admire", "admission",
      "admit", "adolescent", "adopt",
      "adore", "adult", "advance", "advanced", "advantage", "advent", "adventure", "adverb",
      "adverse", "advertise",
      "advice", "advisable"};

  public static String[] day6 = {"advise", "advocate", "aerial", "airplane", "aesthetic", "affair",
      "affect", "affection", "affiliate",
      "affirm", "affluent", "afford", "afraid", "against", "agency", "after", "afternoon",
      "afterward", "again", "age"};

  public static String[] day7 = {"agenda", "agent", "aggravate", "aggressive", "agitate", "ago",
      "agony", "agree", "agreeable", "agreement",
      "agriculture", "ahead", "aid", "aim", "air", "air-conditioning", "aircraft", "airline",
      "airport", "aisle"};

  public static String[] day8 = {"alarm", "album", "alcohol", "alert", "alien", "alike", "alive",
      "all", "allege", "alleviate", "alliance",
      "allocate", "allow", "allowance", "alloy", "ally", "almost", "alone", "along", "alongside"};

  public static String[] day9 = {"aloud", "alphabet", "already", "also", "alter", "alternate",
      "although", "altitude", "altogether",
      "aluminum", "always", "amateur", "amaze", "ambassador", "ambiguous", "ambition", "ambitious",
      "ambulance", "amend"};

  public static String[] day10 = {"amiable", "amid", "among", "amount", "ample", "amplifier",
      "amplify", "amuse", "analogue", "analyse",
      "analysis", "analytic", "ancestor", "anchor", "ancient", "and", "anecdote", "angel", "anger"};

  public static String[] day11 = {"angle", "angry", "anguish", "animal", "ankle", "anniversary",
      "announce", "annoy", "annual", "anonymous",
      "another", "answer", "ant", "antenna", "anticipate", "antique", "anxiety", "anxious", "any",
      "anybody"};
  public static String[] day12 = {"anyhow", "anyone", "anything", "anyway", "anywhere", "apart",
      "apartment",
      "apologise", "apology", "appal", "apparatus", "apparent", "appeal", "appear", "appearance",
      "appendix",
      "appetite", "applaud", "applause", "apple"};

  public static String[]day13 = { "appliance","applicable","application","apply","appoint","appointment",
      "appraisal","appreciate","approach","appropriate","approval","approve","approximate","April","apt",
      "arbitrary","arch","architect","architecture","area"};

  public static String[] day14 = {"argue","argument","arise","arithmetic","arm","army","around","arouse",
      "arrange","array","arrest","arrival","arrive","arrogant","arrow","art","article","articulate","artificial",
  "artist"};

  public static String[] day15 ={"artery","artistic","as","ascend","ascertain","ash","ashamed","ashore","aside","ask",
      "asleep","aspect","aspire","assassinate","assault","assemble","assembly","assert","assess","asset"};

  public static String[] day16 = {"assign","assignment","assimilate","assist","assistance","associate","association",
  "assume","assumption","assure","assurance","astonish","astronaut","astronomy","at","athlete","atmosphere","atom",
  "attach","attack"};

  public static String[] day17 = { "attain","attempt","attend","attendance","attendant","attention","attitude","attorney",
      "attract","attractive","attribute","auction","audience","audio","auditorium","augment","August","aunt","aural"};

  public static String[] day18 = {"authentic","author","authority","auto","automatic","automation","autonomy","autumn",
      "auxiliary","avail","available","avenue","average","avert","aviation","avoid","await","awake","award","aware"
  };

  public static String[] day19 = {"away","awe","awful","awkward","axe","axis","baby","bachelor","back","background",
  "bacon","bacterium","bad","badge","badly","badminton","bag","baggage","bait","bake"};

  public static String[] day20 = {"balance","balcony","bald","ball","ballet","balloon","ballot","ban","banana","band",
  "bandage","bang","bank","bankrupt","banner","banquet","bar","barbecue","barber","bare"};

  public static String[] day21 = {"barely","bargain","bark","barn","barrel","barren","barrier","base","baseball","basement",
 "basic","basin","basket","basketball","bat","batch","bath","bathe","bathroom"};

  public static String[] day22 = {"battery","battle","bay","be","beach","beam","bean","bear","beard","bearing","beast","beat",
"beautiful","beauty","because","become","bed","bee","beef","beer"};

  public static String[] day23 = {"before","beforehand","beg","begin","beginning","behalf","behave","behavior","behind","being",
      "belief","bell","belly","belong","beloved","below","belt","bench","bend"};

  public static String[] day24 = {"beneath","beneficial","benefit","benign","beside","best","bet","betray","better","between","beverage","beware",
  "bewilder","beyond","bias","Bible","bibliography","bicycle","bid"};


  public static final int arrNumber = 12;

  public static void main(String[] args) {
    int a = r.nextInt(20);
    System.out.println(day19[a]);

  }

  public static String[] getArr() {
    int number = r.nextInt(arrNumber) + 1;
    switch (number) {
      case 1:
        return day1;
      case 2:
        return day2;
      case 3:
        return day3;
      case 4:
        return day4;
      case 5:
        return day5;
      case 6:
        return day6;
      case 7:
        return day7;
      case 8:
        return day8;
      case 9:
        return day9;
      case 10:
        return day10;
      case 11:
        return day11;
      default:
        return day12;
    }
  }

}
