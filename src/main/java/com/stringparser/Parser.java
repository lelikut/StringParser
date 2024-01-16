package com.stringparser;

import com.stringparser.functions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private final char OPEN_BRACKET = '{';
    private final char CLOSE_BRACKET = '}';
    private final char OPEN_PARENTHESIS = '(';
    private final char CLOSE_PARENTHESIS = ')';
    private final char SINGLE_QUOTE = '\'';
    //    private final char DOUBLE_QUOTE = '"';
    private final char COMMA = ',';

    private Map<String, String> variables = new HashMap<>();
    private Map<String, Function> functions = new HashMap<>();

    public Parser() {
        functions.put("ADD", new ADD());
        functions.put("AND", new AND());
        functions.put("CONCAT", new CONCAT());
        functions.put("DIV", new DIV());
        functions.put("ENDSWITH", new ENDSWITH());
        functions.put("EQ", new EQ());
        functions.put("FALSE", new FALSE());
        functions.put("GT", new GT());
        functions.put("GTEQ", new GTEQ());
        functions.put("IF", new IF());
        functions.put("JOIN", new JOIN());
        functions.put("LT", new LT());
        functions.put("LTEQ", new LTEQ());
        functions.put("MOD", new MOD());
        functions.put("MUL", new MUL());
        functions.put("NOT", new NOT());
        functions.put("OR", new OR());
        functions.put("STARTSWITH", new STARTSWITH());
        functions.put("SUB", new SUB());
        functions.put("TRUE", new TRUE());
    }

    public void set(String key, String value) {
        variables.put(key, value);
    }

    public void unset(String key) {
        variables.remove(key);
    }

    public void clearVariables() {
        variables.clear();
    }

    public String parse(String line) {
        int currentIndex = line.indexOf(OPEN_BRACKET);
        if (currentIndex < 0) {
            return line;
        }

        String content = getContent(line, currentIndex);
        if (content == null) {
            return line;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(line, 0, currentIndex);

        while (true) {
            String parsed = parseCode(extractCodeBody(content));
            builder.append(parsed == null ? content : parsed);

            currentIndex += content.length();
            int nextIndex = line.indexOf(OPEN_BRACKET, currentIndex);
            if (nextIndex == -1) {
                builder.append(line, currentIndex, line.length());
                return builder.toString();
            }

            String next = getContent(line, nextIndex);
            if (next == null) {
                return line;
            }

            builder.append(line, currentIndex, nextIndex);
            currentIndex = nextIndex;
            content = next;
        }
    }

    public double numeric(String line) {
        return Double.parseDouble(line);
    }

    public boolean isNumeric(String line) {
        return line.matches("-?\\d+(\\.\\d+)?");
    }

    public boolean isString(String line) {
        line = line.trim();
        if (line.length() < 2) {
            return false;
        }
        int index = line.indexOf(SINGLE_QUOTE);
        if (index == -1) {
            return false;
//            index = line.indexOf(DOUBLE_QUOTE);
//            if (index == -1) {
//                return false;
//            }
        }
        String str = getContent(line, index);
        return str != null && str.equals(line);
    }

    public String extractString(String line) {
        line = line.trim();
        return line.substring(1, line.length() - 1);
    }

    public String parseCode(String line) {
        if (variables.containsKey(line)) {
            return variables.get(line);
        }

        if (isString(line)) {
            return parse(extractString(line));
        }

        int index = line.indexOf(OPEN_PARENTHESIS);
        if (index <= 0) {
            return null;
        }

        String functionName = line.substring(0, index);
        Function function = functions.get(functionName);
        if (function == null) {
            return null;
        }

        return function.parse(this, extractFunctionBody(line));
    }

    public List<String> retrieveArguments(String line) {
        List<String> arguments = new ArrayList<>();
        int index = 0;
        String content = getContent(line, index);
        while (content != null && index <= line.length()) {
            arguments.add(content.trim());
            index += content.length() + 1;
            content = getContent(line, index);
        }
        return arguments;
    }

    public String extractFunctionBody(String line) {
        line = line.trim();
        int index = line.indexOf(OPEN_PARENTHESIS);
        return line.substring(index + 1, line.length() - 1).trim();
    }

    public String extractCodeBody(String line) {
        line = line.trim();
        int index = line.indexOf(OPEN_BRACKET);
        return line.substring(index + 1, line.length() - 1).trim();
    }

    public String getContent(String line, int start) {
        StringType stringType = StringType.NONE;
        int counterString = 0;
        int counterOP = 0;
        int counterCP = 0;
        int counterOB = 0;
        int counterCB = 0;

        // TODO: add additional checks to be sure that close bracket goes before open bracket

        // TODO: add support for double-quoted strings

        for (int index = start; index < line.length(); index++) {
            char token = line.charAt(index);
            boolean shouldCheck = false;

            if (token == OPEN_BRACKET) {
                counterOB++;
            } else if (token == CLOSE_BRACKET) {
                counterCB++;
            } else if (token == OPEN_PARENTHESIS) {
                counterOP++;
            } else if (token == CLOSE_PARENTHESIS) {
                counterCP++;
                shouldCheck = true;
            } else if (token == SINGLE_QUOTE) {
                if (stringType == StringType.NONE) {
                    stringType = StringType.SINGLE_QUOTE;
                    counterString++;
                } else if (stringType == StringType.SINGLE_QUOTE) {
                    counterString++;
                }

                if (counterString % 2 == 0) {
                    shouldCheck = true;
                }
//            } else if (token == DOUBLE_QUOTE) {
//                if (stringType == StringType.NONE) {
//                    stringType = StringType.DOUBLE_QUOTE;
//                    counterString++;
//                } else if (stringType == StringType.DOUBLE_QUOTE) {
//                    counterString++;
//                }
//
//                if (counterString % 2 == 0) {
//                    shouldCheck = true;
//                }
            } else if (token == COMMA) {
                if (counterString % 2 == 0 && counterOB == counterCB && counterOP == counterCP) {
                    return line.substring(start, index);
                }
            }

            if (shouldCheck && counterOB == counterCB && counterOP == counterCP && counterString % 2 == 0) {
                return line.substring(start, index + 1);
            }

        }

        /*
            TODO
         */
        if (counterOB == counterCB && counterOP == counterCP && counterString % 2 == 0 && start < line.length()) {
            return line.substring(start);
        }

        return null;
    }

}

enum StringType {

    NONE, SINGLE_QUOTE, DOUBLE_QUOTE;

}