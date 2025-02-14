public class Alphabet {
   public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
   public static final String DIGITS = "0123456789";
   public static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;':,.<>?"; 

   private final StringBuilder sb;

   public Alphabet(boolean hasUpper, boolean hasLower, boolean hasDigit, boolean hasSymbol) {
        sb = new StringBuilder();
        if (hasUpper) sb.append(UPPERCASE);
        if (hasLower) sb.append(LOWERCASE);
        if (hasDigit) sb.append(DIGITS);
        if (hasSymbol) sb.append(SYMBOLS);
   }

   public String getAlphabet() {
       return sb.toString();
   }
}
