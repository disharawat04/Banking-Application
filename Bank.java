import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
        loadAccountsFromDatabase();
    }

    private void loadAccountsFromDatabase() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcHelper.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM accounts";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int accountNumber = resultSet.getInt("accountnumber");
                String customerName = resultSet.getString("customername");
                double balance = resultSet.getDouble("balance");

                Account account = new Account(accountNumber, customerName, balance);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcHelper.closeResultSet(resultSet);
            JdbcHelper.closeStatement(statement);
            JdbcHelper.closeConnection(connection);
        }
    }

    public void addAccount(Account account) {
        accounts.add(account);
        saveAccountToDatabase(account);
    }

    private void saveAccountToDatabase(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcHelper.getConnection();
            String query = "INSERT INTO accounts (accountnumber, customername, balance) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getCustomerName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcHelper.closeStatement(preparedStatement);
            JdbcHelper.closeConnection(connection);
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
