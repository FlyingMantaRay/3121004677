package com.zjr;

public class ShortStringException extends Exception {

    public ShortStringException() {
        super();
    }
    //默认构造方法，不接受任何参数。
    // 它只是调用了父类（Exception）的构造方法，使用 super()。
    // 这个构造方法可用于创建一个 com.zjr.ShortStringException，而不指定自定义错误消息

    public ShortStringException(String message) {
        super(message);
    }
    //这个构造方法接受一个自定义错误消息作为参数
    //它通过调用父类构造方法，使用 super(message)，以便可以创建一个包含自定义错误消息的 com.zjr.ShortStringException

    public ShortStringException(String message, Throwable cause) {
        super(message, cause);
    }
    //这个构造方法接受一个自定义错误消息和一个可抛出的原因（Throwable）作为参数

    public ShortStringException(Throwable cause) {//接受一个可抛出的原因作为参数
        super(cause);
    }

}