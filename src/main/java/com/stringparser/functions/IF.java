package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class IF implements Function {

    @Override
    public String getName() {
        return "IF";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        if (arguments.size() != 3) {
            return null;
        }

        String condition = parser.parseCode(arguments.get(0));
        if (condition == null) {
            return null;
        }

        if (condition.equals("TRUE")) {
            return parser.parseCode(arguments.get(1));
        } else if (condition.equals("FALSE")) {
            return parser.parseCode(arguments.get(2));
        } else {
            return null;
        }
    }
}
