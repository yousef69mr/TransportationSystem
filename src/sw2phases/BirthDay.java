package sw2phases;

public class BirthDay {
    private String day;
    private String month;
    private String year;

    BirthDay(){
        this.day="day";
        this.month="month";
        this.year="year";
    }
    BirthDay(String day,String month,String year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}

