import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements ActionListener {
    private String message;
    private JButton button;
    private JButton submit;
    private BufferedImage goomba;
    private JTextField textField;

    public DisplayPanel() {
        button = new JButton("Click me");
        button.addActionListener(this);
        add(button);

        try {
            goomba = ImageIO.read(new File("src\\goomba.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        textField = new JTextField(10);
        add(textField);

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        add(submit);
    }

    public void addMessage(String message) {
        this.message += message;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString(message, 50, 30);
        button.setLocation(50, 100);
        g.drawImage(goomba, 200, 50, null);
        g.setColor(Color.BLACK);
        g.drawString("goomba!", 200, 110);
        textField.setLocation(50, 60);
        submit.setLocation(50, 130);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == button) {
                message = "CLICKED!";
                repaint();
            }

            if (casted == submit) {
                // obtain string from text field
                String enteredText = textField.getText();

                // update message to the entered text
                message = enteredText;

                // refresh the screen so that the updated message gets displayed
                repaint();
            }
        }
    }
}