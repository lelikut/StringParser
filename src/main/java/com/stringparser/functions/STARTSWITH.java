package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class STARTSWITH implements Function {

    @Override
    public String getName() {
        return "STARTSWITH";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        if (arguments.size() < 2) {
            return null;
        }

        String str = parser.parseCode(arguments.get(0));
        str = str == null ? arguments.get(0) : str;

        for (int i = 1; i < arguments.size(); i++) {
            String argument = arguments.get(i);
            String parsed = parser.parseCode(argument);
            parsed = parsed == null ? argument : parsed;
            if (str.startsWith(parsed)) {
                return "TRUE";
            }
        }
        return "FALSE";
    }
}
