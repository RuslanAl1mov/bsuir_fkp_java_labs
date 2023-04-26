import java.io.Serializable;

public class Operation implements Serializable {
    private String operationName;

    public Operation(String operationName) {
        this.operationName = operationName;
    }

    // Получить название оперции
    public String getOperationName() {
        return operationName;
    }


}

