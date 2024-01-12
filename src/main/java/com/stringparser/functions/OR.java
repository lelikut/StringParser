package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class OR implements Function {

    @Override
    public String getName() {
        return "OR";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        boolean result = arguments.stream()
                .map(argument -> {
                    String parsed = parser.parseCode(argument);
                    return parsed == null ? argument : parsed;
                })
                .anyMatch(s -> s.equals("TRUE"));
        return String.valueOf(result).toUpperCase();
    }
}