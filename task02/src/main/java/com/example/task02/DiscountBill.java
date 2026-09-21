package com.example.task02;

public class DiscountBill extends Bill {

    private final int discount;

    /**
     * Конструктор счета со скидкой
     *
     * @param discount скидка в процентах (от 0 до 100)
     */
    public DiscountBill(int discount) {
        super();
        this.discount = discount;
    }

    /**
     * Возвращает процент скидки
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Возвращает итоговую стоимость покупки с учетом скидки.
     * Переопределяет метод базового класса Bill.
     */
    @Override
    public long getPrice() {
        long originalPrice = super.getPrice();
        return originalPrice - (originalPrice * discount / 100);
    }

    /**
     * Возвращает абсолютный размер скидки в деньгах
     * (разница между исходной суммой и суммой со скидкой)
     */
    public long getAbsoluteDiscount() {
        return super.getPrice() - getPrice();
    }
}