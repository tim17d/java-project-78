package hexlet.code;

public class StringSchema {
    private boolean isRequired = false;
    private Integer minLength;
    private String substring;

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int value) {
        this.minLength = value;
        return this;
    }

    public StringSchema contains(String str) {
        this.substring = str;
        return this;
    }

    public boolean isValid(String str) {
        try {
            if (this.isRequired) {
                if (str.isEmpty()) {
                    return false;
                }
            }
            if (this.minLength != null) {
                if (str.length() < this.minLength) {
                    return false;
                }
            }
            if (this.substring != null) {
                if (!str.contains(this.substring)) {
                    return false;
                }
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
