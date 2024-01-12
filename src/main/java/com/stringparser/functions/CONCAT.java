package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class CONCAT implements Function {
    @Override
    public String getName() {
        return "CONCAT";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        StringBuilder builder = new StringBuilder();
        for (String argument : arguments) {
            String parsed = parser.parseCode(argument);
            if (parsed == null) parsed = argument;
            builder.append(parsed);
        }
        return builder.toString();
    }
}
