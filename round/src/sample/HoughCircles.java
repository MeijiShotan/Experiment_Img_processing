package sample;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.CvType;
//import org.opencv.aruco.Aruco;
import java.util.ArrayList;
import java.util.Arrays;
import org.opencv.calib3d.Calib3d;

class HoughCirclesRun {
    public void run(String[] args) {
        String default_file = "C:\\Users\\thuny\\OneDrive\\Desktop\\code\\target\\chitnut9.png";
        String filename = ((args.length > 0) ? args[0] : default_file);
        // Load an image
        Mat src = Imgcodecs.imread(filename, Imgcodecs.IMREAD_COLOR);
        // Check if image is loaded fine
        if( src.empty() ) {
            System.out.println("Error opening image!");
            System.out.println("Program Arguments: [image_name -- default "
                    + default_file +"] \n");
            System.exit(-1);
        }
        Mat gray=new Mat();
        Mat output = new Mat();
        long Dis_x = 0;
        long Dis_y=0;
        Mat ids = new Mat();
       
        ArrayList<Mat> corners = new ArrayList<>();
        double xdiff = 0, ydiff = 0;
        double[][] cameraParam = {{567.229305, 0.0, 659.077221, 0.0, 574.192915, 517.007571, 0.0, 0.0, 1.0}, {-0.216247, 0.03875, -0.010157, 0.001969, 0.0}};
       // Mat cameraMatrix = new Mat(3, 3, CvType.CV_32FC1);
        //Mat dstMatrix = new Mat(1,4,CvType.CV_32FC1);
        Mat cameraMatrix2 = new Mat(3, 3, CvType.CV_32FC1);
        Mat dstMatrix2 = new Mat(1, 5, CvType.CV_32FC1);
        cameraMatrix2.put(0, 0, cameraParam[0]);
        dstMatrix2.put(0, 0, cameraParam[1]);
       // Mat E =  Mat.eye(3, 3, CvType.CV_32FC1);
  
       /* cameraMatrix.put(0, 0, cameraParam[0][0]);
        cameraMatrix.put(0, 1, cameraParam[0][1]);
        cameraMatrix.put(0, 2, cameraParam[0][2]);
        cameraMatrix.put(1, 0, cameraParam[0][3]);
        cameraMatrix.put(1, 1, cameraParam[0][4]);
        cameraMatrix.put(1, 2, cameraParam[0][5]);
        cameraMatrix.put(2, 0, cameraParam[0][6]);
        cameraMatrix.put(2, 1, cameraParam[0][7]);
        cameraMatrix.put(2, 2, cameraParam[0][8]);
        dstMatrix.put(0, 0, cameraParam[1][0]);
        dstMatrix.put(0, 1, cameraParam[1][1]);
        dstMatrix.put(0, 2, cameraParam[1][2]);
        dstMatrix.put(0, 3, cameraParam[1][3]);*/
       
       
        double[][] destination = new double[cameraParam.length][];

        for (int i = 0; i < destination.length; ++i) {

            // allocating space for each row of destination array
            destination[i] = new double[cameraParam[i].length];

            for (int j = 0; j < destination[i].length; ++j) {
                destination[i][j] = cameraParam[i][j];
            }
        }
     
        // displaying destination array
        System.out.println(Arrays.deepToString(destination));  
       
    
        
       // dstMatrix1.put(0, 0, cameraParam[1]);
       
        //System.out.println(dstMatrix1);
      
        
        
       // dstMatrix.put(0, 0, cameraParam[1]);
        
        //Calib3d.undistort(src,output,cameraMatrix2,dstMatrix2);
     

        
        
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.medianBlur(gray, gray, 5);
        Mat circles = new Mat();
        Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1.0,
                (double)gray.rows()/16, // change this value to detect circles with different distances to each other
                100.0, 30.0, 15	, 30); // change the last two parameters
                // (min_radius & max_radius) to detect larger circles
        
            double[] c = circles.get(0, 0);
            Point center = new Point(Math.round(c[0]), Math.round(c[1]));
            Imgproc.circle(src, center, 1, new Scalar(0,100,100), 3, 8, 0 );
         
           // Imgproc.circle(output, center, 1, new Scalar(0,100,100), 3, 8, 0 );
             
            int radius = (int) Math.round(c[2]);
            Imgproc.circle(src, center, radius, new Scalar(255,0,255), 3, 8, 0 );
           // Imgproc.circle(output, center, radius, new Scalar(255,0,255), 3, 8, 0 );
            System.out.println(center);
            System.out.println((Math.round(c[0])));
            long Des_x=Math.round(c[0]);
            long Des_y=Math.round(c[1]);
            Dis_x= Des_x-748;
            Dis_y=448-Des_y;
            
            
        
       
     
        System.out.println(Dis_x);
        System.out.println(Dis_y);
        HighGui.imshow("input", src); 
       // HighGui.imshow("output", output);
        HighGui.waitKey();
        System.exit(0);
    }
}
public class HoughCircles {
    public static void main(String[] args) {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new HoughCirclesRun().run(args);
    }
}