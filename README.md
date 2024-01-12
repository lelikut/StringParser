# StringParser

StringParser is a simple string parsing library

### Introduction

Any text inside {} is a code that will be parsed. 
Any text inside '' is a string that will not be parsed. 
You can use {} inside '' if you want it to be parsed. 

### Docs
```
num = Number
bool = TRUE/FALSE
str = String (text)
obj = Object (anything)
```


- ADD(num, num) -> num: add the 2 numbers together
- AND(bool...) -> bool: TRUE if all parameters are true, otherwise returns FALSE
- CONCAT(obj...) -> str: joins all the parameters together
- DIV(num, num) -> num: divides the first number by the second number
- ENDSWITH(obj, obj...) -> bool: TRUE if the first parameter ends with any of the following parameters
- EQ(obj, obj) -> bool: TRUE if the first and the second parameter match
- FALSE() -> bool: returns FALSE
- GT(num, num) -> bool: TRUE if the first parameter is greater than the second
- GTEQ(num, num) -> bool: TRUE if the first parameter is greater than or equal to the second
- IF(bool, obj, obj) -> obj: if the first parameter is TRUE, the second parameter is returned, otherwise the third parameter is returned
- JOIN(obj, obj...) -> str: joins all the objects from the second parameter forward, using the first parameter as a separator
- LT(num, num) -> bool: TRUE if the first parameter is less than the second
- LTEQ(num, num) -> bool: TRUE if the first parameter is less than or equal to the second
- MOD(num, num) -> num: the first value modulus (remainder) the second value
- MUL(num, num) -> num: the result of the 2 numbers being multiplied together
- NOT(bool) -> bool: the opposite boolean value
- OR(bool...) -> bool: TRUE if at least one value is true
- STARTSWITH(obj, obj...) -> bool: TRUE if the first parameter starts with any of the following parameters
- SUB(num, num) -> num: the first value subtracted by the second value
- TRUE() -> bool: returns TRUE



### Example usage:

```
StringParser parser = new StringParser();
String result = parser.parse("{CONCAT('Hello', ' ', 'world', '!')}");  // Hello world!

parser.set("name", "Lebowski");
result = parser.parse("Where is the money {name}?"); // Where is the money Lebowski?
```


### Some more examples:

```
- Online: {players} player{IF(EQ(players, 1), '', 's')}      // players = 1
Online: 1 player

- You were slain by {IF(isname, name, CONCAT(IF(STARTSWITH(name, 'a', 'e', 'i', 'o', 'u'), 'an', 'a'), ' ', name))}   // name = Creeper, isname = false
You were slain by a Creeper

- 2 + 2 = {ADD(2, 2)}
4.0

- {IF(TRUE(), 'Always true', 'I think I forgot something')}
Always true

- {JOIN(', ', 'one', 'two', 'three', 'four')}
one, two, three, four
```
