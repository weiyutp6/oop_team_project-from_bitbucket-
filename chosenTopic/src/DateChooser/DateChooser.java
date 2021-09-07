import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * 日期選擇器，可以指定日期的顯示格式 
 */
public class DateChooser extends JPanel {

    private static final long serialVersionUID = 4529266044762990227L;

    private Date initDate;
    private Calendar now = Calendar.getInstance();
    private Calendar select;
    private JPanel monthPanel;//月曆
    private JP1 jp1;//四塊面板,組成
    private JP2 jp2;
    private JP3 jp3;
    private JP4 jp4;
    private Font font = new Font("宋體", Font.PLAIN, 12);
    private final LabelManager lm = new LabelManager();
    private SimpleDateFormat sdf;
    private boolean isShow = false;
    private Popup pop;
    private String chosenDate;

    public String getChosenDate() {
        return chosenDate;
    }

    private JComponent showDate;

    public static DateChooser getInstance() {
        return new DateChooser();
    }

    public static DateChooser getInstance(Date date) {
        return new DateChooser(date);
    }

    public static DateChooser getInstance(String format) {
        return new DateChooser(format);
    }

    public static DateChooser getInstance(Date date, String format) {
        return new DateChooser(date, format);
    }

    /**
     * Creates a new instance of DateChooser
     */
    private DateChooser() {
        this(new Date());
    }

    private DateChooser(Date date) {
        this(date, "yyyy年MM月dd日");
    }

    private DateChooser(String format) {
        this(new Date(), format);
    }

    private DateChooser(Date date, String format) {
        initDate = date;
        sdf = new SimpleDateFormat(format);
        select = Calendar.getInstance();
        select.setTime(initDate);
        initPanel();
    }

    /**
     * 是否允許使用者選擇
     */
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        showDate.setEnabled(b);
    }

    /**
     *得到當前選擇框的日期
     */
    public Date getDate() {
        return select.getTime();
    }

    public String getStrDate() {
        return sdf.format(select.getTime());
    }

    public String getStrDate(String format) {
        sdf = new SimpleDateFormat(format);
        return sdf.format(select.getTime());
    }

    //根據初始化的日期,初始化面板
    private void initPanel() {
        monthPanel = new JPanel(new BorderLayout());
        monthPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel up = new JPanel(new BorderLayout());
        up.add(jp1 = new JP1(), BorderLayout.NORTH);
        up.add(jp2 = new JP2(), BorderLayout.CENTER);
        monthPanel.add(jp3 = new JP3(), BorderLayout.CENTER);
        monthPanel.add(up, BorderLayout.NORTH);
        monthPanel.add(jp4 = new JP4(), BorderLayout.SOUTH);
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {

            }

            public void ancestorRemoved(AncestorEvent event) {

            }

            //只要祖先元件一移動,馬上就讓popup消失
            public void ancestorMoved(AncestorEvent event) {
                hidePanel();
            }
        });
    }

    public void register(final JComponent showDate) {
        this.showDate = showDate;

        showDate.setRequestFocusEnabled(true);
        showDate.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                showDate.requestFocusInWindow();
            }
        });
        this.setBackground(Color.WHITE);
        this.add(showDate, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(90, 25));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        showDate.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    showDate.setForeground(Color.RED);
                }
            }

            public void mouseExited(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    showDate.setForeground(Color.BLACK);
                }
            }

            public void mousePressed(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setForeground(Color.CYAN);
                    if (isShow) {
                        hidePanel();
                    } else {
                        showPanel(showDate);
                    }
                }
            }

            public void mouseReleased(MouseEvent me) {
                if (showDate.isEnabled()) {
                    showDate.setForeground(Color.BLACK);
                }
            }
        });
        showDate.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                hidePanel();
            }

            public void focusGained(FocusEvent e) {

            }
        });
    }

    //根據新的日期重新整理
    private void refresh() {
        jp1.updateDate();
        jp2.updateDate();
        jp3.updateDate();
        jp4.updateDate();
        SwingUtilities.updateComponentTreeUI(this);
    }

    //提交日期
    private String commit() {
        //TODO add other components here
        if (showDate instanceof JTextField) {
            ((JTextField) showDate).setText(sdf.format(select.getTime()));
        }else if (showDate instanceof JLabel) {
            ((JLabel) showDate).setText(sdf.format(select.getTime()));
        }
        chosenDate = ((JTextField) showDate).getText();
        System.out.println(chosenDate);

        hidePanel();
        return chosenDate;
    }

    //隱藏日期選擇面板
    private void hidePanel() {
        if (pop != null) {
            isShow = false;
            pop.hide();
            pop = null;
        }
    }

    //顯示日期選擇面板
    private void showPanel(Component owner) {
        if (pop != null) {
            pop.hide();
        }
        Point show = new Point(0, showDate.getHeight());
        SwingUtilities.convertPointToScreen(show, showDate);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = show.x;
        int y = show.y;
        if (x < 0) {
            x = 0;
        }
        if (x > size.width - 295) {
            x = size.width - 295;
        }
        if (y < size.height - 170) {
        } else {
            y -= 188;
        }
        pop = PopupFactory.getSharedInstance().getPopup(owner, monthPanel, x, y);
        pop.show();
        isShow = true;
    }

    /**
     * 最上面的面板用來顯示月份的增減
     */
    private class JP1 extends JPanel {
        private static final long serialVersionUID = -5638853772805561174L;
        JLabel yearleft, yearright, monthleft, monthright, center, centercontainer;

        public JP1() {
            super(new BorderLayout());
            this.setBackground(new Color(160, 185, 215));
            initJP1();
        }

        private void initJP1() {
            yearleft = new JLabel("  <<", JLabel.CENTER);
            yearleft.setToolTipText("上一年");
            yearright = new JLabel(">>  ", JLabel.CENTER);
            yearright.setToolTipText("下一年");
            yearleft.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
            yearright.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

            monthleft = new JLabel("  <", JLabel.RIGHT);
            monthleft.setToolTipText("上一月");
            monthright = new JLabel(">  ", JLabel.LEFT);
            monthright.setToolTipText("下一月");
            monthleft.setBorder(BorderFactory.createEmptyBorder(2, 30, 0, 0));
            monthright.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 30));

            centercontainer = new JLabel("", JLabel.CENTER);
            centercontainer.setLayout(new BorderLayout());
            center = new JLabel("", JLabel.CENTER);

            centercontainer.add(monthleft, BorderLayout.WEST);
            centercontainer.add(center, BorderLayout.CENTER);
            centercontainer.add(monthright, BorderLayout.EAST);

            this.add(yearleft, BorderLayout.WEST);
            this.add(centercontainer, BorderLayout.CENTER);
            this.add(yearright, BorderLayout.EAST);
            this.setPreferredSize(new Dimension(295, 25));

            updateDate();

            yearleft.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    yearleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    yearleft.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    yearleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    yearleft.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.YEAR, -1);
                    yearleft.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    yearleft.setForeground(Color.BLACK);
                }
            });
            yearright.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    yearright.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    yearright.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    yearright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    yearright.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.YEAR, 1);
                    yearright.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    yearright.setForeground(Color.BLACK);
                }
            });
            monthleft.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    monthleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    monthleft.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    monthleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    monthleft.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.MONTH, -1);
                    monthleft.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    monthleft.setForeground(Color.BLACK);
                }
            });
            monthright.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    monthright.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    monthright.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    monthright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    monthright.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    select.add(Calendar.MONTH, 1);
                    monthright.setForeground(Color.WHITE);
                    refresh();
                }

                public void mouseReleased(MouseEvent me) {
                    monthright.setForeground(Color.BLACK);
                }
            });
        }

        private void updateDate() {
            center.setText(select.get(Calendar.YEAR) + "年" + (select.get(Calendar.MONTH) + 1) + "月");
        }
    }

    private class JP2 extends JPanel {
        private static final long serialVersionUID = -8176264838786175724L;

        public JP2() {
            this.setPreferredSize(new Dimension(295, 20));
        }

        protected void paintComponent(Graphics g) {
            g.setFont(font);
            g.drawString("星期日 星期一 星期二 星期三 星期四 星期五 星期六", 5, 10);
            g.drawLine(0, 15, getWidth(), 15);
        }

        private void updateDate() {

        }
    }

    private class JP3 extends JPanel {
        private static final long serialVersionUID = 43157272447522985L;

        public JP3() {
            super(new GridLayout(6, 7));
            this.setPreferredSize(new Dimension(295, 100));
            initJP3();
        }

        private void initJP3() {
            updateDate();
        }

        public void updateDate() {
            this.removeAll();
            lm.clear();
            Date temp = select.getTime();
            Calendar select = Calendar.getInstance();
            select.setTime(temp);
            select.set(Calendar.DAY_OF_MONTH, 1);
            int index = select.get(Calendar.DAY_OF_WEEK);
            int sum = (index == 1 ? 8 : index);
            select.add(Calendar.DAY_OF_MONTH, 0 - sum);
            for (int i = 0; i < 42; i++) {
                select.add(Calendar.DAY_OF_MONTH, 1);
                lm.addLabel(new MyLabel(select.get(Calendar.YEAR), select.get(Calendar.MONTH), select.get(Calendar.DAY_OF_MONTH)));
            }
            for (MyLabel my : lm.getLabels()) {
                this.add(my);
            }
            select.setTime(temp);
        }
    }

    private class MyLabel extends JLabel implements Comparator<MyLabel>, MouseListener, MouseMotionListener {
        private static final long serialVersionUID = 3668734399227577214L;
        private int year, month, day;
        private boolean isSelected;

        public MyLabel(int year, int month, int day) {
            super("" + day, JLabel.CENTER);
            this.year = year;
            this.day = day;
            this.month = month;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.setFont(font);
            if (month == select.get(Calendar.MONTH)) {
                this.setForeground(Color.BLACK);
            } else {
                this.setForeground(Color.LIGHT_GRAY);
            }
            if (day == select.get(Calendar.DAY_OF_MONTH)) {
                this.setBackground(new Color(160, 185, 215));
            } else {
                this.setBackground(Color.WHITE);
            }
        }

        public boolean getIsSelected() {
            return isSelected;
        }

        public void setSelected(boolean b, boolean isDrag) {
            isSelected = b;
            if (b && !isDrag) {
                int temp = select.get(Calendar.MONTH);
                select.set(year, month, day);
                if (temp == month) {
                    SwingUtilities.updateComponentTreeUI(jp3);
                } else {
                    refresh();
                }
            }
            this.repaint();
        }

        protected void paintComponent(Graphics g) {
            if (day == select.get(Calendar.DAY_OF_MONTH) && month == select.get(Calendar.MONTH)) {
                //如果當前日期是選擇日期,則高亮顯示
                g.setColor(new Color(160, 185, 215));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH) && day == now.get(Calendar.DAY_OF_MONTH)) {
                //如果日期和當前日期一樣,則用紅框
                Graphics2D gd = (Graphics2D) g;
                gd.setColor(Color.RED);
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            if (isSelected) {//如果被選中了就畫出一個虛線框出來
                Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f, new float[] { 2.0f, 2.0f }, 1.0f);
                Graphics2D gd = (Graphics2D) g;
                gd.setStroke(s);
                gd.setColor(Color.BLACK);
                Polygon p = new Polygon();
                p.addPoint(0, 0);
                p.addPoint(getWidth() - 1, 0);
                p.addPoint(getWidth() - 1, getHeight() - 1);
                p.addPoint(0, getHeight() - 1);
                gd.drawPolygon(p);
            }
            super.paintComponent(g);
        }

        public boolean contains(Point p) {
            return this.getBounds().contains(p);
        }

        private void update() {
            repaint();
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            isSelected = true;
            update();
        }

        public void mouseReleased(MouseEvent e) {
            Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
            lm.setSelect(p, false);
            commit();
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
            Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
            lm.setSelect(p, true);
        }

        public void mouseMoved(MouseEvent e) {
        }

        public int compare(MyLabel o1, MyLabel o2) {
            Calendar c1 = Calendar.getInstance();
            c1.set(o1.year, o2.month, o1.day);
            Calendar c2 = Calendar.getInstance();
            c2.set(o2.year, o2.month, o2.day);
            return c1.compareTo(c2);
        }
    }

    private class LabelManager {
        private List<MyLabel> list;

        public LabelManager() {
            list = new ArrayList<MyLabel>();
        }

        public List<MyLabel> getLabels() {
            return list;
        }

        public void addLabel(MyLabel my) {
            list.add(my);
        }

        public void clear() {
            list.clear();
        }

        @SuppressWarnings("unused")
        public void setSelect(MyLabel my, boolean b) {
            for (MyLabel m : list) {
                if (m.equals(my)) {
                    m.setSelected(true, b);
                } else {
                    m.setSelected(false, b);
                }
            }
        }

        public void setSelect(Point p, boolean b) {
            //如果是拖動,則要優化一下,以提高效率
            if (b) {
                //表示是否能返回,不用比較完所有的標籤,能返回的標誌就是把上一個標籤和
                //將要顯示的標籤找到了就可以了
                boolean findPrevious = false, findNext = false;
                for (MyLabel m : list) {
                    if (m.contains(p)) {
                        findNext = true;
                        if (m.getIsSelected()) {
                            findPrevious = true;
                        } else {
                            m.setSelected(true, b);
                        }
                    } else if (m.getIsSelected()) {
                        findPrevious = true;
                        m.setSelected(false, b);
                    }
                    if (findPrevious && findNext) {
                        return;
                    }
                }
            } else {
                MyLabel temp = null;
                for (MyLabel m : list) {
                    if (m.contains(p)) {
                        temp = m;
                    } else if (m.getIsSelected()) {
                        m.setSelected(false, b);
                    }
                }
                if (temp != null) {
                    temp.setSelected(true, b);
                }
            }
        }

    }

    private class JP4 extends JPanel {
        private static final long serialVersionUID = -6391305687575714469L;

        public JP4() {
            super(new BorderLayout());
            this.setPreferredSize(new Dimension(295, 20));
            this.setBackground(new Color(160, 185, 215));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            final JLabel jl = new JLabel("今天: " + sdf.format(new Date()));
            jl.setToolTipText("點選選擇今天日期");
            this.add(jl, BorderLayout.CENTER);
            jl.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    jl.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent me) {
                    jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    jl.setForeground(Color.BLACK);
                }

                public void mousePressed(MouseEvent me) {
                    jl.setForeground(Color.WHITE);
                    select.setTime(new Date());
                    refresh();
                    commit();
                }

                public void mouseReleased(MouseEvent me) {
                    jl.setForeground(Color.BLACK);
                }
            });
        }

        private void updateDate() {

        }
    }

    public static boolean isDate2NotBeforeDate1(String date1, String date2) {

        int y1 = Integer.parseInt(date1.substring(0, 4));
        int m1 = Integer.parseInt(date1.substring(4, 6));
        int d1 = Integer.parseInt(date1.substring(6, 8));
        int y2 = Integer.parseInt(date2.substring(0, 4));
        int m2 = Integer.parseInt(date2.substring(4, 6));
        int d2 = Integer.parseInt(date2.substring(6, 8));

        if(y1 > y2) {
            return false;
        }
        else if(y1 == y2) {
            if(m1 > m2) {
                return false;
            }
            else if(m1 == m2) {
                return d1 <= d2;
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }
    }

}