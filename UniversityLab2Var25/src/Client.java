import java.io.Serializable;

public class Client implements Serializable {
    private final String name;
    private String phoneNumber;
    private int money;
    private boolean needToChangePhoneNumber = false;
    private boolean isBlocked = false;

    public Client(String name, String phoneNumber, int money){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean getNeedToChangePhoneNumber() {
        return this.needToChangePhoneNumber;
    }

    public void setNeedToChangePhoneNumber(boolean needToChangePhoneNumber) {
        this.needToChangePhoneNumber = needToChangePhoneNumber;
    }

    public boolean getIsBlocked(){
        return this.isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }



}
