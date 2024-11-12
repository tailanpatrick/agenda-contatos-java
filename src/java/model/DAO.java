package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

    /**
     * Módulo de Conexão *
     */
    // Parâmetros de conexão
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

    private final String user = "root";
    private final String password = ""; // mudar para root no notebook

    private Connection conectar() {
        Connection con = null;
//        
        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    // CRUD Create
    public void inserirContato(Contato contato) {
        String sql = "INSERT INTO contatos (nome, fone, email) VALUES (?,?,?)";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getPhone());
            pst.setString(3, contato.getEmail());

            pst.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Contato> listarContatos() {
        ArrayList<Contato> contatos = new ArrayList<>();

        String slq = "SELECT * FROM contatos ORDER BY nome";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(slq);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String idcon = rs.getString(1);
                String nome = rs.getString(2);
                String fone = rs.getString(3);
                String email = rs.getString(4);
                contatos.add(new Contato(idcon, nome, fone, email));
            }
            con.close();

            return contatos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void selecionarContato(Contato contato) {
        String sql = "SELECT * FROM contatos WHERE idcon = ?";
        try {
            Connection con = conectar();

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, contato.getIdcon());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                contato.setIdcon(rs.getString(1));
                contato.setNome(rs.getString(2));
                contato.setPhone(rs.getString(3));
                contato.setEmail(rs.getString(4));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editarContato(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, fone = ?, email = ? WHERE idcon = ?";

        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getPhone());
            pst.setString(3, contato.getEmail());
            pst.setString(4, contato.getIdcon());

            
            pst.executeUpdate();
            
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao editar contato: " + e.getMessage());
        }
    }
    
     public void deletarContato(Contato contato) {
         String sql = "DELETE FROM contatos WHERE idcon= ?";
         
         try {
             Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             
             pst.setString(1, contato.getIdcon());
             
             pst.executeUpdate();
             
             con.close();
         } catch (Exception e) {
             System.out.println(e);
         }
     }

}
