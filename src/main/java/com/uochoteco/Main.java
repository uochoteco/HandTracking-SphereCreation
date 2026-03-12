package com.uochoteco;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
//import com.google.mediapipe.tasks.vision.handlandmarker.handLandmarker;
//import com.google.mediapipe.tasks.vision.handlandmarker.handLandmarkerResult;
//import com.google.mediapipe.tasks.vision.handlandmarker.handLandmarker.HandLandmarkerOptions;
//import com.google.mediapipe.tasks.core.BaseOptions;

public class Main extends JPanel {

    private BufferedImage image;
    public static void main(String[] args) 
    {
        OpenCV.loadShared();
        System.out.println("Hello world!");
        System.out.println("Version: " + Core.VERSION);
        JFrame frame = new JFrame("Hand Tracking Sphere Creation Project");
        Main panel = new Main();
        VideoCapture camera = new VideoCapture(0);
        Mat frameMatrix = new Mat();
        frame.add(panel);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        if(camera.isOpened() == false)
            {
                System.out.println("Camera Fail :(");
                return;
            }
        
        while(true)
            {
                if(camera.read(frameMatrix))
                    {
                        panel.image = matrixToBufferedImage(frameMatrix);
                        panel.repaint();
                    }
            }
    }

    public static BufferedImage matrixToBufferedImage(Mat matrix)
    {
        int cols = matrix.cols();
        int rows = matrix.rows();
        int elementSize = (int)matrix.elemSize();
        byte[] data = new byte[cols * rows * elementSize];
        matrix.get(0, 0, data);
        int type = matrix.channels() == 1 ? BufferedImage.TYPE_BYTE_GRAY: BufferedImage.TYPE_3BYTE_BGR;
        BufferedImage image = new BufferedImage(cols, rows, type);
        System.arraycopy(data, 0, ((DataBufferByte)image.getRaster().getDataBuffer()).getData(), 0, data.length);
        return image;
    }

    @Override
    protected void paintComponent(Graphics x)
    {
        super.paintComponent(x);
        if(image != null)
            {
                x.drawImage(image, 0, 0, this);
            }
    }
}