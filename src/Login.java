import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    public JPanel panelLogin;
    private JTextArea Usuario;
    private JTextArea Contraseña;
    private JTextField ingresoUsuario;
    private JPasswordField ingresoContraseña;
    private JButton botonLogin;
    private JLabel mostrarMensaje;
    public Login() {
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String usuario = "root";
                String contraseña = "";
                try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
                    System.out.println("Conexión realizada con éxito");
                    //String consulta="SELECT username, password FROM usuario";
                    String consulta = "SELECT * FROM usuario";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(consulta);
                    while (resultSet.next()){
                        if (ingresoUsuario.getText().equals(resultSet.getString("username")) && ingresoContraseña.getText().equals(resultSet.getString("password"))){
                            JFrame frame1 = new JFrame("Registros");
                            frame1.setContentPane(new Registro().panelRegistro);
                            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame1.setSize(800, 600);
                            frame1.pack();
                            frame1.setVisible(true);
                            ((JFrame) SwingUtilities.getWindowAncestor(botonLogin)).dispose();
                        } else{
                            System.out.println("Credenciales incorrectas");
                            mostrarMensaje.setText("Credenciales incorrectas");
                        }
                    }
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
    }
}
