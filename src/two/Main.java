package two;

import two.exceptoins.AmountException;
import two.exceptoins.CustomerException;
import two.exceptoins.ProductException;
import two.exceptoins.TooMuchSaleException;

import java.util.Random;

import static two.Gender.MALE;

/**
 * Класс «Эмуляция интернет-магазина».
 * 1. Написать классы покупатель (ФИО, возраст, телефон), товар (название, цена) и
 * заказ (объект покупатель, объект товар, целочисленное количество).
 * 2. Создать массив покупателей (инициализировать 2 элемента), массив товаров
 * (инициализировать 5 элементов) и массив заказов (пустой на 5 элементов).
 * 3. Создать статический метод «совершить покупку» со строковыми параметрами,
 * соответствующими полям объекта заказа. Метод должен вернуть объект заказа.
 * 4. Если в метод передан несуществующий покупатель – метод должен выбросить исключение CustomerException,
 * если передан несуществующий товар, метод должен выбросить исключение ProductException, если было передано
 * отрицательное или слишком больше значение количества (например, 100), метод должен выбросить исключение AmountException.
 * Вызвать метод совершения покупки несколько раз таким образом, чтобы заполнить массив покупок возвращаемыми значениями.
 * Обработать исключения следующим образом (в заданном порядке):
 * – если был передан неверный товар – вывести в консоль сообщение об ошиб-
 * ке, не совершать данную покупку;
 * – если было передано неверное количество – купить товар в количестве 1;
 * – если был передан неверный пользователь – завершить работу приложения
 * с исключением.
 * Вывести в консоль итоговое количество совершённых покупок после выполне-
 * ния основного кода приложения.
 * <p>
 * Домашнее задание
 * <p>
 * Добавить перечисление с гендерами. В класс покупателя добавить свойство «пол» со значением
 * созданного перечисления. Добавить геттеры, сеттеры.
 * <p>
 * Добавить в приложение Магазин учет цены товара - в Заказ добавить поле стоимость.
 * Добавить перечисление с размерами скидок - 0, 5, 10, 15, 20%.
 * Написать метод, при вызове которого на переданный тип товара незначается рандомная
 * скидка из перечисления (меняем значение поля price)
 * <p>
 * ** Товарам добавить категорию. Задать категории Стандарт и Премиум. Если на товар категории Премиум
 * назначилась скидка более 15%, выбросить исключение TooMuchSaleException(msg),
 * сообщение с ошибкой вывести в консоль, цену товара не менять.
 **/
public class Main {
    static Customer[] customers;
    static Product[] products;

    public static void main(String[] args) {

        customers = new Customer[]{new Customer("Ivanov Ivan", 33, "+333333333", MALE),
                new Customer("Dart Veider", 77, "+666666666", MALE)};

        Product tea = new Product("tea", 30, Category.STANDART);
        Product water = new Product("water", 100, Category.PREMIUM);
        Product bread = new Product("bread", 400, Category.STANDART);
        Product apple = new Product("apple", 120, Category.STANDART);
        Product ice = new Product("ice", 600, Category.PREMIUM);

        products = new Product[]{tea, water, bread, apple, ice};

        try {
            makeSale(water);  //скидка на воду
        } catch (TooMuchSaleException e) {
            System.out.println(e.getMessage());
        }


        Order[] orders = new Order[5];

        String[] phones = {"+333333333", "+333333333", "+666666666", "3332222", "11111111"};
        String[] productsStr = {"water", "bread", "apple", "ice", "tea"};
        int[] amounts = {1, 2, -1, 0, 1000};


        for (int i = 0; i < orders.length; i++) {
            float price = 0;
            try {
                for (Product prod : products) {
                    if (productsStr[i].equals(prod.getTitle())) {
                        price = prod.getPrice();
                    }
                }
                orders[i] = makePurchase(phones[i], productsStr[i], amounts[i], price);
            } catch (ProductException e) {
                System.out.println(e.getMessage());
            } catch (AmountException e) {
                orders[i] = makePurchase(phones[i], productsStr[i], 1, price);
                System.out.println(e.getMessage());
            } catch (CustomerException e) {
                System.out.println(e.getMessage());
            }
        }
        int counter = 0;
        for (Order ord : orders) {
            if (ord != null) {
                counter++;
            }

        }

        System.out.printf("Совершено %d покупок", counter);
        System.out.println();

        for (Order order : orders) {
            if (order != null) {
                System.out.println(order.toString());
            }
        }


    }

    public static Order makePurchase(String phone, String title, int amount, float price) throws CustomerException, ProductException, AmountException {
        Customer customer = null;
        Product product = null;
        for (Customer cust : customers) {
            if (cust.getPhone().equals(phone)) {
                customer = cust;
            }
            for (Product prod : products) {
                if (prod.getTitle().equals(title)) {
                    product = prod;
                }
            }
        }
        if (customer == null) {
            throw new CustomerException("Phone number not found");
        }
        if (product == null) {
            throw new ProductException("The title not found");
        }
        if (amount > 100 || amount < 1) {
            throw new AmountException("Amount is not correct");
        }
        return new Order(customer, product, amount, product.getPrice());
    }

    public static void makeSale(Product product) throws TooMuchSaleException {
        Sale[] sales = Sale.values();
        Random random = new Random();
        int rand = random.nextInt(sales.length);
        Sale sale = sales[rand];
        switch (sale) {
            case FIVE:
                product.setPrice(product.getPrice() - (product.getPrice() / 100 * 5));
            case TEN:
                product.setPrice(product.getPrice() - (product.getPrice() / 100 * 10));
            case FIFTEEN:
                product.setPrice(product.getPrice() - (product.getPrice() / 100 * 15));
            case TWENTY:
                if (product.getCategory().equals(Category.PREMIUM)) {
                    throw new TooMuchSaleException("This sale is too much for our premium product");
                } else {
                    product.setPrice(product.getPrice() - (product.getPrice() / 100 * 20));
                }
        }


    }
}
