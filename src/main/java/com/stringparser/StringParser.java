package com.stringparser;

public class StringParser {

    Parser parser;

    public StringParser() {
        parser = new Parser();
    }

    public void set(String key, String value) {
        parser.set(key, value);
    }

    public void unset(String key) {
        parser.unset(key);
    }

    public void unsetAll() {
        parser.clearVariables();
    }

    public String parse(String content) {
        return parser.parse(content);
    }

}
