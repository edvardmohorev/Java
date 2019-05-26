package by.gsu.pms;

public enum AccountType {
    COOL,SMART,SUPER,SUPERCRAZY;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
