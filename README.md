# Data Validator

---

### Hexlet tests and linter status:
[![Actions Status](https://github.com/tim17d/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/tim17d/java-project-78/actions)
[![Actions Status](https://github.com/tim17d/java-project-78/actions/workflows/java-ci.yml/badge.svg)](https://github.com/tim17d/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/c9d53e2681b80d09668a/maintainability)](https://codeclimate.com/github/tim17d/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/c9d53e2681b80d09668a/test_coverage)](https://codeclimate.com/github/tim17d/java-project-78/test_coverage)

---

### Description:
Data validator is a library that can be used to validate some types of data in Java: String, Integer and Map. To get started follow these simple steps below:

1. Create a new Validator:
```java
Validator v = new Validator();
```

2. Select one of the supported schemes:

#### String Schema
String schema contains three validation methods. Each method can be chained in fluent style:

`required()` - string can not be null or empty

`minLength()` - string must be longer than limiter, passed as parameter (or the same length)

`contains()` - string must contain the substring, passed as parameter

```java
var v = new Validator();
var stringSchema = v.string();

stringSchema.isValid(""); // true
stringSchema.isValid(null); // true

stringSchema.required();
stringSchema.isValid(""); // false
stringSchema.isValid(null); // false
stringSchema.isValid("Hello!"); // true

stringSchema.minLength(2);
stringSchema.isValid("Ye"); // true
stringSchema.isValid("Yeyeyes"); // true
stringSchema.isValid("Y"); // false

stringSchema.contains("st");
stringSchema.isValid("string"); // true;
stringSchema.isValid("Strong string"); // true
stringSchema.isValid("Hello world!"); // false
```

#### Number Schema
Number schema contains three validation methods. Each method can be chained in fluent style:

`required()` - number can not be null, passed data must be type Integer

`positive()` - number must be more than zero

`range()` - number must be greater than the minimum value passed as a parameter and less than the maximum value

```java
var v = new Validator();
var numberSchema = v.number();

numberSchema.isValid(null); // true
numberSchema.isValid(0); // true

numberSchema.required();
numberSchema.isValid(null); // false
numberSchema.isValid(0); // true

numberSchema.positive();
numberSchema.isValid(0); // false
numberSchema.isValid(-7); // false
numberSchema.isValid(1526); // true

numberSchema.range(10, 25);
numberSchema.isValid(0); // false
numberSchema.isValid(10); // true
numberSchema.isValid(20); // true
numberSchema.isValid(25); // true
numberSchema.isValid(100); // false
```

#### Map Schema
Map schema contains three validation methods. Each method can be chained in fluent style:

`required()` - passed value must not equal null and must be a Map type

`sizeOf()` - number of key-value pairs must not be less than the transferred value

`shape()` - allows you to describe validation for the values of each key of a Map object

```java
var v = new Validator();
var mapSchema = v.map();

mapSchema.isValid(null); // true

mapSchema.required();
mapSchema.isValid(null); // false
mapsSchema.isValid(Map.of()); // true

mapSchema.sizeof(2);
mapSchema.isValid(Map.of()); // false
mapSchema.isValid(Map.of(
                  "key1", "value1",
                  "key2", "value2"
                  )); // true

///

var v = new Validator();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(3));
schemas.put("age", v.number().required().range(0, 100));

mapSchema.shape(schemas);

var human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
human1.put("age", 30);
mapSchema.isValid(human1); // true

var human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", "S");
human2.put("age", 30);
mapSchema.isValid(human2); // false

var human3 = new HashMap<>();
human3.put("firstName", "John");
human3.put("lastName", null);
human3.put("age", 120);
mapSchema.isValid(human3); // false

var human4 = new HashMap<>();
human4.put("firstName", null);
human4.put("lastName", null);
human4.put("age", 120);
mapSchema.isValid(human4); // false
```
