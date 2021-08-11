import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Returnbook extends JFrame implements ActionListener {
    JButton b1,b2,b3;
    JPanel pl;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField f1,f2,f3,f4,f5,f6,f7,f8;
    JDateChooser dt;
    public Returnbook()
    {
        pl=new JPanel();
        pl.setLayout(null);
        pl.setBackground(Color.decode("#ffcd45"));
      //  pl.setBackground(Color.YELLOW);
        l1=new JLabel("BookID");
        l2=new JLabel("StdID");
        l3=new JLabel("Book");
        l4=new JLabel("StdName");
        l5=new JLabel("Course");
        l6=new JLabel("Branch");
        l7=new JLabel("Date of issue");
        l8=new JLabel("Date of return");

        f1=new JTextField();
        f2=new JTextField();
        f3=new JTextField();
        f4=new JTextField();
        f5=new JTextField();
        f6=new JTextField();
        f7=new JTextField();
        dt=new JDateChooser();
        dt.setDateFormatString("YYYY-MM-dd\n\n");

        b1=new JButton("Return");
        b2=new JButton("Back");
        b3=new JButton("Search");
        b1.setForeground(Color.white);
        b1.setBackground(Color.decode("#222b33"));
        b2.setForeground(Color.white);
        b2.setBackground(Color.decode("#222b33"));
        b3.setForeground(Color.white);
        b3.setBackground(Color.decode("#222b33"));

        l1.setBounds(40,40,70+30,30);
        l2.setBounds(40,80,70+30,30);
        l3.setBounds(40,120,70+30,30);
        l4.setBounds(40,160,70+30,30);
        l5.setBounds(40,200,70+30,30);
        l6.setBounds(40,240,70+30,30);
        l7.setBounds(40,280,70+30,30);
        l8.setBounds(40,320,70+30,30);

        f1.setBounds(120+30,40,70+30,30-5);
        f2.setBounds(120+30,80,70+30,30-5);
        f3.setBounds(120+30,120,70+30,30-5);
        f4.setBounds(120+30,160,70+30,30-5);
        f5.setBounds(120+30,200,70+30,30-5);
        f6.setBounds(120+30,240,70+30,30-5);
        f7.setBounds(120+30,280,70+30,30-5);
        dt.setBounds(120+30,320,70+30,30-5);

        b1.setBounds(100,340+35,80,30);
        b2.setBounds(200,340+35,80,30);
        b3.setBounds(260,80,90,25);
        JPanel panel=new JPanel();
        panel.setForeground(new Color(134,90,34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128,90,0),2)
                ,"Return Book",TitledBorder.CENTER,TitledBorder.TOP,null,new Color(134,90,34)));
        panel.setBounds(20,20,350,400);
        panel.setBackground(Color.decode("#ffcd45"));

        pl.add(l1);
        pl.add(l2);
        pl.add(l3);
        pl.add(l4);
        pl.add(l5);
        pl.add(l6);
        pl.add(l7);
        pl.add(l8);
        pl.add(f1);
        pl.add(f2);
        pl.add(f3);
        pl.add(f4);
        pl.add(f5);
        pl.add(f6);
        pl.add(f7);
        pl.add(dt);
        pl.add(b1);
        pl.add(b2);
        pl.add(b3);
        pl.add(panel);
        getContentPane().add(pl);

        setTitle("VESIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b3)
        {
            String stdid=f2.getText();
            try
            {
                PreparedStatement ps;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1",
                        "root","kd101@sd");


                Statement st=con.createStatement();
                String str="select * from issuebook2 where stdid=?";

                ps = con.prepareStatement(str);
                ps.setString(1,stdid);
                ResultSet rs;
                rs=ps.executeQuery();
                while(rs.next())
                {
                    f3.setText(rs.getString("bookname"));
                    f4.setText(rs.getString("sname"));
                    f5.setText(rs.getString("course"));
                    f6.setText(rs.getString("branch"));
                    f7.setText(rs.getString("dateofissue"));
                }

            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
        if(e.getSource()==b2)
        {
            this.setVisible(false);
            new Mainwindow().setVisible(true);
        }
        if(e.getSource()==b1)
        {
            String std=f2.getText();
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1",
                        "root","kd101@sd");

                PreparedStatement ps;

                String str="delete from issuebook2 where stdid=?";
                ps = con.prepareStatement(str);
                ps.setString(1,std);
                int row = ps.executeUpdate();
                if(row>0)
                {
                    JOptionPane.showMessageDialog(b1,"Returned successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(b1,"Return Failed");
                }
                con.close();
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }
}
