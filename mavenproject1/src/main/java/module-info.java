module com.mhsh.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
   requires java.desktop;
    opens com.mhsh.mavenproject1 to javafx.fxml;
    exports com.mhsh.mavenproject1;
}
