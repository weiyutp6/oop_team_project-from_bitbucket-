import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        DateChooser dateChooser1 = DateChooser.getInstance("yyyyMMdd");
        DateChooser dateChooser2 = DateChooser.getInstance("yyyyMMdd");
        JTextField showDate1 = new JTextField("選擇最早出發日期");
        JTextField showDate2 = new JTextField("選擇最晚出發日期");

        dateChooser1.register(showDate1);
        dateChooser2.register(showDate2);

        JFrame jf = new JFrame("選擇出發日期區間");
        jf.add(showDate1, BorderLayout.NORTH);
        jf.add(showDate2, BorderLayout.CENTER);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(DateChooser.isDate2NotBeforeDate1("20200610", "20200702"));
    }
}
