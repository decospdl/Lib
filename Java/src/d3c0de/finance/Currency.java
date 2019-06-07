package d3c0de.finance;

/**
 * Classe enum para classificação dos tipos de moedas existentes.
 *
 * @author d3c0de <decospdl@gmail.com>
 * @version 1.0.0
 * @see Money
 */
public enum Currency {
    BRL("R$", "real", "centavo"),
    EUR("€", "euro", "cêntimo"),
    USD("US$", "dólar", "cent"),
    JPY("¥", "iene", ""),
    GBP("£", "pound", "penny");

    private String symbol, unity, subUnity;

    private Currency(String symbol, String unity, String subUnity) {
        this.symbol = symbol;
        this.unity = unity;
        this.subUnity = subUnity;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUnity() {
        return unity;
    }

    public String getSubUnity() {
        return subUnity;
    }
}
