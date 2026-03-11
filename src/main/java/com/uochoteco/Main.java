package com.uochoteco;
import org.opencv.core.Core;
import  nu.pattern.OpenCV;
public class Main {
    public static void main(String[] args) {
        OpenCV.loadShared();
        System.out.println("Hello world!");
        System.out.println("Version: " + Core.VERSION);
    }
}