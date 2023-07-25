import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class bdconexion {
    //creacion de cadena de conexion
    public static final String DB_URL = "jdbc:mysql://localhost/estudiantes";
    public static final String USER = "root";
    public static final String PASSWORD = "root_bas3";
    public static final String QUERY = "SELECT * FROM estudiantes";




}



class bdventanas {
    private JButton queryButton;
    private JPanel ROOTPANEL;
    private JTextArea textArea1;
    private JTextPane textPane1;

    public bdventanas() {
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(
                        //se manda todos los parametros de la cadena de conexion
                        Connection conn = DriverManager.getConnection(bdconexion.DB_URL, bdconexion.USER, bdconexion.PASSWORD);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(bdconexion.QUERY);
                ){
                    //se utiliza un while para que imprima todos los datos
                    while(rs.next()){
                        textArea1.append(("id: " +  rs.getInt("id")) + (" nombre: " +  rs.getString("nombre"))
                        + (" edad: " +  rs.getInt("edad")) + (" ciudad: " +  rs.getString("ciudad"))
                    + (" cedula: " +  rs.getInt("cedula")) + (" contraseña: " +  rs.getInt("password") + "\n"));
                    }
                    //estos catch deben estar presentes siempre
                } catch (SQLException x) {
                    throw new RuntimeException(x);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("bdventanas");
        frame.setContentPane(new bdventanas().ROOTPANEL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
