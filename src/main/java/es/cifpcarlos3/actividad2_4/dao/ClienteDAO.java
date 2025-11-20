package es.cifpcarlos3.actividad2_4.dao;

public interface ClienteDAO {
    void insertarCliente(String dni, String nombre, String telefono, String email);
    void listarClientes();
    void eliminarCliente(int idCliente);
}
