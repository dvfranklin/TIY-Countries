public class Country {

    private String abbreviation;
    private String countryName;

    public Country(String abbreviation, String countryName) {
        this.abbreviation = abbreviation;
        this.countryName = countryName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString(){
        return "Abbreviation: " + this.abbreviation + "; Country Name: " + this.countryName;
    }

}