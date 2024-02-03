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
        Integer value;
        try {
            value = (Integer) intObj;
        } catch (ClassCastException e) {
            return false;
        }
        return this.requiredCheck(value) && this.positiveCheck(value) && this.rangeCheck(value);
    }

    private boolean requiredCheck(Integer value) {
        if (this.isRequired) {
            return value != null;
        }
        return true;
    }

    private boolean positiveCheck(Integer value) {
        if (this.isPositive) {
            return value == null || value > 0;
        }
        return true;
    }

    private boolean rangeCheck(Integer value) {
        if (this.minValue != null && this.maxValue != null) {
            return value != null && value >= this.minValue && value <= this.maxValue;
        }
        return true;
    }
}
