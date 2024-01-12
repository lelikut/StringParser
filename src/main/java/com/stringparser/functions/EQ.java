package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class EQ implements Function {

    @Override
    public String getName() {
        return "EQ";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        if (arguments.size() != 2) {
            return null;
        }

        String first = parser.parseCode(arguments.get(0));
        String second = parser.parseCode(arguments.get(1));

        first = first == null ? arguments.get(0) : first;
        second = second == null ? arguments.get(1) : second;

        return String.valueOf(first.equals(second)).toUpperCase();
    }
}
