import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMTCalculator extends JFrame {

    private JLabel labelAge;
    private JLabel labelHeight;
    private JLabel labelWeight;
    private JLabel labelGender;
    private JLabel labelIMTResult;
    private JLabel labelBBResult;
    private JTextField textFieldAge;
    private JTextField textFieldHeight;
    private JTextField textFieldWeight;
    private JComboBox<String> comboBoxGender;
    private JButton buttonCalculate;
    private IMTCategoryPanel categoryPanel;

    private class IMTCategoryPanel extends JPanel {

        private String category;
        private Color color;

        public IMTCategoryPanel(String category, Color color) {
            this.category = category;
            this.color = color;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            int diameter = Math.min(width = 84 , height = 84); // Menggunakan diameter yang lebih kecil

            g2d.setColor(color);
            g2d.fillArc(0, height - diameter, diameter, diameter, 0, 180);

            g2d.setColor(Color.WHITE);
            g2d.drawString(category, diameter / 4, height - diameter / 2);

            int arrowSize = 10;
            int[] xPoints = {diameter / 2, diameter / 2 + arrowSize, diameter / 2 - arrowSize};
            int[] yPoints = {height - diameter / 2, height - diameter / 2 + arrowSize, height - diameter / 2 + arrowSize};
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }

    public IMTCalculator() {
        setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        setTitle("Kalkulator IMT");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelAge = new JLabel("Umur (thn): ");
        labelHeight = new JLabel("Tinggi badan (cm): ");
        labelWeight = new JLabel("Berat badan (kg): ");
        labelGender = new JLabel("Jenis kelamin: ");
        labelIMTResult = new JLabel("Nilai IMT: ");
        labelBBResult = new JLabel("Kategori Bobot: ");

        JLabel labelWeightCategories = new JLabel("<html>Indeks Kategori IMT<br>"
                + "Bobot Terlalu Rendah \t\t&lt;= 15.9<br>"
                + "Sangat Kekurangan Bobot 16.0-16.9<br>"
                + "Kurang Bobot \t\t\t17.0-18.4<br>"
                + "Normal \t\t\t\t18.5-24.9<br>"
                + "Kelebihan Bobot \t\t25.0-29.9<br>"
                + "Obesitas kelas I \t\t30.0-34.9<br>"
                + "Obesitas Kelas II \t\t35.0-39.9<br>"
                + "Obesitas Kelas III \t\t>=40.0<br>"
                + "</html>");

        textFieldAge = new JTextField(10);
        textFieldHeight = new JTextField(10);
        textFieldWeight = new JTextField(10);

        comboBoxGender = new JComboBox<>();
        comboBoxGender.addItem("Pria");
        comboBoxGender.addItem("Wanita");

        buttonCalculate = new JButton("Hitung");

        categoryPanel = new IMTCategoryPanel("", Color.ORANGE);

        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double height = Double.parseDouble(textFieldHeight.getText()) / 100;
                double weight = Double.parseDouble(textFieldWeight.getText());
                String gender = (String) comboBoxGender.getSelectedItem();

                double imt = weight / (height * height);
                labelIMTResult.setText("Nilai IMT: " + String.format("%.2f", imt));

                String bbResult = "";
                if (imt <= 15.9) {
                    bbResult = "Bobot terlalu rendah";
                } else if (imt <= 16.9) {
                    bbResult = "Sangat kekurangan bobot";
                } else if (imt <= 18.4) {
                    bbResult = "Kurang bobot";
                } else if (imt <= 24.9) {
                    bbResult = "Normal";
                } else if (imt <= 29.9) {
                    bbResult = "Kelebihan bobot";
                } else if (imt <= 34.9) {
                    bbResult = "Obesitas kelas I";
                } else if (imt <= 39.9) {
                    bbResult = "Obesitas kelas II";
                } else {
                    bbResult = "Obesitas kelas III";
                }
                labelBBResult.setText("Kategori Bobot: " + bbResult);

                if (imt <= 18.4) {
                    categoryPanel.category = "BB Kurang";
                    categoryPanel.color = Color.ORANGE;
                } else if (imt <= 24.9) {
                    categoryPanel.category = "Normal";
                    categoryPanel.color = Color.GREEN;
                } else {
                    categoryPanel.category = "BB Berlebih";
                    categoryPanel.color = Color.RED;
                }

                categoryPanel.repaint();
            }
        });

        box.add(labelAge);
        box.add(textFieldAge);
        textFieldAge.setMargin(new Insets(0, 0, 0, 8));
        box.add(labelHeight);
        box.add(textFieldHeight);
        textFieldHeight.setMargin(new Insets(0, 0, 0, 8));
        box.add(labelWeight);
        box.add(textFieldWeight);
        textFieldWeight.setMargin(new Insets(0, 0, 0, 8));
        box.add(labelGender);
        box.add(comboBoxGender);
        box.add(buttonCalculate);
        box.add(labelIMTResult);
        box.add(labelBBResult);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(categoryPanel, BorderLayout.CENTER);
        centerPanel.add(labelWeightCategories, BorderLayout.SOUTH);
        add(box, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IMTCalculator().setVisible(true);
            }
        });
    }
}
