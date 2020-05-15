package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conexao = null;
		Statement st = null;
		try {
			conexao=DB.getConexao();
			conexao.setAutoCommit(false);
			st = conexao.createStatement();
			int linhas = st.executeUpdate("update seller set BaseSalary=2090 where departmentId=1;");
			/*if(linhas >1) {
				throw new SQLException("Fake error");
			}*/
			int linhas2 = st.executeUpdate("update seller set BaseSalary=3090 where departmentId=2;");
			conexao.commit();
			System.out.println("linhas 1: "+linhas);
			System.out.println("linhas 2: "+linhas2);

			
		}catch(SQLException e) {
			try {
				conexao.rollback();
				throw new DbException("Transação não pode ser efetuada! Erro causado por: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao desfazer operação!");
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConexao();
		}
	}

}
