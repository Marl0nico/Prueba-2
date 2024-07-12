import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Registro {
    public JPanel panelRegistro;
    private JTextArea Cedula;
    private JTextArea historialClinico;
    private JTextArea Nombre;
    private JTextArea Apellido;
    private JTextArea Telefono;
    private JTextArea Edad;
    private JTextArea descripciónEnfermedad;
    private JTextField ingresoCedula;
    private JTextField ingresoHistorial_clinico;
    private JTextField ingresoNombre;
    private JTextField ingresoApellido;
    private JTextField ingresoTelefono;
    private JTextField ingresoEdad;
    private JTextField ingresoEnfermedad;
    private JButton botonIngreso_datos;
    private JButton botonBusqueda;
    private JLabel mostrarMensaje;
    String nombre, apellido, enfermedad;
    int cédula, historialClínico, teléfono, edad;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEnfermedad() {
        return enfermedad;
    }
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
    public int getCédula() {
        return cédula;
    }
    public void setCédula(int cédula) {
        this.cédula = cédula;
    }
    public int getHistorialClínico() {
        return historialClínico;
    }
    public void setHistorialClínico(int historialClínico) {
        this.historialClínico = historialClínico;
    }
    public int getTeléfono() {
        return teléfono;
    }
    public void setTeléfono(int teléfono) {
        this.teléfono = teléfono;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Registro() {
        botonIngreso_datos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String usuario = "root";
                String contraseña = "";
                Registro registros=new Registro();
                cédula= Integer.parseInt(ingresoCedula.getText());
                historialClínico=Integer.parseInt(ingresoHistorial_clinico.getText());
                nombre=ingresoNombre.getText();
                apellido=ingresoApellido.getText();
                teléfono=Integer.parseInt(ingresoTelefono.getText());
                edad=Integer.parseInt(ingresoEdad.getText());
                enfermedad=ingresoEnfermedad.getText();
                registros.setCédula(cédula);
                registros.setHistorialClínico(historialClínico);
                registros.setNombre(nombre);
                registros.setApellido(apellido);
                registros.setTeléfono(teléfono);
                registros.setEdad(edad);
                registros.setEnfermedad(enfermedad);
                String query = "INSERT INTO paciente (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
                    System.out.println("Conexión realizada con éxito");
                    PreparedStatement cadenaPreparada = connection.prepareStatement(query);
                    cadenaPreparada.setInt(1, cédula);
                    cadenaPreparada.setInt(2, historialClínico);
                    cadenaPreparada.setString(3, nombre);
                    cadenaPreparada.setString(4, apellido);
                    cadenaPreparada.setInt(5, teléfono);
                    cadenaPreparada.setInt(6, edad);
                    cadenaPreparada.setString(7, enfermedad);
                    cadenaPreparada.executeUpdate();
                    mostrarMensaje.setText("Datos ingresados con éxito");
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        botonBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Ventana de búsqueda");
                frame.setContentPane(new Busqueda().panelBusqueda);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(botonBusqueda)).dispose();
            }
        });
    }
}
