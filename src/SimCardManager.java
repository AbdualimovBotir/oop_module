
public class SimCardManager {
    private SimCard[] simCards;
    private int count;

    public SimCardManager(int size) {
        simCards = new SimCard[size];
        count = 0;
    }

    public String normalizePhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        if (phoneNumber.startsWith("9") && phoneNumber.length() == 9) {
            return "+998" + phoneNumber;
        }
        return phoneNumber; //
    }

    public void addSimCard(SimCard simCard) {
        if (count >= simCards.length) {
            System.out.println("Sim karta ro'yxati to'la.");
            return;
        }

        String normalizedPhoneNumber = normalizePhoneNumber(simCard.getPhoneNumber());

        for (int i = 0; i < count; i++) {
            if (simCards[i].getPhoneNumber().equals(normalizedPhoneNumber)) {
                System.out.println("Bu raqam allaqachon mavjud.");
                return;
            }
        }

        simCards[count] = new SimCard(simCard.getId(), normalizedPhoneNumber, simCard.getOperator());
        count++;
        System.out.println("Sim karta qo'shildi.");
    }

    public void listSimCards() {
        if (count == 0) {
            System.out.println("Sim kartalar ro'yxati bo'sh.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(simCards[i]);
            }
        }
    }

    public SimCard searchSimCard(String phoneNumber) {
        String normalizedPhoneNumber = normalizePhoneNumber(phoneNumber);
        for (int i = 0; i < count; i++) {
            if (simCards[i].getPhoneNumber().equals(normalizedPhoneNumber)) {
                return simCards[i];
            }
        }
        return null;
    }

    public void deleteSimCard(String phoneNumber) {
        String normalizedPhoneNumber = normalizePhoneNumber(phoneNumber);
        int indexToDelete = -1;
        for (int i = 0; i < count; i++) {
            if (simCards[i].getPhoneNumber().equals(normalizedPhoneNumber)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            for (int i = indexToDelete; i < count - 1; i++) {
                simCards[i] = simCards[i + 1];
            }
            simCards[count - 1] = null;
            count--;
            System.out.println("Sim karta o'chirildi.");
        } else {
            System.out.println("Sim karta topilmadi.");
        }
    }
}