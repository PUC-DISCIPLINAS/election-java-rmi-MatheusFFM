package models;

public class Senator implements Comparable<Senator>{
    private String name;
    private String number;
    private String party;

    public Senator(String name, String number, String party) {
        this.name = name;
        this.number = number;
        this.party = party;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    @Override
    public int compareTo(Senator o) {
        return this.number.compareTo(o.number);
    }
}
