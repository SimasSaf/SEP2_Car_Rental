package server.DataAccess;

import org.postgresql.util.PSQLException;
import shared.transferObjects.Address;
import shared.transferObjects.user.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUserHandler implements UsersData{

    @Override
    public User login(Email email, Password password) {
        System.out.println("Aca llego");
        User user = getUserByEmail(email);
        if (user == null) return null;
        System.out.println(email.getEmail() + " Database");
        System.out.println(password.getPassword() + " database");
        return (user.getPassword().equals(password)) ? user : null;
    }

    @Override
    public void storeUser(User user) {

        new StoreUser().store(user);
    }

    @Override
    public void updateUser(int userID, User newUser) {
        deleteUser(userID); // I Delete the user
        new StoreUser().store(newUser); // Store the new one
        String query = "UPDATE \"" + DataConnection.SCHEMA + "\".users SET user_id = ? WHERE email = ?"; // Update the user_ID
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = executeQuery(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.execute();
            System.out.println("Cambio Relizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int userID) {
        String query = "DELETE FROM \"" + DataConnection.SCHEMA + "\".users WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = executeQuery(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUserList() {
        List<User> totalUserList = getAdminUserList();
        totalUserList.addAll(getCustomerUserList());
        totalUserList.addAll(getFrontDeskUserList());
        return totalUserList;
    }

    public List<User> getFrontDeskUserList() {
        return getUserListByParam(LoginType.FRONT_DESK);
    }

    public List<User> getCustomerUserList() {
        return getUserListByParam(LoginType.CUSTOMER);
    }

    public List<User> getAdminUserList() {
        return getUserListByParam(LoginType.ADMIN);
    }

    private List<User> getUserListByParam(LoginType loginType) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + loginType;
        try {
            ResultSet resultSet = getFromDatabase(query);
            do {
                userList.add(createUser(resultSet));
            } while (resultSet.next());
        } catch (SQLException e) {
            return userList;
        }
        return userList;
    }

    @Override
    public boolean isAvailable(Email email) {
        return getUserByEmail(email) == null;
    }

    @Override
    public User getUserById(int userID) {
        System.out.println(userID);
        String kindUser = "users";
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + kindUser + " WHERE user_id = '" + userID + "'";
        try {
            ResultSet resultSet = getFromDatabase(query);
            try {
                // I Founded, I wanted to know the Type
                kindUser = resultSet.getString("type");
                query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + kindUser + " WHERE user_id = '" + userID + "'";
                return createUser(getFromDatabase(query));
            } catch (PSQLException e) {
                return null; // DOES NOT EXIST
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(Email email) {
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + "users" + " WHERE email = '" + email.getEmail() + "'";
        try {
            return getUserById(getFromDatabase(query).getInt("user_id"));
        } catch (SQLException e) {
            return null;
        }
    }


    ///PRIVATE METHODS //
    private User createUser(ResultSet resultSet) throws SQLException {
        Address address = new Address(resultSet.getString("country"), resultSet.getString("city"), resultSet.getString("street"), resultSet.getString("number"), resultSet.getInt("zip"));
        Password password = new Password(resultSet.getString("password"));
        PhoneNo phoneNo = new PhoneNo(resultSet.getString("phone_no"));
        Email email = new Email(resultSet.getString("email"));
        Date dob = resultSet.getDate("dob");


        switch (resultSet.getString("type")) {
            case "ADMIN": {
                return new Admin(resultSet.getInt("user_id"),
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name"),
                        address,
                        phoneNo,
                        password,
                        email,
                        dob,
                        new Ssn(resultSet.getString("ssn")));
            }
            case "FRONT_DESK":
                return new FrontDesk(
                        resultSet.getInt("user_id"),
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name"),
                        address,
                        phoneNo,
                        password,
                        email,
                        dob,
                        new Ssn(resultSet.getString("ssn")),
                        new Address(resultSet.getString("country"),
                                resultSet.getString("city"),
                                resultSet.getString("street"),
                                resultSet.getString("number"),
                                resultSet.getInt("zip")));
            case "CUSTOMER":
                return new Customer(
                        resultSet.getInt("user_id"),
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name"),
                        address,
                        phoneNo,
                        password,
                        email,
                        dob,
                        new DriverLicense(resultSet.getString("driver_license_no")));

        }
        return null;
    }

    private class StoreUser {

        private final String START_QUERY = "INSERT INTO ";
        private final String SCHEMA_AND_TABLE = "\"" + DataConnection.SCHEMA + "\".";
        private final String QUERY_USER = "(type, f_name, l_name, phone_no, email, password, country, city, street, number, zip, dob,";
        private String finalQuery;

        private PreparedStatement preparedStatement;
        private User user;

        public void store(User user) {
            this.user = user;
            try {
                Connection connection = DataConnection.getInstance().getConnection();

                Method method = this.getClass().getMethod("set" + user.getType().toString() + "Query");
                System.out.println("set" + user.getType().toString() + "Query");
                method.invoke(this);

                preparedStatement = connection.prepareStatement(finalQuery);
                prepareUser();

                method = this.getClass().getMethod("prepare" + user.getType().toString());
                method.invoke(this);

                preparedStatement.execute();
            } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        public void setADMINQuery() {
            finalQuery = START_QUERY + SCHEMA_AND_TABLE + user.getType().toString() + QUERY_USER + " ssn) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        public void setFRONT_DESKQuery() {

            finalQuery = START_QUERY + SCHEMA_AND_TABLE + user.getType().toString() + QUERY_USER + " ssn, workplace_country, workplace_city, workplace_street, workplace_number, workplace_zip) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println(finalQuery);
        }

        public void setCUSTOMERQuery() {
            finalQuery = START_QUERY + SCHEMA_AND_TABLE + user.getType().toString() + QUERY_USER + " driver_license_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        public void prepareUser() throws SQLException {
            preparedStatement.setString(1, user.getType().toString());
            preparedStatement.setString(2, user.getFName());
            preparedStatement.setString(3, user.getLName());
            preparedStatement.setString(4, user.getPhoneNo());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword().getPassword()); //Chage this use cripto
            preparedStatement.setString(7, user.getAddress().getCountry());
            preparedStatement.setString(8, user.getAddress().getCity());
            preparedStatement.setString(9, user.getAddress().getStreet());
            preparedStatement.setString(10, user.getAddress().getNumber());
            preparedStatement.setInt(11, user.getAddress().getZip());
            preparedStatement.setDate(12, user.getDob());
        }

        public void prepareADMIN() throws SQLException {
            preparedStatement.setString(13, ((Admin) user).getSsn());

        }

        public void prepareFRONT_DESK() throws SQLException {
            preparedStatement.setString(13, ((FrontDesk) user).getSsn());
            preparedStatement.setString(14, ((FrontDesk) user).getWorkAddress().getCountry());
            preparedStatement.setString(15, ((FrontDesk) user).getWorkAddress().getCity());
            preparedStatement.setString(16, ((FrontDesk) user).getWorkAddress().getStreet());
            preparedStatement.setString(17, ((FrontDesk) user).getWorkAddress().getNumber());
            preparedStatement.setInt(18, ((FrontDesk) user).getWorkAddress().getZip());
        }

        public void prepareCUSTOMER() throws SQLException {
            preparedStatement.setString(13, ((Customer) user).getDriverLicenseNo());

        }
    }


        private ResultSet getFromDatabase(String query) throws SQLException {
            Connection connection = DataConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet;
        }

        private PreparedStatement executeQuery(String query) throws SQLException {
            Connection connection = DataConnection.getInstance().getConnection();
            return connection.prepareStatement(query);
        }


}