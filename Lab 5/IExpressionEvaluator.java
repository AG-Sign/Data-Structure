package com.company;

public interface IExpressionEvaluator {
    public String infixToPostfix(String expression);
    public int evaluate(String expression);
}
