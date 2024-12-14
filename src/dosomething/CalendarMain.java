package dosomething;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CalendarMain extends JFrame {
    private static final int DEFAULT_HEIGHT = 500;
    private static final int DEFAULT_WEIGHT = 500;
    private static final int DEFAULT_ROW = 7;
    private static final String[] title = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private final MessageOfDay messageOfDay = new MessageOfDay();
    private LocalDate monthChoose;
    private int countOfMonth;
    private int firstday;
    private int lastM;

    public CalendarMain() {
        this.monthChoose = this.messageOfDay.getDateOfChina();
    }

    public void update() {
        this.countOfMonth = this.monthChoose.lengthOfMonth();
        this.firstday = this.monthChoose.withDayOfMonth(1).getDayOfWeek().getValue();
        this.lastM = this.monthChoose.plusMonths(-1L).lengthOfMonth();
        this.setSize(DEFAULT_WEIGHT, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(3);
        this.showNorth();
        this.showSouth();
        this.setVisible(true);
    }

    public void showNorth() {
        JTextField date = new JTextField(this.monthChoose.toString());
        JButton reduceYearButton = new JButton("y");
        JButton plusYearButton = new JButton("y");
        JComboBox<Integer> monthBox = new JComboBox();

        for (int i = 1; i < 13; ++i) {
            monthBox.addItem(i);
        }

        JPanel areaNorth = new JPanel();
        areaNorth.setBackground(Color.blue);
        areaNorth.add(date);
        areaNorth.add(plusYearButton);
        areaNorth.add(reduceYearButton);
        areaNorth.add(monthBox);
        monthBox.addItemListener((e) -> {
            if (e.getStateChange() == 1) {
                this.monthChoose = this.monthChoose.withMonth((Integer) e.getItem());
                this.update();
            }

        });
        reduceYearButton.addActionListener((e) -> {
            this.monthChoose = this.monthChoose.plusMonths(-1L);
            this.update();
        });
        plusYearButton.addActionListener((e) -> {
            this.monthChoose = this.monthChoose.plusMonths(1L);
            this.update();
        });
        this.add(areaNorth, "North");
    }

    public void showSouth() {
        JPanel southArea = new JPanel();
        southArea.setBackground(Color.CYAN);
        southArea.setLayout(new GridLayout(7, 7));

        int i;
        for (i = 0; i < DEFAULT_ROW; ++i) {
            southArea.add(new JLabel(title[i]));
        }

        for (i = 0; i < this.firstday - 1; ++i) {
            southArea.add(new JLabel("" + (this.lastM - this.firstday + 2 + i)));
        }

        for (i = 0; i < this.countOfMonth; ++i) {
            southArea.add(new JLabel("" + (i + 1)));
        }

        for (i = 0; i < 42 - this.countOfMonth - (this.firstday - 1); ++i) {
            southArea.add(new JLabel("" + (i + 1)));
        }

        this.add(southArea, "Center");
    }
}
