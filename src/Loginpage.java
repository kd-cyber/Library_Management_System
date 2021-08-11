import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Loginpage extends JFrame implements ActionListener {
 //   JFrame f=new JFrame();
    JPanel pl;
    JButton b1,b2;
    JTextField f1;
    JLabel lk,l1,l2,l3;
    JPasswordField p;
    ImageIcon i1,i2;
    Image i3;
   // Border bla=BorderFactory.createLineBorder(Color.black);  //used to set border
    public Loginpage()
    {
       i1=new ImageIcon(ClassLoader.getSystemResource("Resources/tra.png"));
       i3=i1.getImage().getScaledInstance(400,700,Image.SCALE_SMOOTH);
       i2=new ImageIcon(i3);
       lk=new JLabel(i2);


       pl=new JPanel();
       pl.setLayout(null);
       pl.setBackground(Color.decode("#ffcd45"));;
       b1=new JButton("Login");
       b1.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,15));
       b2=new JButton("ForgotPassword");
       b2.setFont(new Font("Lucida Sans Unicode",Font.PLAIN,14));

       f1=new JTextField();
       p=new JPasswordField();
       JTextField f2=new JTextField();
       l1=new JLabel("Name");
       l1.setFont(l1.getFont().deriveFont(15.0f));

       l2=new JLabel("Password");
       l2.setFont(l2.getFont().deriveFont(15.0f));
       l3=new JLabel("Login Page");
       l3.setFont(l1.getFont().deriveFont(36.0f));
       l3.setFont(new Font("Impact",Font.BOLD,42));

       lk.setBounds(-10,0,400,600);
       b1.setBounds(100+10,400-30,150,30);
       b1.setForeground(Color.white);
       b1.setBackground(Color.decode("#222b33"));
       b2.setBounds(110,420,150,30);
       b2.setForeground(Color.white);
       b2.setBackground(Color.decode("#222b33"));
       f1.setBounds(150+10,210-20,135,35);
       p.setBounds(150+10,300-20,135,35);
       l1.setBounds(50+15,210-20,100,30);
       l2.setBounds(50+15,300-20,100,30);
       l3.setBounds(95,90,230,55);

        lk.add(l1);
        lk.add(l2);
        lk.add(p);
        lk.add(b1);
        lk.add(b2);
        lk.add(f1);
        lk.add(f2);
        lk.add(l3);
        pl.add(lk);
        getContentPane().add(pl);

        setTitle("VESIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,630);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
            String name=f1.getText();
            String password=p.getText();

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/college?characterEncoding=latin1","root","kd101@sd");

                String str="select *from login where username=? and password=?";

                PreparedStatement ps=con.prepareStatement(str);
                ps.setString(1,name);
                ps.setString(2,password);
                ResultSet rs=ps.executeQuery();

                if(rs.next())
                {
                    JOptionPane.showMessageDialog(b1,"login successful");
                    this.setVisible(false);
                    new Mainwindow().setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(b1,"Invalid name or Password");
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
            new Forgotpword().setVisible(true);
        }
    }
    public static void main(String args[])
    {
        new Loginpage();
    }
}