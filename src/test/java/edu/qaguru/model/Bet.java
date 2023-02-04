package edu.qaguru.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.ArrayList;

public class Bet {
    public Integer id;
    @JsonAlias("Amount")
    public Integer amount;
    public String status;
    public Boolean inBonusForDeposit;
    public MarketName marketName;

    public ArrayList<Outcome> outcomes;
    public ArrayList <String> name;


    public static class MarketName {
        public String en;
        public String kz;
        public String ru;
    }

    public static class Outcome {
        public Integer id;
        public Integer betId;
        public Boolean live;
        public Double rate;
    }


}
