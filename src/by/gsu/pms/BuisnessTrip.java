package by.gsu.pms;

public class BuisnessTrip {
    private int dailyRate;
    private int id;
    private AccountType account;
    private int days;
    private int transportExpences;

    public BuisnessTrip(){
    }
    public BuisnessTrip(int dailyRate, int id, AccountType account, int days, int transportExpences) {
        this.dailyRate = dailyRate;
        this.id = id;
        this.account = account;
        this.days = days;
        this.transportExpences = transportExpences;
    }
    public BuisnessTrip(int id, AccountType account, int days, int transportExpences) {
        this.dailyRate = 0;
        this.id = id;
        this.account = account;
        this.days = days;
        this.transportExpences = transportExpences;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getAccount() {
        return account;
    }

    public void setAccount(AccountType account) {
        this.account = account;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getTransportExpences() {
        return transportExpences;
    }

    public void setTransportExpences(int transportExpences) {
        this.transportExpences = transportExpences;
    }

    public int getTotalCost(){
        return dailyRate+transportExpences;
    }

    @Override
    public String toString() {
        return id+";"+account+";"+days+";"+transportExpences+";"+dailyRate;
    }
    public boolean isEmpty(){
        return id == 0;
    }
}
