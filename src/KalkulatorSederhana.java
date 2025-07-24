import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KalkulatorSederhana extends JFrame implements ActionListener {
    JTextField display;
    double angka1, angka2, hasil;
    String operator;

    public KalkulatorSederhana() {
        setTitle("Kalkulator Sederhana");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set tema UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 32));
        display.setBackground(new Color(240, 240, 240));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // Panel tombol
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(230, 230, 250)); // Lavender

        String[] tombol = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String txt : tombol) {
            JButton btn = new JButton(txt);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(255, 255, 255));
            btn.setForeground(Color.DARK_GRAY);
            btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            btn.addActionListener(this);

            // Hover effect
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(220, 220, 255));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(Color.WHITE);
                }
            });

            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            display.setText(display.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            if (!display.getText().isEmpty()) {
                angka1 = Double.parseDouble(display.getText());
                operator = input;
                display.setText("");
            }
        } else if (input.equals("=")) {
            if (!display.getText().isEmpty()) {
                angka2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": hasil = angka1 + angka2; break;
                    case "-": hasil = angka1 - angka2; break;
                    case "*": hasil = angka1 * angka2; break;
                    case "/":
                        if (angka2 != 0) {
                            hasil = angka1 / angka2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(hasil));
            }
        } else if (input.equals("C")) {
            display.setText("");
            angka1 = angka2 = hasil = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KalkulatorSederhana::new);
    }
}
