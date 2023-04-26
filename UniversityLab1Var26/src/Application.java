/*
 Система Автобаза. Диспетчер распределяет заявки на Рейсы между Водителями и назначает для этого Автомобиль.
 Водитель может сделать заявку на ремонт. Диспетчер может отстранить Водителя от работы. Водитель делает
 отметку о выполнении Рейса и состоянии Автомобиля.
 */


public class Application{
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
