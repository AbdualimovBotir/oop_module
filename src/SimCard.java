public class SimCard {
    private String id;
    private String phoneNumber;
    private String operator;

    public SimCard(String id, String phoneNumber, String operator) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.operator = operator;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "SimCard{" +
                "id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}