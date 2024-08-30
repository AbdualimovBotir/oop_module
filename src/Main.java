import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        System.out.println(BLUE);
        SimCardManager manager = new SimCardManager(100);
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Add Sim card");
            System.out.println("2. Sim card list");
            System.out.println("3. Search");
            System.out.println("4. Delete sim card (by phone)");
            System.out.println("5. Exit");
            System.out.print("Hush kelibsiz Uzbekiston Aloqa provaydrlariga \t\n Menyudan o'zingizga keraklisini tanlang: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Telefon raqamini kiriting (Bu faqat Uzbekiston raqamlari uchun) Misol:+998910256525 yoki 910256525: ");
                    String phoneNumber = manager.normalizePhoneNumber(scanner.nextLine());

                    String prefiks;
                    if (phoneNumber.startsWith("+998")) {
                        prefiks = phoneNumber.substring(4, 6);
                    } else {
                        System.out.println("Noto'g'ri telefon raqami format.");
                        break;
                    }

                    String operator;
                    switch (prefiks) {
                        case "50":
                            operator = "Ucell";
                            break;
                        case "90":
                        case "91":
                            operator = "Beeline";
                            break;
                        case "93":
                        case "94":
                            operator = "Ucell";
                            break;
                        case "97":
                            operator = "Mobiuz";
                            break;
                        case "99":
                            operator = "UzMobile";
                            break;
                        case "88":
                            operator = "Perfectum";
                            break;
                        default:
                            operator = "Noma'lum operator";
                    }

                    String id = UUID.randomUUID().toString();
                    manager.addSimCard(new SimCard(id, phoneNumber, operator));
                    break;

                case 2:
                    manager.listSimCards();
                    break;

                case 3:
                    System.out.print("Qidirilayotgan sim kartaning telefon raqamini kiriting (Bu faqat Uzbekiston raqamlari uchun) Misol:+998910256525 yoki 910256525: ");
                    phoneNumber = manager.normalizePhoneNumber(scanner.nextLine());
                    SimCard foundSimCard = manager.searchSimCard(phoneNumber);
                    if (foundSimCard != null) {
                        System.out.println("Topildi: " + foundSimCard);
                    } else {
                        System.out.println("Sim karta topilmadi.");
                    }
                    break;

                case 4:
                    System.out.print("O'chiriladigan sim kartaning telefon raqamini kiriting (Bu faqat Uzbekiston raqamlari uchun) Misol:+998910256525 yoki 910256525: ");
                    phoneNumber = manager.normalizePhoneNumber(scanner.nextLine());
                    manager.deleteSimCard(phoneNumber);
                    break;

                case 5:
                    System.out.println(RESET);
                    System.out.println("Chiqish.");
                    break;

                default:
                    System.out.println("Noto'g'ri tanlov. Iltimos, qayta urinib ko'ring.");
            }
        } while (choice != 5);
        scanner.close();
    }


}