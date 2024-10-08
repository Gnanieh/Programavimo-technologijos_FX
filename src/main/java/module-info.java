module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires lombok;
    requires java.sql;
    requires java.naming;
    requires org.hibernate.orm.core;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.fxControllers;
    opens org.example.fxControllers to javafx.fxml;

    opens org.example.Model to javafx.fxml, jakarta.persistence, org.hibernate.orm.core;
    exports org.example.Model;
}