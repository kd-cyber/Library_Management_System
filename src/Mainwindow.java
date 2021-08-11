import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainwindow extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4,b5;
    JPanel pan;
    JLabel l1,lk,l2;
    ImageIcon i1,i2;
    Image i3;
    public Mainwindow()
    {
        pan=new JPanel();
        pan.setLayout(null);
        pan.setBackground(Color.decode("#ffcd45"));

        b1=new JButton("Issue Book");
        b1.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,15));
        b2=new JButton("Return Book");
        b2.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,15));
        b3=new JButton("Add Student");
        b3.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,15));
        b5=new JButton("Logout");
        b5.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,15));

        b1.setForeground(Color.white);
        b1.setBackground(Color.decode("#222b33"));
        b2.setForeground(Color.white);
        b2.setBackground(Color.decode("#222b33"));
        b3.setForeground(Color.white);
        b3.setBackground(Color.decode("#222b33"));
        b5.setForeground(Color.white);
        b5.setBackground(Color.decode("#222b33"));


        b1.setBounds(125,150,130,35);
        b2.setBounds(125,215,130,35);
        b3.setBounds(125,280,130,35);
        b5.setBounds(125,345,130,35);


        JPanel panel=new JPanel();
        panel.setForeground(new Color(134,90,34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128,90,0),2)
                ,"Actions",TitledBorder.CENTER,TitledBorder.TOP,null,new Color(134,90,34)));
        panel.setBounds(20,20,350,500);
        panel.setBackground(Color.decode("#ffcd45"));

        pan.add(b1);
        pan.add(b2);
        pan.add(b3);
        pan.add(b5);
        pan.add(panel);
        getContentPane().add(pan);


        setTitle("VESIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,600);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b5.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
            this.setVisible(false);
            new Issuebook().setVisible(true);

        }
        if(e.getSource()==b2)
        {
            this.setVisible(false);
            new Returnbook().setVisible(true);

        }
        if(e.getSource()==b3)
        {
            this.setVisible(false);
            new Addstudent().setVisible(true);
        }
        if(e.getSource()==b4)
        {

        }
        if(e.getSource()==b5)
        {
            this.setVisible(false);
            new Loginpage().setVisible(true);
        }
    }
}
