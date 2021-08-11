import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class Addstudent extends JFrame implements ActionListener {
    JButton b1,b2;
    JTextField f1,f2,f3,f4,f5,f6;
    JLabel l1,l2,l3,l4,l5,l6;
    JPanel p;
    public Addstudent()
    {
        p=new JPanel();
        p.setLayout(null);
        p.setBackground(Color.decode("#ffcd45"));
        l1=new JLabel("Name");
        l2=new JLabel("Course");
        l3=new JLabel("Branch");
        l4=new JLabel("Year");
        l5=new JLabel("Semester");
        l6=new JLabel("Email");

        l1.setFont(l1.getFont().deriveFont(15.0f));
        l2.setFont(l1.getFont().deriveFont(15.0f));
        l3.setFont(l1.getFont().deriveFont(15.0f));
        l4.setFont(l1.getFont().deriveFont(15.0f));
        l5.setFont(l1.getFont().deriveFont(15.0f));
        l6.setFont(l1.getFont().deriveFont(15.0f));


        f1=new JTextField();
        f2=new JTextField();
        f3=new JTextField();
        f4=new JTextField();
        f5=new JTextField();
        f6=new JTextField();

        b1=new JButton("Submit");
        b2=new JButton("Back");
        b1.setForeground(Color.white);
        b1.setBackground(Color.decode("#222b33"));
        b2.setForeground(Color.white);
        b2.setBackground(Color.decode("#222b33"));

        l1.setBounds(60,50,60,40);
        l2.setBounds(60,100,60,40);
        l3.setBounds(60,150,60,40);
        l4.setBounds(60,200,60,40);
        l5.setBounds(60,250,80,40);
        l6.setBounds(60,300,60,40);
        f1.setBounds(160,50+5+5,60+40,40-15);
        f2.setBounds(160,100+5+5,60+40,40-15);
        f3.setBounds(160,150+5+5,60+40,40-15);
        f4.setBounds(160,200+5+5,60+40,40-15);
        f5.setBounds(160,250+5+5,60+40,40-15);
        f6.setBounds(160,300+5+5,60+40,40-15);
        b1.setBounds(100-20,360,60+40,30);
        b2.setBounds(210-20,360,60+40,30);

        JPanel panel=new JPanel();
        panel.setForeground(new Color(134,90,34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128,90,0),2)
                ,"Add student",TitledBorder.CENTER,TitledBorder.TOP,null,new Color(134,90,34)));
        panel.setBounds(20,20,350,400);
        panel.setBackground(Color.decode("#ffcd45"));

        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(f1);
        p.add(f2);
        p.add(f3);
        p.add(f4);
        p.add(f5);
        p.add(f6);
        p.add(b1);
        p.add(b2);
        p.add(panel);
        getContentPane().add(p);

        setTitle("VESIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name=f1.getText();
        String Course=f2.getText();
        String Branch=f3.getText();
        String Year=f4.getText();
        String Sem=f5.getText();
        String Email=f6.getText();

            try
            {
                if(e.getSource()==b1) {


                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1", "root", "kd101@sd");

                    PreparedStatement ps;
                    String str = "insert into student1 values(?,?,?,?,?,?)";
                    ps = con.prepareStatement(str);


                    ps.setString(1, name);
                    ps.setString(2, Course);
                    ps.setString(3, Branch);
                    ps.setString(4, Year);
                    ps.setString(5, Sem);
                    ps.setString(6, Email);
                   // ps.executeUpdate();

                    int row = ps.executeUpdate();
                    if(row>0)
                    {
                        JOptionPane.showMessageDialog(b1,"Student added successfully");
                    }
                    con.close();

                }
                if(e.getSource()==b2)
                {
                    this.setVisible(false);
                    new Mainwindow().setVisible(true);
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }


    }
}
