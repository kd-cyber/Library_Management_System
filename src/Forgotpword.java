import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Forgotpword extends JFrame implements ActionListener {
    JButton b1,b2;
    JLabel l1,l2,l3,l4;
    JPanel pl;
    JTextField f1,f2,f3,f4;
    public Forgotpword()
    {
        pl=new JPanel();
        pl.setLayout(null);
        pl.setBackground(Color.decode("#ffcd45"));

        b1=new JButton("Search");
        b2=new JButton("Back");
        l1=new JLabel("User");
        l2=new JLabel("Name");
        l3=new JLabel("Password");
        l4=new JLabel("Email");
        f1=new JTextField();
        f2=new JTextField();
        f3=new JTextField();
        f4=new JTextField();
        f2.setEditable(false);
        f3.setEditable(false);
        f4.setEditable(false);

        b1.setForeground(Color.white);
        b1.setBackground(Color.decode("#222b33"));
        b2.setForeground(Color.white);
        b2.setBackground(Color.decode("#222b33"));

        l1.setBounds(50,50,50,25);
        l2.setBounds(50,90,50,25);
        l3.setBounds(50,130,80,25);
        l4.setBounds(50,170,50,25);
        f1.setBounds(130,50,100,25);
        f2.setBounds(130,90,100,25);
        f3.setBounds(130,130,100,25);
        f4.setBounds(130,170,100,25);
        b1.setBounds(90,180+25,80,25);
        b2.setBounds(180,180+25,80,25);

        JPanel panel=new JPanel();
        panel.setForeground(new Color(134,90,34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128,90,0),2)
                ,"Forgot Password",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(134,90,34)));
        panel.setBounds(20,20,350,220);
        panel.setBackground(Color.decode("#ffcd45"));

        pl.add(l1);
        pl.add(l2);
        pl.add(l3);
        pl.add(l4);
        pl.add(f1);
        pl.add(f2);
        pl.add(f3);
        pl.add(f4);
        pl.add(b1);
        pl.add(b2);
        pl.add(panel);
        getContentPane().add(pl);

        setTitle("VESIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,340);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
            String name=f1.getText();
            try
            {
                PreparedStatement ps;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1",
                        "root","kd101@sd");

                Statement st=con.createStatement();

                String str="select * from login1 where username=?";
                ps=con.prepareStatement(str);
                ps.setString(1,name);
                ResultSet rs;
                rs=ps.executeQuery();

                while(rs.next())
                {
                    f2.setText(rs.getString("name"));
                    f3.setText(rs.getString("password"));
                    f4.setText(rs.getString("email"));
                }
                con.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
        if(e.getSource()==b2)
        {
            this.setVisible(false);
            new Loginpage().setVisible(true);
        }
    }
}
