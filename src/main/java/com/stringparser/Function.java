package com.stringparser;

public interface Function {

    String getName();

    String parse(Parser parser, String content);

}
