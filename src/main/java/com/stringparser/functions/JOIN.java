package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class JOIN implements Function {

    @Override
    public String getName() {
        return "JOIN";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        if (arguments.size() < 2) {
            return null;
        }

        for (int i = 1; i < arguments.size(); i++) {
            String argument = arguments.get(i);
            String parsed = parser.parseCode(argument);
            if (parsed == null) parsed = argument;
            arguments.set(i, parsed);
        }

        String delimeter = arguments.get(0);
        String parsed = parser.parseCode(delimeter);

        return String.join(parsed == null ? delimeter : parsed, arguments.subList(1, arguments.size()));
    }
}
