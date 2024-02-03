package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength;
    private String substring;

    @Override
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

    @Override
    public boolean isValid(String str) {
        return this.requiredCheck(str) && this.minLengthCheck(str) && this.containsCheck(str);
    }

    private boolean requiredCheck(String s) {
        if (this.isRequired) {
            return s != null && !s.isEmpty();
        }
        return true;
    }

    private boolean minLengthCheck(String s) {
        if (this.minLength != null) {
            return s != null && s.length() >= this.minLength;
        }
        return true;
    }

    private boolean containsCheck(String s) {
        if (this.substring != null) {
            return s != null && s.contains(this.substring);
        }
        return true;
    }
}
