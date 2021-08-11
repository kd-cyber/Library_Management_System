import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.toedter.calendar.JDateChooser;

public class Issuebook extends JFrame implements ActionListener
{
    JPanel p3;
    JLabel l1,l3,l4,l5,l6,l7,ln;
    JTextField f1,f3,f4,f5,f6,f7,f8;
    JButton b1,b2,b3;
    JDateChooser dt;
    JLabel ql1,ql2,ql3;
    JTextField qf1,qf2,qf3;
    JButton qb1,qb2,qb3;
    public Issuebook()
    {
        p3=new JPanel();
        p3.setLayout(null);
        p3.setBackground(Color.decode("#ffcd45"));

        l1=new JLabel("StudentID");
        l3=new JLabel("Stdname");
        l4=new JLabel("Course");
        l5=new JLabel("Branch");
        l6=new JLabel("Year");
        l7=new JLabel("Semester");
        ln=new JLabel("Date of issue");


        ql1=new JLabel("Bookname");
        qf1=new JTextField();
        qb1=new JButton("Search");
        ql2=new JLabel("Bookid");
        ql3=new JLabel("Edition");
        qf2=new JTextField();
        qf3=new JTextField();

        qb1.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,15));

        qb1.setForeground(Color.white);
        qb1.setBackground(Color.decode("#222b33"));


        f1=new JTextField();
        f3=new JTextField();
        f4=new JTextField();
        f5=new JTextField();
        f6=new JTextField();
        f7=new JTextField();

        dt=new JDateChooser();
        dt.setDateFormatString("YYYY-MM-dd\n\n");
        dt.setBorder(new LineBorder(new Color(0,0,0),1,true));
        dt.setForeground(new Color(105,105,105));
        qf2.setEditable(false);
        qf3.setEditable(false);

        b1=new JButton("Issue");
        b2=new JButton("Back");
        b3=new JButton("Search");

        b1.setForeground(Color.white);
        b1.setBackground(Color.decode("#222b33"));
        b2.setForeground(Color.white);
        b2.setBackground(Color.decode("#222b33"));
        b3.setForeground(Color.white);
        b3.setBackground(Color.decode("#222b33"));

        ql1.setBounds(600-80,50,70,30);
        ql2.setBounds(600-80,100,60,30);
        ql3.setBounds(600-80,150,60,30);
        qf1.setBounds(700-80,50,100,30);
        qf2.setBounds(700-80,100,100,30);
        qf3.setBounds(700-80,150,100,30);

        qb1.setBounds(600,220,90,30);

        l1.setBounds(40,50,60,40);
        l3.setBounds(40,100,60,40);
        l4.setBounds(40,150,60,40);
        l5.setBounds(40,200,60,40);
        l6.setBounds(40,250,60,40);
        l7.setBounds(40,300,60,40);
        ln.setBounds(40,350,90,40);

        f1.setBounds(180,60,40+60,25);
        f3.setBounds(180,110,40+60,25);
        f4.setBounds(180,160,40+60,25);
        f5.setBounds(180,210,40+60,25);
        f6.setBounds(180,260,40+60,25);
        f7.setBounds(180,310,40+60,25);

        b1.setBounds(70,460,100,40);
        b2.setBounds(200,460,100,40);
        b3.setBounds(300,60,100,25);
        dt.setBounds(180,350,100,30);  //f8

        JPanel panel=new JPanel();
        panel.setForeground(new Color(134,90,34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128,90,0),2)
                ,"Issue Book",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(134,90,34)));
        panel.setBounds(20,20,400,500);
        panel.setBackground(Color.decode("#ffcd45"));

        JPanel panel1=new JPanel();
        panel1.setForeground(new Color(134,90,34));
        panel1.setBorder(new TitledBorder(new LineBorder(new Color(128,90,0),2)
                ,"IssueBook",TitledBorder.CENTER,TitledBorder.TOP,null,new Color(134,90,34)));
        panel1.setBounds(580-80,20,300,270);
        panel1.setBackground(Color.decode("#ffcd45"));

        p3.add(l1);
        p3.add(l3);
        p3.add(l4);
        p3.add(l5);
        p3.add(l6);
        p3.add(l7);
        p3.add(ln);
        p3.add(f1);
        p3.add(f3);
        p3.add(f4);
        p3.add(f5);
        p3.add(f6);
        p3.add(f7);
        p3.add(dt);
        p3.add(b1);
        p3.add(b2);
        p3.add(b3);

        p3.add(ql1);
        p3.add(ql2);
        p3.add(ql3);
        p3.add(qf1);
        p3.add(qf2);
        p3.add(qf3);
        p3.add(qb1);
        p3.add(panel);
        p3.add(panel1);
        getContentPane().add(p3);

        setTitle("VESIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        qb1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==qb1)
        {
            String bname=qf1.getText();
            try
            {
                PreparedStatement ps;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1",
                        "root","kd101@sd");

                Statement st=con.createStatement();
                String str="select * from book where bookname=?";
                ps=con.prepareStatement(str);
                ps.setString(1,bname);
                ResultSet rs;
                rs=ps.executeQuery();

                while(rs.next())
                {
                    qf2.setText(rs.getString("bookid"));
                    qf3.setText(rs.getString("edition"));
                }
                con.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
        if(e.getSource()==qb3)
        {
            this.setVisible(false);
            new Mainwindow().setVisible(true);
        }


        if(e.getSource()==b1)
        {
            int stdid=Integer.parseInt(f1.getText());
            String sname=f3.getText();
            String course=f4.getText();
            String branch=f5.getText();
            String year=f6.getText();
            int sem=Integer.parseInt(f7.getText());
          //  String dateofissue=dt.getDateFormatString();
            String date=dt.getDateFormatString();
            String bookname=qf1.getText();
            String bid=qf2.getText();
            String edition=qf3.getText();
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1",
                        "root","kd101@sd");

                PreparedStatement ps;
                String str="insert into issuebook2(stdid,sname,course,branch,year,semester,dateofissue,bookname,bookid,edition) values(?,?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(str);

                ps.setInt(1,stdid);
                ps.setString(2,sname);
                ps.setString(3,course);
                ps.setString(4,branch);
                ps.setString(5,year);
                ps.setInt(6,sem);
                ps.setString(7,((JTextField) dt.getDateEditor().getUiComponent()).getText());
                ps.setString(8,bookname);
                ps.setString(9,bid);
                ps.setString(10,edition);

                int row = ps.executeUpdate();
                if(row>0)
                {
                    JOptionPane.showMessageDialog(b1,"Saved successfully");
                }
                con.close();

            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
            this.setVisible(false);
            new Mainwindow().setVisible(true);
        }
        if(e.getSource()==b2)
        {
            this.setVisible(false);
            new Mainwindow().setVisible(true);
        }
        if (e.getSource()==b3)
        {
            int stdid=Integer.parseInt(f1.getText());
            try
            {
                PreparedStatement ps;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1",
                        "root","kd101@sd");

                Statement st=con.createStatement();


                String str="select * from studentdb where stdid=?";

                ps=con.prepareStatement(str);
                ps.setInt(1,stdid);
                ResultSet rs;
                rs=ps.executeQuery();

                while(rs.next())
                {
                    f1.setText(rs.getString("stdid"));
                    f3.setText(rs.getString("sname"));
                    f4.setText(rs.getString("course"));
                    f5.setText(rs.getString("branch"));
                    f6.setText(rs.getString("year"));
                    f7.setText(rs.getString("semester"));
                }
                con.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
    }
}