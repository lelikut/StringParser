package com.stringparser.functions;

import com.stringparser.Function;
import com.stringparser.Parser;

public class FALSE implements Function {

    @Override
    public String getName() {
        return "FALSE";
    }

    @Override
    public String parse(Parser parser, String content) {
        return "FALSE";
    }
}
