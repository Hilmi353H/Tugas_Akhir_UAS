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

    public IMTCalculator() {
        setLayout(new FlowLayout());
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

        textFieldAge = new JTextField(10);
        textFieldHeight = new JTextField(10);
        textFieldWeight = new JTextField(10);

        comboBoxGender = new JComboBox<>();
        comboBoxGender.addItem("Pria");
        comboBoxGender.addItem("Wanita");

        buttonCalculate = new JButton("Hitung");
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
                    bbResult = "sangat kurang berboobot";
                } else if (imt <= 18.4) {
                    bbResult = "kurang bobot";
                } else if (imt <= 24.9) {
                    bbResult = "Normal";
                } else if (imt <= 29.9) {
                    bbResult = "kelebihan bobot";
                } else if (imt <= 34.9) {
                    bbResult = "Obesitas kelas I";
                } else if (imt <= 39.9) {
                    bbResult = "Obesitas kelas II";
                } else {
                    bbResult = "Obesitas kelas III";
                }
                labelBBResult.setText("Kategori Bobot: " + bbResult);
            }
        });

        box.add(labelAge);
        box.add(textFieldAge);
        textFieldAge.setMargin(new Insets(0, 0, 0, 10));
        box.add(labelHeight);
        box.add(textFieldHeight);
        textFieldHeight.setMargin(new Insets(0, 0, 0, 10));
        box.add(labelWeight);
        box.add(textFieldWeight);
        textFieldWeight.setMargin(new Insets(0, 0, 0, 10));
        box.add(labelGender);
        box.add(comboBoxGender);
        box.add(buttonCalculate);
        box.add(labelIMTResult);
        box.add(labelBBResult);
        add(box);

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