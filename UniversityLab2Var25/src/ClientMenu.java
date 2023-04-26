import java.util.List;
import java.util.Scanner;

public class ClientMenu {
    private Client client;
    private List<Client> clients;

    public ClientMenu(Client client, List<Client> clients) {
        this.client = client;
        this.clients = clients;
        clientMenu();
    }

    public void clientMenu(){
        System.out.println("\nМеню КЛИЕНТА:");
        System.out.println("1) Проверить счет");
        System.out.println("2) Пополнить счет");
        System.out.println("3) Сделать звонок (стоимсоть-1руб.)");
        System.out.println("4) ГЛАВНОЕ МЕНЮ");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        switch (choose) {
            case "1": {checkStatus(); break;}
            case "2": {increaseBalance(); break;}
            case "3": {callSomeone(); break;}
            case "4": {break;}
        }
    }

    public void checkStatus() {
        System.out.println("\nПРОВЕРКА СЧЕТА:");
        System.out.println("На вашем счету: " + client.getMoney());
        if (client.getIsBlocked()){
            System.out.println("ВЫ ЗАБЛОКИРОВАНЫ, ПОПОЛНИТЕ СЧЕТ!");
        }
        clientMenu();
    }

    public void increaseBalance() {
        System.out.println("\nПОПОЛНИТЬ СЧЕТ:");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Введите сумму для пополнения: ");
        String moneyNum = consoleChoose.nextLine();

        try {
            int intMoney = Integer.parseInt(moneyNum);
            client.setMoney(client.getMoney()+intMoney);
            System.out.println("Счет пополнен!");

            boolean isBlocked = client.getIsBlocked();
            if (client.getMoney() > 0) {
                client.setIsBlocked(false);
                if (isBlocked){System.out.println("Вы разблокированы!");}
            }
            clientMenu();
        } catch (Exception e){
            System.out.println("Что-то пошло не так!");
            clientMenu();
        }
    }

    public void callSomeone(){
        if (!client.getIsBlocked()) {
            System.out.println("\nВыполнен звонок! За звоно снят 1р.");
            client.setMoney(client.getMoney()-1);
        } else {
            System.out.println("\nВы не можете сделать звонок, вы ЗАБЛОКИРОВАНЫ!\nПополните счет!");
        }
        clientMenu();
    }
}
