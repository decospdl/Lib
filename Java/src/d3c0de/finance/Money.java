package d3c0de.finance;

import d3c0de.validate.Validate;

/**
 * Classe para controle de moedas, facilitado para cálculos matemáticos
 * financeiro com dinheiro.
 *
 * @author d3c0de <decospdl@gmail.com>
 * @version 1.0.0
 * @see Currency
 */
public class Money implements Comparable<Money> {

    public static final int SIMPLE = 0;
    public static final int SYMBOL = 1;
    public static final int EXTENSION = 2;

    private int unity, subUnity;
    private Currency currency;

    public Money(String money, Currency currency) {
        setMoney(money);
        this.currency = currency;
    }

    public Money setMoney(String money) {
        this.subUnity = Integer.parseInt(money.substring(money.length() - 2, money.length()));
        this.unity = Integer.parseInt(money.substring(0, money.length() - 3));
        return this;
    }

    public int getUnity() {
        return unity;
    }

    public int getSubUnity() {
        return subUnity;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money setUnity(int unity) {
        Validate.rangeBetween(unity, 0, 99);
        this.unity = unity;
        return this;
    }

    public Money setSubUnity(int subUnity) {
        Validate.rangeBetween(subUnity, 0, 99);
        this.subUnity = subUnity;
        return this;
    }

    public Money setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public String getMoney(int typeId) {
        String[] type = new String[]{unity + "," + subUnity,
            currency.getSymbol() + " " + unity + "," + subUnity,
            unity + " " + currency.getUnity() + " " + subUnity + " " + currency.getSubUnity()};
        return type[typeId];
    }

    public int compareUnity(Money money) {
        if (unity > money.getUnity()) {
            return 1;
        } else if (unity < money.getUnity()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int compareSubUnity(Money money) {
        if (subUnity > money.getSubUnity()) {
            return 1;
        } else if (subUnity < money.getSubUnity()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Money money) {
        int unity = compareUnity(money);
        if(unity == 0){
            return  compareSubUnity(money);   
        }
        return unity;
    }

    @Override
    public String toString() {
        return currency.getSymbol() + " " + unity + "," + subUnity;
    }
}
