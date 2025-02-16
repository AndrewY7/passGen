import java.security.SecureRandom;

class Password {
    private String pass_strength;
    private int pass_length;

    public Password(String s) {
        pass_strength = s;
        pass_length = s.length();
    }

    public int Password_strength() {
        String s = this.pass_strength;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        int type;
        int score = 0;

        for (int i = 0; i < s.length(); i++) {
            type = Alphabet.classifyCharacter(s.charAt(i));
            if (type == 1) {
                hasDigit = true;
                score += 20;
            } else if (type == 2) {
                hasUpper = true;
                score += 20;
            } else if (type == 3) {
                hasLower = true;
                score += 20;
            } else if (type == 4) {
                hasSymbol = true;
                score += 10;
            }
        }

        if (s.length() >= 12) { score += 20; }

        return score;
    }

    public String determineScore() {
        int score = this.Password_strength();

        if (score == 100) {return "Great Password!";}
        else if (score >= 80) {return "Good Password!";}
        else if (score >= 60) {return "Fair Password!";}
        else if (score >= 40) {return "Weak Password!";}
        else {return "Very Weak Password!";}
    }

    @Override
    public String toString() {
        return pass_strength;
    }
}