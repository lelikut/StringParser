package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

public class NOT implements Function {

    @Override
    public String getName() {
        return "NOT";
    }

    @Override
    public String parse(Parser parser, String content) {
        String parsed = parser.parseCode(content);
        parsed = parsed == null ? content : parsed;
        if (parsed.equals("TRUE")) {
            return "FALSE";
        } else if (parsed.equals("FALSE")) {
            return "TRUE";
        } else {
            return null;
        }
    }
}
