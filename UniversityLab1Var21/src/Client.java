import java.util.ArrayList;
import java.util.List;


public class Client {
    private String clientName;
    private int clientAge;
    private String removeReason = "Alive";
    private List<Drugs> clientDrugs = new ArrayList<>();
    private List<Operation> clientOperations = new ArrayList<>();


    public Client(String clientName, int clientAge) {
        this.clientName = clientName;
        this.clientAge = clientAge;
    }


    // Получить имя клиента
    public String getClientName() {
        return clientName;
    }

    // Получить возраст клиента
    public int getClientAge() {
        return clientAge;
    }

    // Добавть Таблетки клиенту
    public void addDrug(Drugs drug) {
        clientDrugs.add(drug);
    }

    // Добавть Операцию клиенту
    public void addOperation(Operation operation) {
        clientOperations.add(operation);
    }

    // Получить лекарства клиента
    public List<Drugs> getClientDrugs() {
        return clientDrugs;
    }

    // Полуичть список операций клиента
    public List<Operation> getClientOperations() {
        return clientOperations;
    }

    // Получить причину отчисления из больницы
    public String getRemoveReason() {
        return removeReason;
    }

    // Задать причину отчисления из больницы
    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }
}

