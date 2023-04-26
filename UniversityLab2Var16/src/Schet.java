import java.io.Serializable;

public class Schet implements Serializable {
    private String schetName;  // Наименование счета
    private int schetSum ;  // Сумма счета
    private boolean block = false;  // Флаг блокировки счета (по-умолчанию разблокирован)

    public Schet (String schetName, int schetSum){
        this.schetName = schetName;
        this.schetSum = schetSum;
    }

    public Schet() {

    }

    // Задать новое название счету
    public void setSchetName(String schetName) {
        this.schetName = schetName;
    }


    // Получить название счета
    public String getSchetName() {
        return schetName;
    }

    // Установить новую сумму по счету
    public void setSchetSum(int schetSum) {
        this.schetSum = schetSum;
    }

    // Получить сумму на счету
    public int getSchetSum() {
        return schetSum;
    }

    // Управление блокировкой если на вход подается 1-счет блокируется, 0-разблокируется
    public void setBlock(boolean block) {
        this.block = block;
    }

    // получить значение флага блокировки
    public boolean getBlock() {
        return block;
    }

}

