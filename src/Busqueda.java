import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Busqueda {
    public JPanel panelBusqueda;
    private JTextArea busquedaCedula;
    private JTextField ingresoCedula;
    private JButton botonBuscar;
    private JTextArea Cedula;
    private JTextArea historialClinico;
    private JTextArea Nombre;
    private JTextArea Apellido;
    private JTextArea Telefono;
    private JTextArea Edad;
    private JTextArea descripcionEnfermedad;
    private JLabel mostrarCedula;
    private JLabel mostrarHistorial_clinico;
    private JLabel mostrarNombre;
    private JLabel mostrarApellido;
    private JLabel mostrarTelefono;
    private JLabel mostrarEdad;
    private JLabel mostrarEnfermedad;
    private JButton botonIngresar_datos;
    private JButton botonLogin;

    public Busqueda() {
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String usuario = "root";
                String contraseña = "";
                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("Conexión exitosa");
                    String query = "SELECT * FROM paciente WHERE cedula='" + ingresoCedula.getText()+"'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()){
                        mostrarCedula.setText(resultSet.getString("cedula"));
                        mostrarHistorial_clinico.setText(resultSet.getString("n_historial_clinico"));
                        mostrarNombre.setText(resultSet.getString("nombre"));
                        mostrarApellido.setText(resultSet.getString("apellido"));
                        mostrarTelefono.setText(resultSet.getString("telefono"));
                        mostrarEdad.setText(resultSet.getString("edad"));
                        mostrarEnfermedad.setText(resultSet.getString("descripcion_enfermedad"));
                    }
                } catch (SQLException E){
                    System.out.println(E.getMessage());
                }
            }
        });
        botonIngresar_datos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame3 = new JFrame();
                frame3.setTitle("Registro");
                frame3.setContentPane(new Registro().panelRegistro);
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.setSize(800, 600);
                frame3.pack();
                frame3.setVisible(true);
            }
        });
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame4 = new JFrame();
                frame4.setTitle("Login");
                frame4.setContentPane(new Login().panelLogin);
                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame4.setSize(800, 600);
                frame4.pack();
                frame4.setVisible(true);
            }
        });
    }
}
