package vista;

import dao.locationDAO;
import modelo.Location;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LocationVista extends JFrame {

    private locationDAO dao = new locationDAO();

    // Tabla
    private JTable tablaLocation;
    private JScrollPane scrollPane;

    // Labels
    private JLabel lblIdLocation;
    private JLabel lblLatitud;
    private JLabel lblLongitud;
    private JLabel lblIdZone;
    private JLabel lblIdAddress;

    // Campos de texto
    private JTextField txtIdLocation;
    private JTextField txtLatitud;
    private JTextField txtLongitud;
    private JComboBox<String> cmbIdZone;
    private JTextField txtIdAddress;

    // Botones
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public LocationVista() {
        initComponentes();
        listar();
    }

    private void initComponentes() {
        setTitle("JuninComercial - Location");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //  TABLA 
        tablaLocation = new JTable();
        scrollPane = new JScrollPane(tablaLocation);
        scrollPane.setBounds(10, 10, 760, 150);
        add(scrollPane);
        tablaLocation.getSelectionModel().addListSelectionListener(e -> {

    if (!e.getValueIsAdjusting()) {

        int fila = tablaLocation.getSelectedRow();

        if (fila != -1) {

            txtIdLocation.setText(tablaLocation.getValueAt(fila, 0).toString());

            String zona = tablaLocation.getValueAt(fila, 1).toString();

            switch (zona) {
                case "Norte":
                    cmbIdZone.setSelectedIndex(0);
                    break;
                case "Sur":
                    cmbIdZone.setSelectedIndex(1);
                    break;
                case "Este":
                    cmbIdZone.setSelectedIndex(2);
                    break;
                case "Oeste":
                    cmbIdZone.setSelectedIndex(3);
                    break;
                case "Centro":
                    cmbIdZone.setSelectedIndex(4);
                    break;
            }

            txtIdAddress.setText(tablaLocation.getValueAt(fila, 2).toString());
            txtLatitud.setText(tablaLocation.getValueAt(fila, 3).toString());
            txtLongitud.setText(tablaLocation.getValueAt(fila, 4).toString());

        }

    }

});

        // LABELS Y CAMPOS
        lblIdLocation = new JLabel("ID Location:");
        lblIdLocation.setBounds(10, 180, 100, 25);
        add(lblIdLocation);

        txtIdLocation = new JTextField();
        txtIdLocation.setBounds(120, 180, 150, 25);
        add(txtIdLocation);

        lblLatitud = new JLabel("Latitud:");
        lblLatitud.setBounds(10, 220, 100, 25);
        add(lblLatitud);

        txtLatitud = new JTextField();
        txtLatitud.setBounds(120, 220, 150, 25);
        add(txtLatitud);

        lblLongitud = new JLabel("Longitud:");
        lblLongitud.setBounds(10, 260, 100, 25);
        add(lblLongitud);

        txtLongitud = new JTextField();
        txtLongitud.setBounds(120, 260, 150, 25);
        add(txtLongitud);

        lblIdZone = new JLabel("Zona:");
        lblIdZone.setBounds(10, 300, 100, 25);
        add(lblIdZone);

        // ComboBox con las zonas
        cmbIdZone = new JComboBox<>(new String[]{"Norte", "Sur", "Este", "Oeste", "Centro"});
        cmbIdZone.setBounds(120, 300, 150, 25);
        add(cmbIdZone);

        lblIdAddress = new JLabel("ID Address:");
        lblIdAddress.setBounds(10, 340, 100, 25);
        add(lblIdAddress);

        txtIdAddress = new JTextField();
        txtIdAddress.setBounds(120, 340, 150, 25);
        add(txtIdAddress);

        // BOTONES
        btnListar = new JButton("Listar");
        btnListar.setBounds(10, 420, 120, 40);
        add(btnListar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 420, 120, 40);
        add(btnBuscar);

        btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(290, 420, 120, 40);
        add(btnInsertar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(430, 420, 120, 40);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(570, 420, 120, 40);
        add(btnEliminar);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(10, 480, 120, 40);
        add(btnLimpiar);

        // BOTONES
        btnListar.addActionListener(e -> listar());
        btnBuscar.addActionListener(e -> buscar());
        btnInsertar.addActionListener(e -> insertar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    private int getIdZoneSeleccionado() {
        return cmbIdZone.getSelectedIndex() + 1;
    }

    
    private void setZoneEnCombo(int idZone) {
        cmbIdZone.setSelectedIndex(idZone - 1);
    }

    private void limpiarCampos() {
        txtIdLocation.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        txtIdAddress.setText("");
        cmbIdZone.setSelectedIndex(0);
    }

    private void listar() {
        ArrayList<Location> lista = dao.listar();
        String[] columnas = {"ID", "Zona", "ID Address", "Latitud", "Longitud"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        String[] zonas = {"Norte", "Sur", "Este", "Oeste", "Centro"};

        for (Location loc : lista) {
            String nombreZona = (loc.getIdZone() != null && loc.getIdZone() >= 1 && loc.getIdZone() <= 5)
                    ? zonas[loc.getIdZone() - 1]
                    : String.valueOf(loc.getIdZone());

            Object[] fila = {
                loc.getIdLocation(),
                nombreZona,
                loc.getIdAddress(),
                loc.getLatitude(),
                loc.getLongitude()
            };
            modelo.addRow(fila);
        }

        tablaLocation.setModel(modelo);
    }

    private void buscar() {

    String texto = txtIdLocation.getText().trim();

    String[] columnas = {"ID", "Zona", "ID Address", "Latitud", "Longitud"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    String[] zonas = {"Norte", "Sur", "Este", "Oeste", "Centro"};

    if (!texto.isEmpty()) {

        try {

            int id = Integer.parseInt(texto);

            Location loc = dao.buscarPorId(id);

            if (loc != null) {

                String nombreZona = (loc.getIdZone() != null
                        && loc.getIdZone() >= 1
                        && loc.getIdZone() <= 5)
                        ? zonas[loc.getIdZone() - 1]
                        : String.valueOf(loc.getIdZone());

                modelo.addRow(new Object[]{
                    loc.getIdLocation(),
                    nombreZona,
                    loc.getIdAddress(),
                    loc.getLatitude(),
                    loc.getLongitude()
                });

            } else {

                JOptionPane.showMessageDialog(this,
                        "No se encontró el ID " + id);

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número.");

        }

    }
    else {

        int idZona = getIdZoneSeleccionado();

        ArrayList<Location> lista = dao.buscarPorZona(idZona);

        for (Location loc : lista) {

            String nombreZona = (loc.getIdZone() != null
                    && loc.getIdZone() >= 1
                    && loc.getIdZone() <= 5)
                    ? zonas[loc.getIdZone() - 1]
                    : String.valueOf(loc.getIdZone());

            modelo.addRow(new Object[]{
                loc.getIdLocation(),
                nombreZona,
                loc.getIdAddress(),
                loc.getLatitude(),
                loc.getLongitude()
            });

        }

    }

    tablaLocation.setModel(modelo);

}

    private void insertar() {
        try {
            Location loc = new Location();
            loc.setIdZone(getIdZoneSeleccionado());
            loc.setIdAddress(Integer.parseInt(txtIdAddress.getText().trim()));
            loc.setLatitude(Double.parseDouble(txtLatitud.getText().trim()));
            loc.setLongitude(Double.parseDouble(txtLongitud.getText().trim()));
            loc.setIdUserCreate(1);
            loc.setIdUserUpdate(1);
            loc.setDateCreate(LocalDateTime.now());
            loc.setDateUpdate(LocalDateTime.now());

            if (dao.insertar(loc)) {
                JOptionPane.showMessageDialog(this, "Registro insertado correctamente.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verificá que los campos tengan valores válidos.");
        }
    }

    private void modificar() {
        String texto = txtIdLocation.getText().trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresá el ID del registro a modificar.");
            return;
        }

        try {
            Location loc = new Location();
            loc.setIdLocation(Integer.parseInt(texto));
            loc.setIdZone(getIdZoneSeleccionado());
            loc.setIdAddress(Integer.parseInt(txtIdAddress.getText().trim()));
            loc.setLatitude(Double.parseDouble(txtLatitud.getText().trim()));
            loc.setLongitude(Double.parseDouble(txtLongitud.getText().trim()));
            loc.setIdUserUpdate(1);
            loc.setDateUpdate(LocalDateTime.now());

            if (dao.modificar(loc)) {
                JOptionPane.showMessageDialog(this, "Registro modificado correctamente.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verificá que los campos tengan valores válidos.");
        }
    }

    private void eliminar() {
        String texto = txtIdLocation.getText().trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresá el ID del registro a eliminar.");
            return;
        }

        int id = Integer.parseInt(texto);
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que querés eliminar el registro con ID " + id + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar. Puede tener relaciones con otras tablas.");
            }
        }
    }

    public static void main(String[] args) {
        new LocationVista();
    }
}