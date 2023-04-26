/*
 * Система Автобаза. Диспетчер распределяет заявки на Рейсы между Водителями и назначает для этого Автомобиль.
 * Водитель может сделать заявку на ремонт. Диспетчер может отстранить Водителя от работы. Водитель делает
 * отметку о выполнении Рейса и состоянии Автомобиля.
 */



public class Car {
    private String mark;
    private int mileage;  // Пробег
    private boolean onRepair = false; // На ремонте (false-не на ремонте)
    private boolean busy = false; // занята машина или нет (false-свободная)

    public Car (String mark, int mileage) {
        this.mark = mark;
        this.mileage = mileage;
    }

    // Получить название машины
    public String getMark() {
        return mark;
    }

    // Получить пробег машины
    public int getMileage() {
        return mileage;
    }

    // Поставить машину на ремонт/забрать с ремонта
    public void setOnRepair(boolean onRepair) {
        this.onRepair = onRepair;
    }

    // Получить статус машины на ремонте
    public boolean getOnRepairStatus() {
        return onRepair;
    }

    // Задать занята машина или нет
    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    // Получить занята машина или нет
    public boolean getBusy() {
        return busy;
    }
}
