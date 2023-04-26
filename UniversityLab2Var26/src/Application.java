import java.io.Serializable;

public class Application implements Serializable {
    private String startAddress;  // начальный адрес
    private String finishAddress;  // конечный адресс
    private boolean finished; // закрытая заявка

    public Application (String startAddress, String finishAddress) {
        this.startAddress = startAddress;
        this.finishAddress = finishAddress;
    }

    // Получить начальный адрес
    public String getStartAddress() {
        return startAddress;
    }

    // Получить конечный адресс
    public String getFinishAddress() {
        return finishAddress;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean getFinished() {
        return finished;
    }
}
