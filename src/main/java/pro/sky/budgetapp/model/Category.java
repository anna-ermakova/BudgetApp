package pro.sky.budgetapp.model;

public enum Category {
    FOOD("Продукты питания"), CLOTHES("Одажда и аксессуары"), FUN("Развлечения"), TRANSPORT("Транспорт"), HOBBY("Хобби");
    private final String text;

    Category(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
