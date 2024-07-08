package Admin;

import javafx.beans.property.*;

import java.time.LocalDate;

public class User {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty email;
    private final ObjectProperty<LocalDate> joinDate;
    private final StringProperty status;
    private final StringProperty address;
    private final StringProperty stemField;

    public User(int id2, String name2, String email2, String homeAddress, String password, LocalDate joinDate2, String stemField2, String status2) {
        this(0, "", "", LocalDate.now(), "", "", "");
    }

    public User(int id, String name, String email, LocalDate joinDate, String status, String address, String stemField) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.joinDate = new SimpleObjectProperty<>(joinDate);
        this.status = new SimpleStringProperty(status);
        this.address = new SimpleStringProperty(address);
        this.stemField = new SimpleStringProperty(stemField);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public LocalDate getJoinDate() {
        return joinDate.get();
    }

    public ObjectProperty<LocalDate> joinDateProperty() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate.set(joinDate);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getStemField() {
        return stemField.get();
    }

    public StringProperty stemFieldProperty() {
        return stemField;
    }

    public void setStemField(String stemField) {
        this.stemField.set(stemField);
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    public void setPassword(String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    }
}
