package hexlet.code;

public class NumberSchema extends BaseSchema {
    private boolean isPositive = false;
    private Integer minValue;
    private Integer maxValue;

    @Override
    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(int begin, int end) {
        this.minValue = begin;
        this.maxValue = end;
        return this;
    }

    @Override
    public boolean isValid(Object intObj) {
        try {
            var value = (Integer) intObj;
            if (this.isRequired) {
                if (value == null) {
                    return false;
                }
            }
            if (this.isPositive) {
                if (value != null && value <= 0) {
                    return false;
                }
            }
            if (this.minValue != null && this.maxValue != null) {
                if (value < this.minValue || value > this.maxValue) {
                    return false;
                }
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
