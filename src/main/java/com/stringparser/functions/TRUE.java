package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

public class TRUE implements Function {

    @Override
    public String getName() {
        return "TRUE";
    }

    @Override
    public String parse(Parser parser, String content) {
        return "TRUE";
    }
}
