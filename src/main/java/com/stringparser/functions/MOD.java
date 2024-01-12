package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

import java.util.List;

public class MOD implements Function {
    @Override
    public String getName() {
        return "MOD";
    }

    @Override
    public String parse(Parser parser, String content) {
        List<String> arguments = parser.retrieveArguments(content);
        if (arguments.size() != 2) {
            return null;
        }

        String str1 = parser.parseCode(arguments.get(0));
        String str2 = parser.parseCode(arguments.get(1));

        str1 = str1 == null ? arguments.get(0) : str1;
        str2 = str2 == null ? arguments.get(1) : str2;

        if (parser.isNumeric(str1) && parser.isNumeric(str2)) {
            return String.valueOf(parser.numeric(str1) % parser.numeric(str2));
        }

        return null;
    }
}
